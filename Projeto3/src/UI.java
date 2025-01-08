import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {
    private static final Scanner sc = new Scanner(System.in);

    public static void mostrarMenu() {
        System.out.println("\n===================== Menu =====================");
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
                System.out.print("\nInsira o diretório para o ficheiro: ");
                String diretorio = sc.nextLine();

                System.out.print("\nSelecione o número de Vetores Próprios a utilizar: ");
                int k = sc.nextInt();

                decomposicaoPropria(k, diretorio);
                break;
            case 2:
                System.out.print("\nInsira o diretório da pasta com as imagens a reconstruir: ");
                String pasta = sc.nextLine();

                System.out.print("\nSelecione o número de Eigenfaces a utilizar: ");
                int e = sc.nextInt();

                reconstruirImagens(e, pasta);
                break;
            case 3:
                System.out.print("\nInsira o diretório da imagem a identificar: ");
                String diretorioNovaI = sc.nextLine();
                System.out.print("\nInsira a localização da base imagens: ");
                String baseImagens = sc.nextLine();

                System.out.print("\nSelecione o número de Eigenfaces a utilizar: ");
                int f = sc.nextInt();

                identificarImagem(f, diretorioNovaI, baseImagens);
                break;
            case 4:
                System.out.println("A sair...");
                break;
            default:
                System.out.println("Input Inválido. [1-4]");
        }
    }

    public static void decomposicaoPropria(int k, String diretorio) throws FileNotFoundException {
        //src\inputs\Funcionalidade1\exampleInputFunc1.csv

        double[][] matriz = leiturasaidadados.lerMatriz(diretorio);

        if (calcularMatrizes.verificarMatriz(matriz)) {
            double[][] vetoresProprios = calcularMatrizes.matrizVetoresProprios(matriz);
            double[][] valoresProprios = calcularMatrizes.matrizDiagonalValoresProprios(matriz);

            k = verificarK(matriz[0].length, k);

            double[][] A = calcularMatrizes.calcularA(valoresProprios, vetoresProprios);
            double[][] vetoresPropriosReduzidos = calcularMatrizes.vetoresPropriosReduzidos(vetoresProprios, k);
            double[][] valoresPropriosReduzidos = calcularMatrizes.matrizDiagonalReduzida(valoresProprios, k);
            double[][] Ak = calcularMatrizes.calcularAk(valoresPropriosReduzidos, vetoresPropriosReduzidos);
            double erroAbsolutoMedio = calcularMatrizes.calcularErroAbsolutoMedio(A, Ak);

            System.out.println("\nNúmero de vetores próprios: " + k);
            leiturasaidadados.exibirMatriz(vetoresPropriosReduzidos, "\nVetores Próprios Reduzido", 2);
            leiturasaidadados.exibirMatriz(valoresPropriosReduzidos, "\nValores Próprios Reduzido", 2);
            leiturasaidadados.exibirMatriz(Ak, "\nMatriz Ak (Aproximação com " + k + " vetores próprios)", 2);
            System.out.printf("\nErro absoluto médio: %.2f \n", erroAbsolutoMedio);
            leiturasaidadados.guardarMatriz(Ak, "exampleInputFunc1.csv", "src\\outputs\\funcionalidade1");

        } else {
            System.out.println("A matriz não é simétrica. Não é possível realizar a decomposição.");
        }
    }

    public static int verificarK(int tamanho, int k) {
        if (k == -1) {
            k = tamanho;
        } else if (k > 0 && k <= tamanho) {
            return k;
        } else if (k > tamanho) {
            k = tamanho;
        } else {
            throw new IllegalArgumentException("Valor inválido para k. Deve ser -1 ou um valor positivo.");
        }
        return k;
    }


    public static void reconstruirImagens(int k, String pasta) throws FileNotFoundException {
        //src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3

        final String ficheiroOutput = "src/vetores.csv";

        leiturasaidadados.lerVetoresDeImagens(pasta, ficheiroOutput);

        double[][] matrizM = leiturasaidadados.lerMatriz(ficheiroOutput);
        matrizM = calcularMatrizes.calcularTransposta(matrizM);

        double[][] media = calcularMatrizes.calcularMedia(matrizM);
        double[][] matrizPhi = calcularMatrizes.calcularPhi(matrizM, media);
        double[][] vetoresPropriosC = calcularMatrizes.vetoresPropriosC(matrizPhi);
        double[][] vetoresPropriosNormalizados = calcularMatrizes.normalizarVetoresPropriosC(vetoresPropriosC);

        k = verificarK(vetoresPropriosNormalizados[0].length, k);

        double[][] pesos = calcularMatrizes.calcularPesos(matrizPhi, vetoresPropriosNormalizados, k);
        double[][] imagensReconstruidas = calcularMatrizes.reconstruirTodasImagens(media, vetoresPropriosNormalizados, pesos, k);

        leiturasaidadados.exibirMatriz(media, "Matriz µ", 2);

        System.out.println();
        for (int i = 0; i < pesos[0].length; i++) {
            System.out.println("Imagem " + (i + 1) + ":");
            System.out.print("Pesos w: ");
            for (int p = 0; p < k; p++) {
                System.out.printf("%.2f ", pesos[p][i]);
            }
            System.out.println();
        }

        System.out.printf("%nNúmero de Eigenfaces utilizadas na reconstrução de cada imagem: %d%n", k);

        System.out.printf("%nEAM: %.2f%n", calcularMatrizes.calcularErroAbsolutoMedio(matrizM, imagensReconstruidas));

        leiturasaidadados.guardarCSV(vetoresPropriosNormalizados, "src\\outputs\\funcionalidade2\\Eigenfaces", "Eigenface");
        leiturasaidadados.guardarImagem(vetoresPropriosNormalizados, "src\\outputs\\funcionalidade2\\Eigenfaces", "Eigenface");
        leiturasaidadados.guardarCSV(imagensReconstruidas, "src\\outputs\\funcionalidade2\\ImagensReconstruidas", "imagem");
        leiturasaidadados.guardarImagem(imagensReconstruidas, "src\\outputs\\funcionalidade2\\ImagensReconstruidas", "imagem");
    }

    public static void identificarImagem(int k, String diretorioNovaI, String baseImagens) throws FileNotFoundException {
        int cont = 0;

        leiturasaidadados.lerVetoresDeImagens(baseImagens, "src/vetores.csv");

        double[][] novaImagemMatriz = leiturasaidadados.lerMatriz(diretorioNovaI);
        double[][] novaImagemVetor = new double[novaImagemMatriz.length * novaImagemMatriz.length][1];

        for(int i = 0; i < novaImagemMatriz.length; i++) {
            for(int j = 0; j < novaImagemMatriz[0].length; j++) {
                novaImagemVetor[cont][0] = novaImagemMatriz[i][j];
                cont++;
            }
        }

        double[][]matrizImagens = leiturasaidadados.lerMatriz("src/vetores.csv");
        matrizImagens = calcularMatrizes.calcularTransposta(matrizImagens);
        double[][] media = calcularMatrizes.calcularMedia(matrizImagens);
        double[][] matrizPhi = calcularMatrizes.calcularPhi(matrizImagens, media);
        double[][] vetoresPropriosC = calcularMatrizes.vetoresPropriosC(matrizPhi);
        double[][] vetoresPropriosNormalizados = calcularMatrizes.normalizarVetoresPropriosC(vetoresPropriosC);

        k = verificarK(vetoresPropriosNormalizados[0].length, k);

        double[][] phiNovo = calcularMatrizes.calcularPhi(novaImagemVetor, media);
        double[][] pesosNovaImagem = calcularMatrizes.calcularPesos(phiNovo, vetoresPropriosNormalizados, k);
        double[][] pesosBaseImagens = calcularMatrizes.calcularPesos(matrizPhi, vetoresPropriosNormalizados, k);

        double[] distancias = calcularMatrizes.calcularDistanciaEuclidianaMatriz(pesosNovaImagem, pesosBaseImagens);

        int menorDistancia = calcularMatrizes.obterIndiceMenorDistancia(distancias);

        System.out.println(menorDistancia);

    }
}
