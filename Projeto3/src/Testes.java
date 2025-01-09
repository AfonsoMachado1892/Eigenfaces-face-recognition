import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Testes {
    public static void main(String[] args) {

        //Funcionalidade 1
        //testarMatrizVetoresProprios();
        //testarMatrizValoresProprios();
        testarCalcularTransposta();
        testarMultiplicarMatrizes();
        testarCalcularA();
        testarCalcularEAM();

         testarCalcularAk();
        testarVetoresPropriosReduzidos();
        testarMatrizDiagonalReduzida();
        testarVerificarMatriz();


        //Funcionalidade 2
        testarCalcularMedia();
        testarCalcularPhi();
        testarNormalizarVetoresPropriosC();
        /*
        testarVetoresPropriosC();
        testarCalcularPesos();
        testarReconstruirTodasImagens();
        testarEscreverArrayComoImagem();

        //Funcionalidade 3
        testarCalcularDistanciaEuclidianaMatriz();
        testarObterIndiceMenorDistancia();
        */

    }

    //---------- Funcionalidade 1 ----------
    /*public static void testarMatrizVetoresProprios() {
        boolean valido = true;

        double[][] matriz = {
                {1, 2, 5},
                {0, 6, 3},
                {0, 0, 7}
        };

        double[][] esperado = {
                {1, (double) 2/5, (double) 11/6},
                {0, 1, 3},
                {0, 0, 1}
        };

        double[][] resultado = calcularMatrizes.matrizVetoresProprios(matriz);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Vetores Próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Vetores Próprios INVALIDADO!");
        };
    }*/


    /*public static void testarMatrizValoresProprios() {
        boolean valido = true;

        double[][] matriz = {
                {1, 2, 5},
                {0, 6, 3},
                {0, 0, 7}
        };

        double[][] esperado = {
                {1, 0, 0},
                {0, 6, 0},
                {0, 0, 7}
        };

        double[][] resultado = calcularMatrizes.matrizDiagonalValoresProprios(matriz);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Matriz Diagonal de Valores Próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz Diagonal de Valores Próprios INVALIDADO!");
        };
    }*/

    public static void testarCalcularTransposta() {
        boolean valido = true;

        double[][] matriz = {
                {9, 7, 3, 2, 6, 7, 6},
                {1, 1, 6, 6, 3, 7, 5},
                {1, 3, 8, 1, 2, 1, 7},
                {4, 3, 8, 9, 4, 4, 1},
                {6, 8, 2, 9, 6, 8, 8},
                {5, 6, 9, 9, 7, 3, 5},
                {9, 4, 5, 8, 2, 1, 9}
        };

        double[][] esperado = {
                {9, 1, 1, 4, 6, 5, 9},
                {7, 1, 3, 3, 8, 6, 4},
                {3, 6, 8, 8, 2, 9, 5},
                {2, 6, 1, 9, 9, 9, 8},
                {6, 3, 2, 4, 6, 7, 2},
                {7, 7, 1, 4, 8, 3, 1},
                {6, 5, 7, 1, 8, 5, 9}
        };

        double[][] resultado = calcularMatrizes.calcularTransposta(matriz);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Matriz Transposta validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz Transposta INVALIDADO!");
        };
    }

    public static void testarCalcularEAM() {
        boolean valido = true;

        double[][] matrizA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[][] matrizAk = {
                {2, 3, 4},
                {5, 6, 7},
                {8, 9, 10}
        };

        double esperado = 1;

        double resultado = calcularMatrizes.calcularErroAbsolutoMedio(matrizA, matrizAk);

        if(esperado != resultado) {
            valido = false;
        }

        if(valido) {
            System.out.println("Teste de cálculo de Erro Absoluto Médio validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Erro Absoluto Médio INVALIDADO!");
        };
    }
    public static void testarCalcularAk() {
        boolean valido = true;

        double[][] matrizDiagonalReduzida = {
                {3, 0},
                {0, 2}
        };

        double[][] vetoresPropriosReduzidos = {
                {0.8, -0.6},
                {0.6, 0.8}
        };

        double[][] esperado = {
                {2.64, 0.48},
                {0.48, 2.36}
        };

        double[][] resultado = calcularMatrizes.calcularAk(matrizDiagonalReduzida, vetoresPropriosReduzidos);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if (Math.abs(esperado[i][j] - resultado[i][j]) > 0.01) {
                    valido = false;
                }
            }
        }

        if (valido) {
            System.out.println("Teste de cálculo de Ak validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Ak INVALIDADO!");
        }
    }


    public static void testarVetoresPropriosReduzidos() {
        boolean valido = true;

        double[][] vetoresProprios = {
                {1, 0.5, 0.3},
                {0.5, 1, 0.7},
                {0.3, 0.7, 1}
        };

        int k = 2;

        double[][] esperado = {
                {1, 0.5},
                {0.5, 1},
                {0.3, 0.7}
        };

        double[][] resultado = calcularMatrizes.vetoresPropriosReduzidos(vetoresProprios, k);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if (Math.abs(esperado[i][j] - resultado[i][j]) > 0.01) {
                    valido = false;
                }
            }
        }

        if (valido) {
            System.out.println("Teste de cálculo de Vetores Próprios Reduzidos validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Vetores Próprios Reduzidos INVALIDADO!");
        }
    }



    public static void testarMatrizDiagonalReduzida() {
        boolean valido = true;

        double[][] matrizDiagonal = {
                {3, 0, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 7, 0},
                {0, 0, 0, 2}
        };

        int k = 2;

        double[][] esperado = {
                {3, 0},
                {0, 5}
        };

        double[][] resultado = calcularMatrizes.matrizDiagonalReduzida(matrizDiagonal, k);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if ((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if (valido) {
            System.out.println("Teste de matriz diagonal reduzida validado com sucesso!");
        } else {
            System.out.println("Teste de matriz diagonal reduzida INVALIDADO!");
        }
    }
    
    public static void testarVerificarMatriz() {
        boolean valido = true;

        // Matriz Válida (Quadrada, Simétrica, Menor que 256x256, Apenas números reais)
        double[][] matrizValida = {
                {4, 2, 3},
                {2, 5, 1},
                {3, 1, 6}
        };

        // Matriz Não Simétrica (Deve ser inválida)
        double[][] matrizNaoSimetrica = {
                {4, 2, 3},
                {3, 5, 1}, // O 3 na posição [1][0] quebra a simetria
                {3, 1, 6}
        };

        // Matriz Inválida (Com NaN e Infinito)
        double[][] matrizInvalida = {
                {4, 2, 3},
                {2, Double.NaN, 1},
                {3, 1, Double.POSITIVE_INFINITY}
        };

        // Matriz Inválida (Não é Quadrada)
        double[][] matrizNaoQuadrada = {
                {1, 2, 3},
                {4, 5, 6}
        };

        // Teste Matriz Válida (Deve ser TRUE)
        if (!calcularMatrizes.verificarMatriz(matrizValida)) {
            valido = false;
            System.out.println("ERRO: Matriz válida foi considerada inválida!");
        }

        // Teste Matriz Não Simétrica (Deve ser FALSE)
        if (calcularMatrizes.verificarMatriz(matrizNaoSimetrica)) {
            valido = false;
            System.out.println("ERRO: Matriz não simétrica foi considerada válida!");
        }

        // Teste Matriz Inválida com NaN e Infinito (Deve ser FALSE)
        if (calcularMatrizes.verificarMatriz(matrizInvalida)) {
            valido = false;
            System.out.println("ERRO: Matriz com valores NaN/Infinito foi considerada válida!");
        }

        // Teste Matriz Não Quadrada (Deve ser FALSE)
        if (calcularMatrizes.verificarMatriz(matrizNaoQuadrada)) {
            valido = false;
            System.out.println("ERRO: Matriz não quadrada foi considerada válida!");
        }

        // Exibir resultado final do teste
        if (valido) {
            System.out.println("Teste de verificação de matriz validado com sucesso!");
        } else {
            System.out.println("Teste de verificação de matriz INVALIDADO!");
        }
    }



    public static void testarMultiplicarMatrizes() {
        boolean valido = true;

        double[][] matriz1 = {
                {1,123,123145,123,12,3124,1243,1245,346,323},
                {4567,534,5768,23,1,2356,23,655,34,235},
        };

        double[][] matriz2 = {
                {12},
                {1245},
                {123},
                {468},
                {6796},
                {6784},
                {23486},
                {12},
                {356},
                {1}
        };

        double[][] esperado = {
                {65963851},
                {17990139}
        };

        double[][] resultado = calcularMatrizes.multiplicarMatrizes(matriz1, matriz2);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Multiplicação de Matrizes validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Multiplicação de Matrizes INVALIDADO!");
        };
    }

    public static void testarCalcularA() {
        boolean valido = true;

        double[][] matrizP = {
                {1, (double) 2/5, (double) 11/6},
                {0, 1, 3},
                {0, 0, 1}
        };

        double[][] matrizD = {
                {1, 0, 0},
                {0, 6, 0},
                {0, 0, 7}
        };

        double[][] esperado = {
                {(double) 22939 / 900, (double) 409 / 10, (double) 77 / 6},
                {(double) 409 / 10, 69, 21},
                {(double) 77 / 6, 21, 7}
        };

        double[][] resultado = calcularMatrizes.calcularA(matrizD, matrizP);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Matriz A (Decomposição Própria de Matriz Simétrica) validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz A (Decomposição Própria de Matriz Simétrica) INVALIDADO!");
        };
    }


    //---------- Funcionalidade 2 ----------
    public static void testarNormalizarVetoresPropriosC() {
        boolean valido = true;

        double[][] matriz = {
                {1, 4, 0},
                {6, 0, 2},
                {2, 0, 3}
        };

        double[][] esperado = {
                {(double) 1/Math.sqrt(41), 1 , 0},
                {(double) 6/Math.sqrt(41), 0, (double) 2/Math.sqrt(13)},
                {(double) 2/Math.sqrt(41), 0, (double) 3/Math.sqrt(13)}
        };

        double[][] resultado = calcularMatrizes.normalizarVetoresPropriosC(matriz);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Vetores Próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Vetores Próprios INVALIDADO!");
        };
    }

    public static void testarCalcularMedia() {
        boolean valido = true;

        double[][] matriz = {
                {3, 5, 1},
                {2, 0, 6},
                {0, 7, 4}
        };

        double[][] esperado = {
                {3},
                {(double) 8/3},
                {(double) 11/3},
        };

        double[][] resultado = calcularMatrizes.calcularMedia(matriz);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Vetores Próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Vetores Próprios INVALIDADO!");
        };
    }

    public static void testarCalcularPhi() {
        boolean valido = true;

        double[][] matrizA = {
                {1, 2, 3},
                {3, 2, 1},
                {6, 0, 4}
        };

        double[][] matrizB = {
                {3},
                {1},
                {2},
        };

        double[][] esperado = {
                {-2, -1, 0},
                {2, 1, 0},
                {4, 0, 2}
        };

        double[][] resultado = calcularMatrizes.calcularPhi(matrizA, matrizB);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Vetores Próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Vetores Próprios INVALIDADO!");
        };
    }

    /*
    public static boolean testarVerificarMatriz() {

    }

    public static boolean testarVetoresPropriosReduzidos() {

    }

    public static boolean testarMatrizDiagonalReduzida() {

    }

    public static boolean testarCalcularAk() {

    }

    public static boolean testarNormalizarVetoresProprios() {

    }

    public static boolean testarCalcularMedia() {

    }

    public static boolean testarCalcularPhi() {

    }

    public static boolean testarVetoresPropriosC() {

    }

    public static boolean testarCalcularPesos() {

    }

    public static boolean testarReconstruirTodasImagens() {

    }

    public static boolean testarEscreverArrayComoImagem() {

    }*/

    //---------- Funcionalidade 3 ----------
}
