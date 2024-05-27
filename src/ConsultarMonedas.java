import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultarMonedas {
    public double convertirMoneda(String monedaBase, String monedaDestino, double cantidad) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/aeef272a9ff725d7c2b029b6/latest/" + monedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, Object> conversionMap = new Gson().fromJson(response.body(), Map.class);
            // Se obtiene la tasa de conversión
            Map<String, Double> rates = (Map<String, Double>) conversionMap.get("conversion_rates");
            double tasaConversion = rates.get(monedaDestino);
            return cantidad * tasaConversion;
        } catch (Exception e) {
            throw new RuntimeException("No se pudo realizar la conversión");
        }
    }
}
