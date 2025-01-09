import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class calcularMatrizes {
    //---------- Funcionalidade 1 ----------

    //P - Matriz dos vetores próprios unitários
    public static double[][] matrizVetoresProprios(double[][] matriz, int k) {
        RealMatrix matrizreal = new Array2DRowRealMatrix(matriz);
        EigenDecomposition decomposicao = new EigenDecomposition(matrizreal);
        double[][] vetoresProprios = new double[matriz.length][matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            double[] vetorProprio = decomposicao.getEigenvector(i).toArray();
            for (int j = 0; j < matriz.length; j++) {
                vetoresProprios[j][i] = vetorProprio[j];
            }
        }

        double[] valoresProprios = decomposicao.getRealEigenvalues();

        int[] indices = new int[valoresProprios.length];
        for (int i = 0; i < valoresProprios.length; i++) {
            indices[i] = i;
        }

        for (int i = 0; i < valoresProprios.length - 1; i++) {
            for (int j = i + 1; j < valoresProprios.length; j++) {
                if (valoresProprios[indices[i]] < valoresProprios[indices[j]]) {
                    int temp = indices[i];
                    indices[i] = indices[j];
                    indices[j] = temp;
                }
            }
        }

        double[][] matrizColuna = new double[vetoresProprios.length][k];
        for (int col = 0; col < k; col++) {
            int index = indices[col];
            for (int row = 0; row < vetoresProprios.length; row++) {
                matrizColuna[row][col] = vetoresProprios[row][index];
            }
        }

        return matrizColuna;
    }

    //D - Matriz diagonal com os valores próprios associados aos vetores próprios da matriz A
    public static double[][] matrizDiagonalValoresProprios(double[][] matriz, int k) {
        RealMatrix matrizReal = new Array2DRowRealMatrix(matriz);
        EigenDecomposition decomposicao = new EigenDecomposition(matrizReal);
        double[] valoresproprios = decomposicao.getRealEigenvalues();

        Arrays.sort(valoresproprios);
        double[] maioresValores = Arrays.copyOfRange(valoresproprios, valoresproprios.length - k, valoresproprios.length);

        int tamanho = maioresValores.length;
        double[][] matrizdiagonal = new double[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            matrizdiagonal[i][i] = maioresValores[tamanho - 1 - i];
        }

        for (int i = 0; i < tamanho; i++) {
            if((int) matrizdiagonal[i][i] == -0) {
                matrizdiagonal[i][i] = 0;
            }
        }

        return matrizdiagonal;
    }

    //P^T - Matriz transposta da matriz P
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

    //Cálculo da multiplicação de matrizes
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

    //A - Decomposição própria de uma matriz simétrica
    public static double[][] calcularA(double[][] matrizdiagonal, double[][] vetoresproprios) {
        return calcularMatrizes.multiplicarMatrizes(
                calcularMatrizes.multiplicarMatrizes(vetoresproprios, matrizdiagonal),
                calcularMatrizes.calcularTransposta(vetoresproprios)
        );
    }

    //Pk - Matriz com os k vetores próprios unitários selecionados
    public static double[][] vetoresPropriosReduzidos(double[][] vetoresproprios, int k) {
        double[][] vetoresPropriosReduzidos = new double[vetoresproprios.length][k];
        for (int i = 0; i < vetoresproprios.length; i++) {
            for (int j = 0; j < k; j++) {
                vetoresPropriosReduzidos[i][j] = vetoresproprios[i][j];
            }
        }
        return vetoresPropriosReduzidos;
    }

    //Dk - Matriz diagonal com os k valores próprios com maior valor absoluto
    public static double[][] matrizDiagonalReduzida(double[][] matrizDiagonal, int k) {
        double[][] matrizDiagonalReduzida = new double[k][k];
        for (int i = 0; i < k; i++) {
            matrizDiagonalReduzida[i][i] = matrizDiagonal[i][i];
        }
        return matrizDiagonalReduzida;
    }

    //Ak - Aproximação da matriz A para k vetores próprios unitários selecionados
    public static double[][] calcularAk(double[][] matrizDiagonalReduzida, double[][] vetoresPropriosReduzidos) {
        double[][] vetoresPropriosTranspostos = calcularMatrizes.calcularTransposta(vetoresPropriosReduzidos);
        double[][] pkDk = calcularMatrizes.multiplicarMatrizes(vetoresPropriosReduzidos, matrizDiagonalReduzida);
        return calcularMatrizes.multiplicarMatrizes(pkDk, vetoresPropriosTranspostos);
    }

    //Cálculo do Erro Absoluto Médio
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

    //Verificação das matrizes
    public static boolean verificarMatriz(double[][] matriz) {
        //Tem que ser uma matriz quadrada
        if (matriz.length == 0 || matriz.length != matriz[0].length) {
            return false;
        }

        //Tem que ter o número de linhas menor que 256
        if (matriz.length >= 256) {
            return false;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                //Tem que ser um número real
                if (Double.isNaN(matriz[i][j]) || Double.isInfinite(matriz[i][j])) {
                    return false;
                }

                //Tem que ser uma matriz simétrica
                if (matriz[i][j] != matriz[j][i]) {
                    return false;
                }
            }
        }

        //Pode ter linhas em branco no final de cada matriz
        for (int i = matriz.length - 1; i >= 0; i--) {
            boolean linhaEmBranco = true;
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] != 0) {
                    linhaEmBranco = false;
                    break;
                }
            }
            if (!linhaEmBranco) {
                break;
            }
        }
        return true;
    }


    //---------- Funcionalidade 2 ----------

    //Cálculo do vetor coluna média - média de todas as imagens presentes na matriz
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

    //Cálculo da matriz phi para cada imagem
    public static double[][] calcularPhi(double[][] matriz, double[][] media) {
        double[][] phi = new double[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                phi[i][j] = matriz[i][j] - media[i][0];
            }
        }
        return phi;
    }

    //Cálculo dos vetores próprios de C
    public static double[][] vetoresPropriosC(double[][] matrizA, int k) {
        double[][] transpostaA = calcularTransposta(matrizA);
        double[][] matrizATA = multiplicarMatrizes(transpostaA, matrizA);
        double[][] matrizVetoresPropriosATA = matrizVetoresProprios(matrizATA, k);
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

    public static double[][] valoresPropriosC(double[][] matrizA, int k) {
        double[][] transpostaA = calcularTransposta(matrizA);
        double[][] matrizATA = multiplicarMatrizes(transpostaA, matrizA);

        return  matrizDiagonalValoresProprios(matrizATA, k);
    }

    //Normalizar os vetores próprios de C
    public static double[][] normalizarVetoresPropriosC(double[][] vetoresProprios) {
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

    //Cálculo dos pesos
    public static double[][] calcularPesos(double[][] A, double[][] eigenfaces, int k) {
        int numImagens = A[0].length;
        double[][] pesos = new double[k][numImagens];

        for (int i = 0; i < numImagens; i++) {
            for (int p = 0; p < k; p++) {
                double soma = 0;
                for (int j = 0; j < A.length; j++) {
                    soma += eigenfaces[j][p] * A[j][i];
                }
                pesos[p][i] = soma;
            }
        }
        return pesos;
    }

    //Reconstrução das imagens, utilizando um determinado número de k eigenfaces e os pesos associados a cada eigenface
    public static double[][] reconstruirTodasImagens(double[][] media, double[][] eigenfaces, double[][] pesos, int k) {
        int numImagens = pesos[0].length;
        double[][] imagensReconstruidas = new double[media.length][numImagens];

        for (int i = 0; i < numImagens; i++) {
            for (int j = 0; j < media.length; j++) {
                imagensReconstruidas[j][i] = media[j][0];
            }
            for (int p = 0; p < k; p++) {
                for (int j = 0; j < media.length; j++) {
                    imagensReconstruidas[j][i] += eigenfaces[j][p] * pesos[p][i];
                }
            }
        }
        return imagensReconstruidas;
    }

    public static void escreverArrayComoImagem(double[][] array, String caminhoOutput) throws IOException {
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

    public static double[][] reescalonarEigenfaces(double[][] eigenfaceMatrix) {
        int rows = eigenfaceMatrix.length;    // Número de linhas (altura das imagens)
        int cols = eigenfaceMatrix[0].length; // Número de colunas (número de eigenfaces)

        // Criar uma nova matriz para armazenar as eigenfaces ajustadas
        double[][] normalizedMatrix = new double[rows][cols];

        for (int col = 0; col < cols; col++) {
            // Extrair a coluna atual (uma eigenface)
            double[] eigenface = new double[rows];
            for (int row = 0; row < rows; row++) {
                eigenface[row] = eigenfaceMatrix[row][col];
            }

            // Verificar se os valores estão fora do intervalo [0, 255]
            double minVal = findMin(eigenface);
            double maxVal = findMax(eigenface);

            if (minVal < 0 || maxVal > 255) {
                // Se os valores não estiverem entre [0, 255], normalizar
                eigenface = normalizeTo255(eigenface, minVal, maxVal);
            }

            // Inserir a eigenface (ajustada ou não) na matriz final
            for (int row = 0; row < rows; row++) {
                normalizedMatrix[row][col] = eigenface[row];
            }
        }

        return normalizedMatrix;
    }

    private static double[] normalizeTo255(double[] array, double minVal, double maxVal) {
        double[] normalizedArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            normalizedArray[i] = 255 * (array[i] - minVal) / (maxVal - minVal);
        }
        return normalizedArray;
    }

    public static double findMin(double[] array) {
        double min = Double.MAX_VALUE;
        for (double val : array) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    public static double findMax(double[] array) {
        double max = Double.MIN_VALUE;
        for (double val : array) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    public static int[] compararMatrizes(double[][] matriz1, double[][] matriz2) {
        // Verificar se as matrizes têm dimensões compatíveis
        if (matriz1.length != matriz2.length || matriz1[0].length != matriz2[0].length) {
            throw new IllegalArgumentException("As matrizes devem ter as mesmas dimensões.");
        }

        int rows = matriz1.length; // Número de linhas
        int cols = matriz1[0].length; // Número de colunas

        // Criar um array para armazenar os índices das colunas diferentes
        int[] colunasDiferentes = new int[cols];
        int contador = 0; // Contador para o número de colunas diferentes encontradas

        // Comparar cada coluna
        for (int col = 0; col < cols; col++) {
            boolean diferente = false; // Flag para identificar se a coluna é diferente

            for (int row = 0; row < rows; row++) {
                if (matriz1[row][col] != matriz2[row][col]) {
                    diferente = true;
                    break; // Parar de verificar a coluna, pois já sabemos que é diferente
                }
            }

            // Se a coluna for diferente, armazenar o índice no array
            if (diferente) {
                colunasDiferentes[contador] = col;
                contador++;
            }
        }

        // Criar um novo array com o tamanho exato das colunas diferentes
        int[] resultado = new int[contador];
        for (int i = 0; i < contador; i++) {
            resultado[i] = colunasDiferentes[i];
        }

        return resultado; // Retornar os índices das colunas diferentes
    }

    public static boolean contemIndice(int[] indices, int coluna) {
        for (int indice : indices) {
            if (indice == coluna) {
                return true;
            }
        }
        return false;
    }


    //---------- Funcionalidade 3 ----------

    //Cálculo da distância euclidiana
    public static double[] calcularDistanciaEuclidianaMatriz(double[][] omegaNova, double[][] omegaJ) {
        int numImagens = omegaJ[0].length;
        double[] distancias = new double[numImagens];

        for (int colunas = 0; colunas < numImagens; colunas++) {
            double soma = 0;
            for (int linhas = 0; linhas < omegaNova.length; linhas++) {
                soma += Math.pow(omegaNova[linhas][0] - omegaJ[linhas][colunas], 2);
            }
            distancias[colunas] = Math.sqrt(soma);
        }
        return distancias;
    }

    //Obter a menor de todas as distâncias calculadas
    public static int obterIndiceMenorDistancia(double[] distancias) {
        double menorValor = distancias[0];
        int menorIndice = 0;
        boolean menorIgual = false;

        for(int i = 1; i < distancias.length; i++) {
            if(distancias[i] <= menorValor) {
                if(distancias[i] == menorValor) {
                    menorIgual = true;
                } else {
                    menorIgual = false;
                    menorValor = distancias[i];
                    menorIndice = i;
                }
            }
        }

        if(menorIgual) {
            return -1;
        } else {
            return menorIndice;
        }
    }

    public static double[][] obterImagem(double[][] matrizImagens, int indice) {
        double[][] imagem = new double[matrizImagens.length][1];
        for (int i = 0; i < matrizImagens.length; i++) {
            imagem[i][0] = matrizImagens[i][indice];
        }
        return imagem;
    }

    public static double[][] gerarImagem(double[][] media, double[][] valoresProprios, double[][] vetoresPropriosNormalizados, int k) {
        double min;
        double max;
        double w ;

        double[][] imagemGerada = new double[media.length][1];
        double[][] mult = new double[media.length][1];

        for(int i = 0; i < media.length; i++) {
            imagemGerada[i][0] = media[i][0];
        }

        for(int i = 0; i < k; i++) {
            min = -(Math.sqrt(valoresProprios[i][i]));
            max = Math.sqrt(valoresProprios[i][i]);

            w = Math.random() * (max - min) + min;

            for(int j = 0; j < media.length; j++) {
                mult[j][0] = w * vetoresPropriosNormalizados[j][i];
            }

            for(int p = 0; p < media.length; p++) {
                imagemGerada[p][0] += mult[p][0];
            }
        }

        return  imagemGerada;
    }
}
