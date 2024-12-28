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

        /*
        testarCalcularEAM();
        testarMultiplicarMatrizes();
        testarCalcularA();
        testarVerificarMatriz();
        testarVetoresPropriosReduzidos();
        testarMatrizDiagonalReduzida();
        testarCalcularAk();

        testarNormalizarVetoresProprios();
        testarCalcularMedia();
        testarCalcularPhi();
        testarVetoresPropriosC();
        testarCalcularPesos();
        testarReconstruirTodasImagens();
        testarEscreverArrayComoImagem();*/
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
                if(esperado[i][j] != resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de vetores próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de vetores próprios INVALIDADO!");
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
                if(esperado[i][j] != resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de matriz diagonal de valores próprios validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de matriz diagonal de valores próprios INVALIDADO!");
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
                if(esperado[i][j] != resultado[i][j]) {
                    valido = false;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de matriz transposta validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de matriz transposta INVALIDADO!");
        };
    }

    /*public static boolean testarCalcularEAM() {

    }

    public static boolean testarMultiplicarMatrizes() {

    }

    public static boolean testarCalcularA() {

    }

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
