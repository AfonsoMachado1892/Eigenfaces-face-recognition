import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("===================== Menu =====================");
        System.out.println("1. Decomposição Própria de Matriz Simétrica");
        System.out.println("2. Reconstrução de Imagens Utilizando Eigenfaces");
        System.out.println("3. Identificação de Imagem Mais Próxima");
        System.out.println("4. Sair \n");
        System.out.print("Escolha uma opção: ");
    }

    public static int obterOpcao() {
        return sc.nextInt();
    }

    public static void executarOpcao(int opcao) throws FileNotFoundException {
        sc.nextLine();
        switch (opcao) {
            case 1:
                decomposicaoPropria();
                break;
            case 2:
                reconstruirImagens();
                break;
            case 3:
                identificarImagem();
                break;
            case 4:
                System.out.println("A sair...");
                break;
            default:
                System.out.println("Input Inválido. [1-4]");
        }
    }

    private static void decomposicaoPropria() throws FileNotFoundException {
        //src\inputs\Funcionalidade1\exampleInputFunc1.csv

        System.out.print("\nInsira o diretório para o ficheiro: ");
        String diretorio = sc.nextLine();
        double[][] matriz = leiturasaidadados.lerMatriz(diretorio);

        if (calcularMatrizes.verificarMatriz(matriz)) {
            double[][] vetoresProprios = calcularMatrizes.matrizVetoresProprios(matriz);
            double[][] valoresProprios = calcularMatrizes.matrizDiagonalValoresProprios(matriz);

            int k = obterK(matriz[0].length, "Selecione o número de vetores próprios a utilizar: ");

            double[][] A = calcularMatrizes.calcularA(valoresProprios, vetoresProprios);
            double[][] vetoresPropriosReduzidos = calcularMatrizes.vetorespropriosreduzidos(vetoresProprios, k);
            double[][] valoresPropriosReduzidos = calcularMatrizes.matrizdiagonalreduzida(valoresProprios, k);
            double[][] Ak = calcularMatrizes.calcularAk(valoresPropriosReduzidos, vetoresPropriosReduzidos);
            double erroAbsolutoMedio = calcularMatrizes.calcularErroAbsolutoMedio(A, Ak);

            System.out.println("\nNúmero de vetores próprios: " + k);
            leiturasaidadados.exibirMatriz(vetoresPropriosReduzidos, "\nVetores Próprios Reduzido", 2);
            leiturasaidadados.exibirMatriz(valoresPropriosReduzidos, "\nValores Próprios Reduzido", 2);
            leiturasaidadados.exibirMatriz(Ak, "\nMatriz Ak (Aproximação com " + k + " vetores próprios)", 2);
            System.out.printf("\nErro absoluto médio: %.2f \n", erroAbsolutoMedio);
            leiturasaidadados.guardarMatriz(Ak, diretorio, "src\\outputs\\funcionalidade1");

        } else {
            System.out.println("A matriz não é simétrica. Não é possível realizar a decomposição.");
        }
    }

    public static int obterK(int tamanho, String mensagem) {
        Scanner scanner = new Scanner(System.in);
        int k;

        while (true) {
            System.out.println(mensagem);
            System.out.printf("Introduza um valor para k (0 < k < %d): ", tamanho);

            k = scanner.nextInt();

            if (k == -1) {
                k = tamanho;
                break;
            } else if (k > 0 && k <= tamanho) {
                break;
            } else if (k > tamanho) {
                k = tamanho;
                break;
            } else {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
        return k;
    }


    private static void reconstruirImagens() throws FileNotFoundException {
        //src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3

        System.out.print("\nInsira o diretório da pasta com as imagens a reconstruir: ");
        String pasta = sc.nextLine();
        final String ficheiroOutput = "src/vetores.csv";

        leiturasaidadados.lerVetoresDeImagens(pasta, ficheiroOutput);

        double[][] matrizM = leiturasaidadados.lerMatriz(ficheiroOutput);
        matrizM = calcularMatrizes.calcularTransposta(matrizM);

        double[][] media = calcularMatrizes.calcularMedia(matrizM);
        double[][] matrizPhi = calcularMatrizes.calcularPhi(matrizM, media);
        double[][] vetoresPropriosC = calcularMatrizes.vetoresPropriosC(matrizPhi);
        double[][] vetoresPropriosNormalizados = calcularMatrizes.normalizarVetoresProprios(vetoresPropriosC);

        int k = obterK(vetoresPropriosNormalizados[0].length, "Selecione o número de eigenfaces a utilizar na reconstrução de imagens: ");

        double[][] pesos = calcularMatrizes.calcularPesos(matrizPhi, vetoresPropriosNormalizados, k);
        double[][] imagensReconstruidas = calcularMatrizes.reconstruirTodasImagens(media, vetoresPropriosNormalizados, pesos, k);

        leiturasaidadados.exibirMatriz(media, "Matriz µ", 0);

        System.out.println();
        for (int i = 0; i < pesos[0].length; i++) {
            System.out.println("Imagem " + (i + 1) + ":");
            System.out.print("Pesos w: ");
            for (int p = 0; p < k; p++) {
                System.out.print(pesos[p][i] + " ");
            }
            System.out.println();
        }

        System.out.printf("%nNúmero de Eigenfaces utilizadas na reconstrução de cada imagem: %d%n", k);

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                System.out.printf("%f, ", matrizPhi[i][j]);
            }
            System.out.println();
        }



    }

    private static void identificarImagem() {
        // Coloque aqui a lógica da funcionalidade 3
    }
}
