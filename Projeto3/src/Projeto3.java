import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import org.apache.commons.math3.linear.*;
import java.lang.Math;

public class Projeto3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;
        double[][] matriz;
        double[][] vetoresProprios;
        double[][] valoresProprios;
        String diretorio = "";

        while (opcao != 4) {
            System.out.println("=========== Menu ==========");
            System.out.println("1. Decomposição Própria de Matriz Simétrica");
            System.out.println("2. Reconstrução de Imagens Utilizando Eigenfaces");
            System.out.println("3. Identificação de Imagem Mais Próxima");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Insira o diretório para o ficheiro: ");
                    diretorio = sc.nextLine();
                    matriz = lerMatriz(diretorio);

                    if (eSimetrica(matriz)) {
                        vetoresProprios = matrizVetoresProprios(matriz);
                        valoresProprios = matrizDiagonalValoresProprios(matriz);
                        double erroAbsolutoMedio;
                        int k = 0;
                        boolean kValido = false;

                        while (!kValido) {
                            System.out.print("Digite o número de vetores próprios (k) a utilizar (1-" + matriz.length + "): ");
                            k = sc.nextInt();
                            sc.nextLine();

                            if (k>0 && k <= matriz.length) {
                                kValido = true;

                            } else {
                                System.out.print("Valor inválido! Por favor insira um número entre 1 e " + matriz.length + ".");
                            }
                        }
                        double[][] A = calcularA(matriz, valoresProprios, vetoresProprios);
                        double[][] vetoresPropriosReduzidos = vetorespropriosreduzidos(vetoresProprios, k);
                        double[][] valoresPropriosReduzidos = matrizdiagonalreduzida(valoresProprios, k);
                        double [][] Ak = calcularAk(valoresPropriosReduzidos, vetoresPropriosReduzidos, k);

                        erroAbsolutoMedio = calcularErroAbsolutoMedio(A, Ak);

                        exibirMatriz(matriz, "Matriz CSV");
                        System.out.println("Número de vetores próprios:" + k);
                        exibirMatriz(vetoresPropriosReduzidos, "Vetores Próprios Reduzido");
                        exibirMatriz(valoresPropriosReduzidos, "Valores Próprios Reduzido");
                        exibirMatriz(calcularTransposta(vetoresPropriosReduzidos), "Vetores Próprios Reduzidos Transpostos");
                        exibirMatriz(Ak, "Matriz Ak (Aproximação com " + k + " vetores próprios)");
                        System.out.printf("Erro absoluto médio: %.2f%% \n", erroAbsolutoMedio);

                    } else {
                        System.out.println("A matriz não é simétrica. Não é possível realizar a decomposição.");
                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("A sair...");
                    return;
                default:
                    System.out.println("Input Inválido. [1-4]");
            }
        }
    }

    public static double[][] lerMatriz(String diretorio) throws FileNotFoundException {
        //src/inputs/Funcionalidade1/exampleInputFunc1.csv
        File file = new File(diretorio);
        Scanner in = new Scanner(file);
        int cont = 0;
        int linhas;

        while (in.hasNextLine()) {
            cont++;
            in.nextLine();
        }

        linhas = cont - 1;

        in = new Scanner(file);

        double[][] matriz = new double[linhas][linhas];

        for (int i = 0; i < linhas; i++) {
            String[] valores = in.nextLine().split(",");
            for (int j = 0; j < linhas; j++) {
                matriz[i][j] = Double.parseDouble(valores[j]);
            }
        }

        in.close();

        return matriz;
    }

    public static double[][] matrizVetoresProprios(double[][] matriz) {
        RealMatrix matrizreal = new Array2DRowRealMatrix(matriz);

        EigenDecomposition decomposicao = new EigenDecomposition(matrizreal);

        double[][] vetoresproprios = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            double[] vetorProprio = decomposicao.getEigenvector(i).toArray();
            for (int j = 0; j < matriz.length; j++) {
                vetoresproprios[j][i] = vetorProprio[j];
            }
        }

        return vetoresproprios;
    }

    public static double[][] matrizDiagonalValoresProprios(double[][] matriz) {
        RealMatrix matrizReal = new Array2DRowRealMatrix(matriz);

        EigenDecomposition decomposicao = new EigenDecomposition(matrizReal);

        double[] valoresproprios = decomposicao.getRealEigenvalues();

        int tamanho = valoresproprios.length;

        double[][] matrizdiagonal = new double[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            matrizdiagonal[i][i] = valoresproprios[i];
        }

        return matrizdiagonal;
    }

    public static boolean eSimetrica(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static double[][] calcularTransposta(double[][] matriz) {
        double[][] mTransposta;
        mTransposta = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                mTransposta[j][i] = matriz[i][j];
            }
        }
        return mTransposta;
    }

    public static void exibirMatriz(double[][] matriz, String nomeMatriz) {
        System.out.println(nomeMatriz + ":");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] multiplicarMatrizes(double[][] matriz1, double[][] matriz2) {
        double[][] matrizMultiplicada = new double[matriz1.length][matriz2[0].length];
        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz2[0].length; j++) {
                for (int l = 0; l < matriz2.length; l++) {
                    matrizMultiplicada[i][j] += matriz1[i][l] * matriz2[l][j];
                }
            }
        }
        return matrizMultiplicada;
    }

    public static double[][] multiplicar_VetorValor_VetorTransposto(double[][] matrizdiagonal, double[][] vetoresproprios, double[][] vetorTransposto) {
        double[][] resultado = multiplicarMatrizes(multiplicarMatrizes(vetoresproprios, matrizdiagonal), vetorTransposto);
        return resultado;
    }

    public static double[][] calcularA(double[][] matriz, double[][] matrizdiagonal, double[][] vetoresproprios) {
        double[][] matrizA = multiplicar_VetorValor_VetorTransposto(matrizdiagonal, vetoresproprios, calcularTransposta(vetoresproprios));
        return matrizA;
    }

    public static double[][] vetorespropriosreduzidos(double[][] vetoresproprios, int k) {
        double[][] vetoresPropriosReduzidos = new double[vetoresproprios.length][k];
        for (int i = 0; i < vetoresproprios.length; i++) {
            for (int j = 0; j < k; j++) {
                vetoresPropriosReduzidos[i][j] = vetoresproprios[i][j];
            }
        }
        return vetoresPropriosReduzidos;
    }

    public static double[][] matrizdiagonalreduzida(double[][] matrizDiagonal, int k) {
        double[][] matrizDiagonalReduzida = new double[k][k];
        for (int i = 0; i < k; i++) {
            matrizDiagonalReduzida[i][i] = matrizDiagonal[i][i];
        }
        return matrizDiagonalReduzida;
    }

    public static double[][] calcularAk(double[][] matrizDiagonalReduzida, double[][] vetoresPropriosReduzidos, int k) {
        double[][] vetoresPropriosTranspostos = calcularTransposta(vetoresPropriosReduzidos);
        double[][] pkDk = multiplicarMatrizes(vetoresPropriosReduzidos, matrizDiagonalReduzida);
        return multiplicarMatrizes(pkDk, vetoresPropriosTranspostos);
    }

    public static double calcularErroAbsolutoMedio (double[][] A, double[][] Ak) {
        double erroAbsolutoMedio = 0;
        double moduloDiferencaEntreMatrizes = 0;
        int M = Ak.length;
        int N = Ak[0].length;
        for (int linhas = 0; linhas < M; linhas++) {
            for (int colunas = 0; colunas < N; colunas++) {
                moduloDiferencaEntreMatrizes += Math.abs(A[linhas][colunas]-Ak[linhas][colunas]);
            }
        }
        erroAbsolutoMedio =  moduloDiferencaEntreMatrizes / (M*N);
        return erroAbsolutoMedio;
    }
}