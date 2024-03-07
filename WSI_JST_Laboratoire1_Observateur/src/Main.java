import View.MainWindow;

public class Main {

    public static void main(String[] args) {
        MainWindow window = new MainWindow(Integer.parseInt(args[0]));
        window.pack();
    }
}