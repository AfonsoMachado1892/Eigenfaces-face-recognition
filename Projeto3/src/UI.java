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

            int k = obterK(matriz.length);

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
            leiturasaidadados.guardarMatriz(Ak, diretorio, "src\\Output\\funcionalidade1");

        } else {
            System.out.println("A matriz não é simétrica. Não é possível realizar a decomposição.");
        }
    }

    private static int obterK(int tamanhoMaximo) {
        int k;
        int limiteSuperior = Math.min(8, tamanhoMaximo); // O limite de k é o menor entre 8 e tamanhoMaximo.

        while (true) {
            System.out.print("\nDigite o número de vetores próprios (k) a utilizar (-1 para todos ou 1-" + limiteSuperior + "): ");
            k = sc.nextInt();
            sc.nextLine();

            if (k == -1) {
                System.out.println("Você escolheu usar todos os vetores próprios disponíveis.");
                return limiteSuperior; // Retorna o valor máximo permitido (até 8).
            } else if (k >= 1 && k <= limiteSuperior) {
                System.out.println("Você escolheu " + k + " vetores próprios. Continuando...");
                return k; // Retorna o valor válido de k.
            } else {
                System.out.println("Valor inválido! Por favor insira um número válido entre 1 e " + limiteSuperior + ", ou -1 para todos.");
            }
        }
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

        int k = obterK(vetoresPropriosC.length);
    }

    private static void identificarImagem() {
        // Coloque aqui a lógica da funcionalidade 3
    }
}
