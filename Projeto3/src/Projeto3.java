import java.io.FileNotFoundException;

public class Projeto3 {
    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;

        while (opcao != 4) {
            UI.mostrarMenu();
            opcao = UI.obterOpcao();
            UI.executarOpcao(opcao);
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
        */
    }
}
