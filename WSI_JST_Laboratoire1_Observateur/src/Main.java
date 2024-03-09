import View.MainWindow;

/**
 * -----------------------------------------------------------------------------------
 * @Labo         : Labo 01 : Observateur
 * @Authors      : Slimani Walid & Steiner Jeremiah
 * @Description  : Ce programme vise à implémenter le modèle de concéption réutilisable
 *                 de l'observateur. L'application possède un GUI permettant de gérer
 *                 des chronomètres et d'en afficher la valeur sous différentes représentations.
 *
 * @Remarque     : /
 * @Modification : /
 * -----------------------------------------------------------------------------------
 **/

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow(Integer.parseInt(args[0]));
        window.pack();
    }
}