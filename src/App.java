import java.io.IOException;

public class App {
    public static void ClearConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public static void main(String[] args) throws Exception {
        ClearConsole();

        BoardCreator bc = new BoardCreator();
        bc.CreateStartingBoard();
    }
}
