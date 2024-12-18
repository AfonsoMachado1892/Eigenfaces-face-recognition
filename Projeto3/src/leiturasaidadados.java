import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class leiturasaidadados {

    public static double[][] lerMatriz(String diretorio) throws FileNotFoundException {
        //src/inputs/Funcionalidade1/exampleInputFunc1.csv
        File file = new File(diretorio);
        Scanner in = new Scanner(file);
        int cont = 0;
        int linhas;

        while (in.hasNextLine()) {
            cont++;
            in.nextLine();
        }

        linhas = cont - 1;

        in = new Scanner(file);

        double[][] matriz = new double[linhas][linhas];

        for (int i = 0; i < linhas; i++) {
            String[] valores = in.nextLine().split(",");
            for (int j = 0; j < linhas; j++) {
                matriz[i][j] = Double.parseDouble(valores[j]);
            }
        }

        in.close();

        return matriz;
    }

    public static void exibirMatriz(double[][] matriz, String nomeMatriz) {
        System.out.println(nomeMatriz + ":");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void guardarMatriz (double[][] Ak, String diretorio, String outputFolder) {
        String nomeCSV = new File(diretorio).getName();
        String nomeCSVreconstruido = "reconstruido" + nomeCSV;
        String caminho = outputFolder + "/" + nomeCSVreconstruido;
        File ficheiro = new File(caminho);
        int contador = 1;

        while (ficheiro.exists()) {
            ficheiro = new File(outputFolder + "/" + nomeCSVreconstruido.replace(".csv", "(" + contador + ").csv"));
            contador++;
        }

        try (PrintWriter printwriter = new PrintWriter(ficheiro)) {
            for (int i = 0; i < Ak.length; i++) {
                for (int j = 0; j < Ak[i].length; j++) {
                    printwriter.printf("%.2f ", Ak[i][j]);
                    if(j == Ak[i].length - 1) {
                        printwriter.printf("");
                    } else {
                        printwriter.print("; ");
                    }
                }
                printwriter.println();
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar ficheiro CSV: " + e.getMessage());
        }
    }
}
