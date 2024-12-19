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

        int linhas = 0;
        int colunas = 0;

        while (in.hasNextLine()) {
            String linha = in.nextLine();
            if (!linha.trim().isEmpty()) {
                linhas++;
                if (colunas == 0) {
                    colunas = linha.split(",").length;
                }
            }
        }

        double[][] matriz = new double[linhas][colunas];

        in = new Scanner(file);
        for (int i = 0; i < linhas; i++) {
            String[] valores = in.nextLine().split(",");
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = Double.parseDouble(valores[j]);
            }
        }

        in.close();
        return matriz;
    }


    public static void exibirMatriz(double[][] matriz, String nomeMatriz, int casasDecimais) {
        System.out.println(nomeMatriz + ":");
        String formato = "%." + casasDecimais + "f ";

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.printf(formato, matriz[i][j]);
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

    public static void lerVetoresDeImagens(String caminho, String ficheiroOutput) throws FileNotFoundException {
        double[][] matriz;
        double[] vetor;
        int cont;

        File[] ficheiros = new File(caminho).listFiles();

        try (PrintWriter printwriter = new PrintWriter(new File(ficheiroOutput))) {
            for (File f : ficheiros) {
                String filePath = f.getAbsolutePath();
                String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);

                if ("csv".equals(fileExtension)) {
                    matriz = lerMatriz(filePath);

                    vetor = new double[matriz.length * matriz[0].length];
                    cont = 0;

                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz[i].length; j++) {
                            vetor[cont] = matriz[i][j];
                            cont++;
                        }
                    }
                    for (int i = 0; i < vetor.length; i++) {
                        printwriter.printf("%.0f", vetor[i]);
                        if (i == vetor.length - 1) {
                            printwriter.println();
                        } else {
                            printwriter.print(", ");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao guardar ficheiro: " + e.getMessage());
        }
    }
}
