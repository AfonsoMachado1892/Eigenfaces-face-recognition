import java.io.FileNotFoundException;

public class Projeto3 {
    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;

        while (opcao != 4) {
            UI.mostrarMenu();
            opcao = UI.obterOpcao();
            UI.executarOpcao(opcao);
        }

        //F1: src\inputs\Funcionalidade1\exampleInputFunc1.csv
        //F2: src\inputs\Funcionalidades 2 e 3\exemploMatrizesImagensCSVFuncionalidades2e3\exemploMatrizesImagensCSVFuncionalidades2e3
    }
}
