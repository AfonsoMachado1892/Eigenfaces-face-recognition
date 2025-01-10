public class Testes {
    public static void main(String[] args) {

        //Funcionalidade 1
        /*
        testarMatrizVetoresProprios();
        testarMatrizValoresProprios();
        */
        testarCalcularTransposta();
        testarMultiplicarMatrizes();
        testarCalcularA();
        testarCalcularAk(); //CORRIGIR
        testarCalcularEAM();
        testarVetoresPropriosReduzidos();
        testarMatrizDiagonalReduzida();
        testarVerificarMatriz();

        //Funcionalidade 2
        testarCalcularMedia();
        testarCalcularPhi();
        testarNormalizarVetoresPropriosC();
        testarCalcularPesos();
        testarReconstruirTodasImagens();
        /*
        testarFindMin();
        testarFindMax();
        testarReescalonarEigenfaces();
        testarCompararMatrizes();
        */

        //Funcionalidade 3
        testarCalcularDistanciaEuclidianaMatriz();

        //Funcionalidade 4
        testarGerarImagens(); //CORRIGIR
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
                if ((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Matriz Transposta validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz Transposta INVALIDADO!");
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
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Multiplicação de Matrizes validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Multiplicação de Matrizes INVALIDADO!");
        }
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
                {(double) 22939/900, (double) 409/10, (double) 77/6},
                {(double) 409/10, 69, 21},
                {(double) 77/6, 21, 7}
        };

        double[][] resultado = calcularMatrizes.calcularA(matrizD, matrizP);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Matriz A (Decomposição Própria de Matriz Simétrica) validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz A (Decomposição Própria de Matriz Simétrica) INVALIDADO!");
        }
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
        }
    }

    public static void testarCalcularAk() {
        boolean valido = true;

        double[][] matrizVetoresPropriosReduzidos = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        double[][] matrizDiagonalReduzida = {
                {3, 0, 0},
                {0, 5, 0},
                {0, 0, 7}
        };

        double[][] esperado = {
                {3, 0, 0},
                {0, 5, 0},
                {0, 0, 7}
        };

        double[][] resultado = calcularMatrizes.calcularAk(matrizVetoresPropriosReduzidos, matrizDiagonalReduzida);

        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if ((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if (valido) {
            System.out.println("Teste de cálculo de Matriz Ak validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz Ak INVALIDADO!");
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
                if ((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
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
                    break;
                }
            }
        }

        if (valido) {
            System.out.println("Teste de cálculo de Matriz Diagonal Reduzida validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Matriz Diagonal Reduzida INVALIDADO!");
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
            System.out.println("Teste de verificação de Matriz validado com sucesso!");
        } else {
            System.out.println("Teste de verificação de Matriz INVALIDADO!");
        }
    }

    //---------- Funcionalidade 2 ----------
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
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Calcular Média validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Calcular Média INVALIDADO!");
        }
    }

    public static void testarCalcularPhi() {
        boolean valido = true;

        double[][] matriz = {
                {1, 2, 3},
                {3, 2, 1},
                {6, 0, 4}
        };

        double[][] media = {
                {3},
                {1},
                {2},
        };

        double[][] esperado = {
                {-2, -1, 0},
                {2, 1, 0},
                {4, -2, 2}
        };

        double[][] resultado = calcularMatrizes.calcularPhi(matriz, media);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Calcular Phi validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Calcular Phi INVALIDADO!");
        }
    }

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
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Normalizar Vetores Próprios de C validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Normalizar Vetores Próprios de C INVALIDADO!");
        }
    }

    public static void testarCalcularPesos() {
        boolean valido = true;

        double[][] matrizA = {
                {1, 3, 6},
                {0, 1, 3},
                {0, 0, 1}
        };

        double[][] matrizEigenfaces = {
                {1, 2, 5},
                {0, 6, 3},
                {0, 0, 7}
        };

        double[][] esperado = {
                {1, 3, 6},
                {2, 12, 30},
                {5, 18, 46}
        };

        int k = 3;

        double[][] resultado = calcularMatrizes.calcularPesos(matrizA, matrizEigenfaces, k);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Calcular Pesos validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Calcular Pesos INVALIDADO!");
        }
    }

    public static void testarReconstruirTodasImagens() {
        boolean valido = true;

        double[][] media = {
                {1},
                {5},
                {2}
        };

        double[][] eigenfaces = {
                {1, 4, 3},
                {0, 3, 2},
                {0, 0, 5}
        };

        double[][] pesos = {
                {1, 3, 6},
                {0, 1, 3},
                {0, 0, 4}
        };

        int k = 3;

        double[][] esperado = {
                {2, 8, 31},
                {5, 8, 22},
                {2, 2, 22}
        };

        double[][] resultado = calcularMatrizes.reconstruirTodasImagens(media, eigenfaces, pesos, k);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Reconstruir Todas as Imagens validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Reconstruir Todas as Imagens INVALIDADO!");
        }
    }

    //---------- Funcionalidade 3 ----------
    public static void testarCalcularDistanciaEuclidianaMatriz() {
        boolean valido = true;

        double[][] omegaNova = {
                {1},
                {3},
                {0}
        };

        double[][] omegaJ = {
                {1, 5, 3},
                {0, 2, 4},
                {0, 0, 6}
        };

        double[][] esperado = {
                {3, Math.sqrt(17), Math.sqrt(41)},
        };

        double[][] resultado = new double[][]{calcularMatrizes.calcularDistanciaEuclidianaMatriz(omegaNova, omegaJ)};

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Distância Euclidiana validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Distância Euclidiana INVALIDADO!");
        }
    }

    //---------- Funcionalidade 4 ----------
    public static void testarGerarImagens() {
        boolean valido = true;

        double[][] media = {
                {1},
                {3},
                {2}
        };

        double[][] valoresProprios = {
                {1, 2, 3},
                {0, 6, 2},
                {0, 0, 5}
        };

        double[][] vetoresPropriosNormalizados = {
                {1, 5, 2},
                {0, 3, 4},
                {0, 0, 2}
        };

        int k = 3;

        double[][] esperado = {
                {2.63},
                {8.69},
                {5.58}
        };

        double[][] resultado = calcularMatrizes.gerarImagem(media, valoresProprios, vetoresPropriosNormalizados, k);

        for(int i = 0; i < resultado.length; i++) {
            for(int j = 0; j < resultado[0].length; j++) {
                if((int) esperado[i][j] != (int) resultado[i][j]) {
                    valido = false;
                    break;
                }
            }
        }

        if(valido) {
            System.out.println("Teste de cálculo de Geração de Imagem validado com sucesso!");
        } else {
            System.out.println("Teste de cálculo de Geração de Imagem INVALIDADO!");
        }
    }
}