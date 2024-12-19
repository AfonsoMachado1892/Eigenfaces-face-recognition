import java.io.FileNotFoundException;
import java.util.Scanner;

public class Projeto3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;
        double[][] matriz;
        double[][] vetoresProprios;
        double[][] valoresProprios;
        String diretorio;

        while (opcao != 4) {
            System.out.println("===================== Menu =====================");
            System.out.println("1. Decomposição Própria de Matriz Simétrica");
            System.out.println("2. Reconstrução de Imagens Utilizando Eigenfaces");
            System.out.println("3. Identificação de Imagem Mais Próxima");
            System.out.println("4. Sair \n");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nInsira o diretório para o ficheiro: ");
                    diretorio = sc.nextLine();
                    matriz = leiturasaidadados.lerMatriz(diretorio);

                    if (calcularMatrizes.verificarMatriz(matriz)) {
                        vetoresProprios = calcularMatrizes.matrizVetoresProprios(matriz);
                        valoresProprios = calcularMatrizes.matrizDiagonalValoresProprios(matriz);
                        double erroAbsolutoMedio;
                        int k = 0;
                        boolean kValido = false;

                        while (!kValido) {
                            System.out.print("\nDigite o número de vetores próprios (k) a utilizar (1-" + matriz.length + "): ");
                            k = sc.nextInt();
                            sc.nextLine();

                            if (k>0 && k <= matriz.length) {
                                kValido = true;

                            } else {
                                System.out.print("\nValor inválido! Por favor insira um número entre 1 e " + matriz.length + ".\n");
                            }
                        }
                        double[][] A = calcularMatrizes.calcularA(valoresProprios, vetoresProprios);
                        double[][] vetoresPropriosReduzidos = calcularMatrizes.vetorespropriosreduzidos(vetoresProprios, k);
                        double[][] valoresPropriosReduzidos = calcularMatrizes.matrizdiagonalreduzida(valoresProprios, k);
                        double[][] Ak = calcularMatrizes.calcularAk(valoresPropriosReduzidos, vetoresPropriosReduzidos);
                        erroAbsolutoMedio = calcularMatrizes.calcularErroAbsolutoMedio(A, Ak);

                        System.out.println("\nNúmero de vetores próprios: " + k);
                        leiturasaidadados.exibirMatriz(vetoresPropriosReduzidos, "\nVetores Próprios Reduzido", 2);
                        leiturasaidadados.exibirMatriz(valoresPropriosReduzidos, "\nValores Próprios Reduzido", 2);
                        leiturasaidadados.exibirMatriz(Ak, "\nMatriz Ak (Aproximação com " + k + " vetores próprios)", 2);
                        System.out.printf("\nErro absoluto médio: %.2f \n", erroAbsolutoMedio);
                        leiturasaidadados.guardarMatriz(Ak, diretorio, "Projeto3\\src\\Output\\funcionalidade1");

                    } else {
                        System.out.println("A matriz não é simétrica. Não é possível realizar a decomposição.");
                    }

                    break;
                case 2:
                    final String dirFunc23 = "src\\inputs\\Funcionalidades 2 e 3\\exemploMatrizesImagensCSVFuncionalidades2e3\\exemploMatrizesImagensCSVFuncionalidades2e3";
                    final String ficheiroOutput = "src/vetores.csv";

                    double[][] matrizM = leiturasaidadados.lerMatriz(ficheiroOutput);
                    matrizM = calcularMatrizes.calcularTransposta(matrizM);

                    for(int i = 0; i < matrizM.length; i++) {
                        for(int j = 0; j < matrizM[i].length; j++) {
                            System.out.print(matrizM[i][j] + " ");
                        }
                        System.out.println();
                    }

                    leiturasaidadados.lerVetoresDeImagens(dirFunc23, ficheiroOutput);
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
}