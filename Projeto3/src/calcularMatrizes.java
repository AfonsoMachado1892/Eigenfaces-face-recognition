import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class calcularMatrizes {

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

    public static double calcularErroAbsolutoMedio (double[][] A, double[][] Ak) {
        double erroAbsolutoMedio;
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
        return calcularMatrizes.multiplicarMatrizes(calcularMatrizes.multiplicarMatrizes(vetoresproprios, matrizdiagonal), vetorTransposto);
    }

    public static double[][] calcularA(double[][] matrizdiagonal, double[][] vetoresproprios) {
        return calcularMatrizes.multiplicar_VetorValor_VetorTransposto(matrizdiagonal, vetoresproprios, calcularMatrizes.calcularTransposta(vetoresproprios));
    }

    public static boolean verificarMatriz(double[][] matriz) {
        if((matriz.length != matriz[0].length)) {
            return false;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if ((matriz[i][j] != matriz[j][i])) {
                    return false;
                }
            }
        }

        if(matriz.length >= 256) {
            return false;
        }

        return true;
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

    public static double[][] calcularAk(double[][] matrizDiagonalReduzida, double[][] vetoresPropriosReduzidos) {
        double[][] vetoresPropriosTranspostos = calcularMatrizes.calcularTransposta(vetoresPropriosReduzidos);
        double[][] pkDk = calcularMatrizes.multiplicarMatrizes(vetoresPropriosReduzidos, matrizDiagonalReduzida);
        return calcularMatrizes.multiplicarMatrizes(pkDk, vetoresPropriosTranspostos);
    }

    public static double[][] normalizarVetoresProprios(double[][]vetoresProprios) {
        // Normalizar vetores próprios
        double[][] vetoresPropriosNormalizados = new double [vetoresProprios.length][vetoresProprios[0].length];
        for (int i = 0; i < vetoresProprios.length; i++) {
            double soma = 0;
            for (int j = 0; j < vetoresProprios[i].length; j++) {
                soma += Math.pow(vetoresProprios[i][j], 2);
            }
            double norma = Math.sqrt(soma);
            for (int j = 0; j < vetoresProprios[i].length; j++) {
                vetoresPropriosNormalizados[i][j] = vetoresProprios[i][j] / norma;
            }
        }
        return vetoresPropriosNormalizados;
    }

    public static double[][] reconstruirImagem(double[][] imagem, double[][] media, double[][] eigenfaces, int k) {
        // Subtrair média da imagem
        double[][] imagemCentrada = new double[imagem.length][1];
        for (int i = 0; i < imagem.length; i++) {
            imagemCentrada[i][0] = imagem[i][0] - media[i][0];
        }

        // Calcular pesos
        double[][] pesos = new double[k][1];
        for (int i = 0; i < k; i++) {
            double soma = 0;
            for (int j = 0; j < imagemCentrada.length; j++) {
                soma += eigenfaces[j][i] * imagemCentrada[j][0];
            }
            pesos[i][0] = soma;
        }

        // Reconstruir imagem
        double[][] imagemReconstruida = new double[media.length][1];
        for (int i = 0; i < media.length; i++) {
            imagemReconstruida[i][0] = media[i][0];
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < media.length; j++) {
                imagemReconstruida[j][0] += eigenfaces[j][i] * pesos[i][0];
            }
        }
        return imagemReconstruida;
    }

    public static double[][] calcularMedia(double[][] matrizM) {
        int numImagens = 0;
        double somaValoresImagens = 0;
        double[][] media = new double[matrizM.length][1];

        for (int colunas = 0; colunas < matrizM.length; colunas++) {
            for (int linhas = 0; linhas < matrizM[0].length; linhas++) {
                somaValoresImagens += matrizM[linhas][colunas];
                numImagens++;
            }
            media[colunas][0] = somaValoresImagens / numImagens;
        }
        return media;
    }

    public static double[][] calcularPhi(double[][] matrizM, double[][] media) {
        double[][] phi = new double[matrizM.length][1];

        for (int linhas = 0; linhas < matrizM.length; linhas++) {
            for (int colunas = 0; colunas < matrizM[0].length; colunas++) {
                phi[linhas][colunas] = matrizM[linhas][0] - media[linhas][0];
            }
        }
        return phi;
    }

    public static double[][] vetoresPropriosC(double[][] phi) {
        double[][] phiTransposta = calcularTransposta(phi);
        double[][] matrizPhiTransposta_Phi = multiplicarMatrizes(phiTransposta, phi);
        double[][] vetoresPropriosPhiTransposta_Phi = matrizVetoresProprios(matrizPhiTransposta_Phi);
        double[][] vetoresPropriosC = multiplicarMatrizes(phi, vetoresPropriosPhiTransposta_Phi);
        return vetoresPropriosC;
    }
}
