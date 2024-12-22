import java.io.FileNotFoundException;

public class Projeto3 {
    public static void main(String[] args) throws FileNotFoundException {
        int opcao = 0;

        while (opcao != 4) {
            UI.mostrarMenu();
            opcao = UI.obterOpcao();
            UI.executarOpcao(opcao);
        }
    }
}
