import application.configuration.Config;
import application.userinterface.MenuApp;

public class Main {

    public static void main(String[] args) {
        try {
            MenuApp menuApp = Config.createMenuApp();
            menuApp.showMainMenu();
        } catch (Exception e) {
            System.out.println("Error al iniciar la aplicacion: " + e.getMessage());
            System.out.println("Verifica que MySQL este corriendo y la contrasena en DataBaseConnectionMySql sea correcta.");
        }
    }
}
