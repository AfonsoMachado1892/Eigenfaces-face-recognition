import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import org.apache.commons.math3.linear.*;

public class Projeto3 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;
        double matriz[][];

        while (opcao != 4) {
            System.out.println("=========== Menu ==========");
            System.out.println("1. Decomposição Própria de Matriz Simétrica");
            System.out.println("2. Reconstrução de Imagens Utilizando Eigenfaces");
            System.out.println("3. Identificação de Imagem Mais Próxima");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    matriz = lerMatriz();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("A sair...");
                    return;
                default:
                    System.out.println("Input Inválido. [1-4]");
            }
        }
    }

    public static double[][] lerMatriz() throws FileNotFoundException {
        //src/inputs/Funcionalidade1/exampleInputFunc1.csv

        System.out.println("Insira o diretório para o ficheiro: ");
        String diretorio = sc.nextLine();
        File file = new File(diretorio);

        Scanner in = new Scanner(file);
        int cont = 0;
        int linhas;

        while(in.hasNextLine()) {
            cont++;
            in.nextLine();
        }

        linhas = cont - 1;

        in = new Scanner(file);

        double[][] matriz = new double[linhas][linhas];

        for(int i = 0; i < linhas; i++) {
            String[] valores = in.nextLine().split(",");
            for(int j = 0; j < linhas; j++) {
                matriz[i][j] = Double.parseDouble(valores[j]);
            }
        }

        in.close();

        return matriz;
    }

    public static double[][] matrizVetoresProprios(double[][] matriz) {
        RealMatrix matrizVetoresProprios = new Array2DRowRealMatrix(matriz);

        EigenDecomposition decomposicao = new EigenDecomposition(matrizVetoresProprios);

        double[][] vetoresproprios = new double[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            double[] vetorProprio = decomposicao.getEigenvector(i).toArray();
            for (int j = 0; j < matriz.length; j++) {
                vetoresproprios[j][i] = vetorProprio[j];
            }
        }

        return vetoresproprios;
    }

    public static boolean eSimetrica(double[][] matriz) {
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz.length; j++) {
                if(matriz[i][j] != matriz[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
