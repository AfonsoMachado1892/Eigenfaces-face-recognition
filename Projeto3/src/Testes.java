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
        testarMatrizVetoresProprios();
        testarMatrizValoresProprios();
        testarCalcularTransposta();
        testarCalcularEAM();
        testarMultiplicarMatrizes();
        testarCalcularA();
        /*
        testarVerificarMatriz();
        testarVetoresPropriosReduzidos();
        testarMatrizDiagonalReduzida();
        testarCalcularAk();*/

        //Funcionalidade 2
        testarNormalizarVetoresProprios();
        /*
        testarCalcularMedia();
        testarCalcularPhi();
        testarVetoresPropriosC();
        testarCalcularPesos();
        testarReconstruirTodasImagens();
        testarEscreverArrayComoImagem();

        //Funcionalidade 3
        testarCalcularDistanciaEuclidianaMatriz();
        testarConstruirVetoresPesos();
        testarObterIndiceMenorDistancia();
        */

    }

    public static void testarMatrizVetoresProprios() {
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
    }


    public static void testarMatrizValoresProprios() {
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
    }

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

    public static void testarNormalizarVetoresProprios() {
        boolean valido = true;

        double[][] matriz = {
                {1, 3, 5},
                {7, 0, 2},
                {2, 3, 4}
        };

        double[][] esperado = {
                {1, (double) 2/5, (double) 11/6},
                {0, 1, 3},
                {0, 0, 1}
        };

        double[][] resultado = calcularMatrizes.normalizarVetoresProprios(matriz);

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
}
