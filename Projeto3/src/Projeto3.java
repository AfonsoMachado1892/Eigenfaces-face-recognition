import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Projeto3 {
    public static void main(String[] args) throws FileNotFoundException {
        int[][] matrix;

        matrix = lerMatriz();
    }

    public static int[][] lerMatriz() throws FileNotFoundException {
        File file = new File("src/inputs/Funcionalidade1/exampleInputFunc1.csv");

        Scanner sizesc = new Scanner(file);

        int size = 0;

        while (sizesc.hasNextLine()) {
            size++;
            sizesc.nextLine();
        }

        size = size - 1;

        int[][] matrix = new int[size][size];

        Scanner in = new Scanner(file);

        for (int i = 0; i < size; i++) {
            String[] valores = in.nextLine().split(",");
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(valores[j]);
            }
        }

        in.close();

        return matrix;
    }
}
