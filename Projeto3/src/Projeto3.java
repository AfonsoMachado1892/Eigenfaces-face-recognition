import java.io.FileNotFoundException;

public class Projeto3 {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            System.out.println("Modo Interativo Iniciado.");
            executarModoInterativo();
        } else {
            System.out.println("Modo Não Interativo Iniciado.");
            executarModoNaoInterativo(args);
        }

         /*
        F1: src\inputs\Funcionalidade1\exampleInputFunc1.csv
        F2: src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3

        F3:
        Imagens:
        Imagem 38:     src\inputs\Funcionalidade 3\exemploImagensInputFuncionalidade3\exemploImagensInputFuncionalidade3\image_038.csv
        Imagem 78:     src\inputs\Funcionalidade 3\exemploImagensInputFuncionalidade3\exemploImagensInputFuncionalidade3\image_078.csv
        Imagem 140:    src\inputs\Funcionalidade 3\exemploImagensInputFuncionalidade3\exemploImagensInputFuncionalidade3\image_140.csv
        Imagem 213:    src\inputs\Funcionalidade 3\exemploImagensInputFuncionalidade3\exemploImagensInputFuncionalidade3\image_213.csv
        Base Imagens - src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3

        F4: src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3
        */

        /*------------------------------*/

        /*
        EXECUTAR APP:
        - MODO INTERATIVO: java -jar out\artifacts\Projeto3_jar\Projeto3.jar
        - MODO NÃO INTERATIVO:
        1: java -jar out\artifacts\Projeto3_jar\Projeto3.jar -f 1 -k 5 -i "src\inputs\Funcionalidade1\exampleInputFunc1.csv"
        2: java -jar out\artifacts\Projeto3_jar\Projeto3.jar -f 2 -k 40 -d "src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3"
        3: java -jar out\artifacts\Projeto3_jar\Projeto3.jar -f 3 -k 10 -i "src\inputs\Funcionalidade 3\exemploImagensInputFuncionalidade3\exemploImagensInputFuncionalidade3\image_213.csv" -d "src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3"
        4: java -jar out\artifacts\Projeto3_jar\Projeto3.jar -f 4 -k 40 -d "src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3"

         */
    }

    public static void executarModoInterativo() throws FileNotFoundException {
        int opcao;
        do {
            UI.mostrarMenu();
            opcao = UI.obterOpcao();
            UI.executarOpcao(opcao);
        } while (opcao != 5);
    }

    private static void executarModoNaoInterativo(String[] args) throws FileNotFoundException {
        int f = -1;
        int k = -1;
        String i = null;
        String d = null;

        for (int j = 0; j < args.length; j++) {
            switch (args[j]) {
                case "-f":
                    f = Integer.parseInt(args[++j]);
                    break;
                case "-k":
                    k = Integer.parseInt(args[++j]);
                    break;
                case "-i":
                    i = args[++j];
                    break;
                case "-d":
                    d = args[++j];
                    break;
                default:
                    System.out.println("Parâmetro inválido: " + args[j]);
                    return;
            }
        }

        if (f < 1 || f > 4) {
            System.out.println("Erro: A funcionalidade (f) deve ser 1, 2, 3 ou 4.");
            return;
        }
        if ((f == 1 || f == 3) && i == null) {
            System.out.println("Erro: O parâmetro '-i' é obrigatório para as funcionalidades 1 e 3.");
            return;
        }
        if ((f == 2 || f == 3 || f == 4) && d == null) {
            System.out.println("Erro: O parâmetro '-d' é obrigatório para as funcionalidades 2, 3 e 4.");
            return;
        }

        switch (f) {
            case 1:
                System.out.println("A executar Funcionalidade 1...");
                UI.decomposicaoPropria(k, i);
                break;
            case 2:
                System.out.println("A executar Funcionalidade 2...");
                UI.reconstruirImagens(k, d);
                break;
            case 3:
                System.out.println("A executar Funcionalidade 3...");
                UI.identificarImagem(k, i, d);
                break;
            case 4:
                System.out.println("A executar Funcionalidade 4...");
                UI.gerarNovasImagens(d, k);
                break;
            default:
                System.out.println("Erro: Funcionalidade inválida.");
        }
    }

}


