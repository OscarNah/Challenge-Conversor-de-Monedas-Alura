import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversiones {
    private List<String> historial;

    public HistorialConversiones(){
        historial = new ArrayList<>();
    }
    public void agregarConversion(String conversion){
        LocalDateTime now = LocalDateTime.now();
        String fechaHora = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        historial.add(fechaHora + " - " + conversion);
    }
    public List<String> obtenerHistorial(){
        return historial;
    }
}
