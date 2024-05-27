import com.google.gson.Gson;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMonedas consulta = new ConsultarMonedas();
        HistorialConversiones historial = new HistorialConversiones();

        Menu menu = new Menu(lectura, consulta, historial);
        menu.mostrarMenuPrincipal();
    }
}