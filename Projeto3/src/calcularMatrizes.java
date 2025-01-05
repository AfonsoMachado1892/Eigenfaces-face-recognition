import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public static double[][] calcularA(double[][] matrizdiagonal, double[][] vetoresproprios) {
        return calcularMatrizes.multiplicarMatrizes(
                calcularMatrizes.multiplicarMatrizes(vetoresproprios, matrizdiagonal),
                calcularMatrizes.calcularTransposta(vetoresproprios)
        );
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

        return matriz.length < 256;
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

    public static double[][] normalizarVetoresProprios(double[][] vetoresProprios) {
        double[][] vetoresPropriosNormalizados = new double[vetoresProprios.length][vetoresProprios[0].length];

        for (int j = 0; j < vetoresProprios[0].length; j++) {
            double soma = 0;

            for (int i = 0; i < vetoresProprios.length; i++) {
                soma += Math.pow(vetoresProprios[i][j], 2);
            }
            double norma = Math.sqrt(soma);

            for (int i = 0; i < vetoresProprios.length; i++) {
                vetoresPropriosNormalizados[i][j] = vetoresProprios[i][j] / norma;
            }
        }

        return vetoresPropriosNormalizados;
    }


    public static double[][] calcularMedia(double[][] matriz) {
        double[][] media = new double[matriz.length][1];
        double soma;

        for (int i = 0; i < matriz.length; i++) {
            soma = 0;
            for (int j = 0; j < matriz[0].length; j++) {
                soma += matriz[i][j];
            }
            media[i][0] = soma / matriz[0].length;
        }

        return media;
    }


    public static double[][] calcularPhi(double[][] matriz, double[][] media) {
        double[][] phi = new double[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                phi[i][j] = matriz[i][j] - media[i][0];
            }
        }
        return phi;
    }


    public static double[][] vetoresPropriosC(double[][] matrizA) {
        double[][] transpostaA = calcularTransposta(matrizA);

        double[][] matrizATA = multiplicarMatrizes(transpostaA, matrizA);

        double[][] matrizVetoresPropriosATA = matrizVetoresProprios(matrizATA);

        int linhasA = matrizA.length;
        int numVetoresProprios = matrizVetoresPropriosATA[0].length;

        double[][] vetoresPropriosC = new double[linhasA][numVetoresProprios];

        for (int i = 0; i < numVetoresProprios; i++) {
            double[][] vetorViColuna = new double[matrizATA.length][1];
            for (int j = 0; j < vetorViColuna.length; j++) {
                vetorViColuna[j][0] = matrizVetoresPropriosATA[j][i];
            }

            double[][] vetorUiColuna = multiplicarMatrizes(matrizA, vetorViColuna);

            for (int j = 0; j < linhasA; j++) {
                vetoresPropriosC[j][i] = vetorUiColuna[j][0];
            }
        }

        return vetoresPropriosC;
    }
    
    public static double[][] calcularPesos(double[][] A, double[][] eigenfaces, int k) {
        int numImagens = A[0].length; // Número de imagens
        double[][] pesos = new double[k][numImagens]; // Matriz para armazenar os pesos de todas as imagens

        for (int i = 0; i < numImagens; i++) { // Para cada imagem
            for (int p = 0; p < k; p++) { // Para cada eigenface
                double soma = 0;
                for (int j = 0; j < A.length; j++) { // Para cada pixel
                    soma += eigenfaces[j][p] * A[j][i];
                }
                pesos[p][i] = soma;
            }
        }

        return pesos;
    }

    public static double[][] reconstruirTodasImagens(double[][] media, double[][] eigenfaces, double[][] pesos, int k) {
        int numImagens = pesos[0].length; // Número de imagens
        double[][] imagensReconstruidas = new double[media.length][numImagens]; // Matriz para armazenar as imagens reconstruídas

        for (int i = 0; i < numImagens; i++) {
            // Inicializar com a média
            for (int j = 0; j < media.length; j++) {
                imagensReconstruidas[j][i] = media[j][0];
            }

            // Adicionar contribuição das eigenfaces ponderadas pelos pesos
            for (int p = 0; p < k; p++) {
                for (int j = 0; j < media.length; j++) {
                    imagensReconstruidas[j][i] += eigenfaces[j][p] * pesos[p][i];
                }
            }
        }

        return imagensReconstruidas;
    }

    public static void escreverArraycomoImagem(double[][] array, String caminhoOutput) throws IOException {
        int alturaImagem = array.length;
        int larguraImagem = array[0].length;
        BufferedImage imagem = new BufferedImage(larguraImagem, alturaImagem, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < alturaImagem; y++) {
            for (int x = 0; x < larguraImagem; x++) {
                int intensidade = (int) array[y][x];
                if (intensidade < 0 || intensidade > 255) {
                    throw new IllegalArgumentException("A intensidade do pixel deve estar entre 0 e 255.");
                }
                int rgb = (intensidade << 16) | (intensidade << 8) | intensidade;
                imagem.setRGB(x, y, rgb);
            }
        }
        File outputFile = new File(caminhoOutput);
        ImageIO.write(imagem, "jpg", outputFile);
    }


    // Método para calcular a distância euclidiana entre dois vetores
    public static double[] calcularDistanciaEuclidianaMatriz(double[][] omegaNova, double[][] omegaJ) {
        double[] distanciaEuclidiana = new double[omegaNova[0].length];

        for (int colunas = 0; colunas < omegaNova[0].length; colunas++) {
            double soma = 0;
            for (int linhas = 0; linhas < omegaNova.length; linhas++) {
                soma += Math.pow(omegaNova[linhas][0] - omegaJ[colunas][linhas], 2);
            }
            distanciaEuclidiana[colunas] = Math.sqrt(soma);
        }
        return distanciaEuclidiana;
    }

    public static double[][] construirVetoresPesos(double[][] pesosBase) {
        int numImagens = pesosBase[0].length;
        double[][] vetoresPesos = new double[numImagens][pesosBase.length];

        for (int i = 0; i < numImagens; i++) {
            for (int j = 0; j < pesosBase.length; j++) {
                vetoresPesos[i][j] = pesosBase[j][i];
            }
        }
        return vetoresPesos;
    }

    public static int obterIndiceMenorDistancia(double[] distancias) {
        double menorValor = distancias[0];
        int menorIndice = 0;

        for(int i = 1; i < distancias.length; i++) {
            if(distancias[i] < menorValor) {
                menorValor = distancias[i];
                menorIndice = i;
            }
        }

        return menorIndice;
    }


}
