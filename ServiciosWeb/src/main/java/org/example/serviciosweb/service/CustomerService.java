package org.example.serviciosweb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.serviciosweb.model.Customer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerService {

    private static final String URL = System.getenv("API_URL");
    private static final String API_KEY = System.getenv("API_KEY");

    public List<Customer> obtenerCustomers() throws IOException, InterruptedException {
        //por si no sirve la api key
        if (URL == null || URL.isBlank()) {
            throw new IOException("Falta configurar API_URL");
        }

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IOException("Falta configurar API_KEY");
        }

        HttpClient cliente = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15)).header("apikey", API_KEY)
                .header("Accept", "application/json").GET().build();


        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error HTTP: " + response.statusCode()
            );
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode raiz = mapper.readTree(response.body());

        JsonNode datos = raiz.isArray() ? raiz : raiz.path("data");

        if (!datos.isArray()) {
            throw new IOException("La respuesta no contiene clientes");
        }

        List<Customer> clientes = new ArrayList<>();

        for (JsonNode nodo : datos) {

            Customer c = new Customer(
                    nodo.path("id").asInt(),
                    nodo.path("first_name").asText(),
                    nodo.path("last_name").asText(),
                    nodo.path("email").asText(),
                    nodo.path("phone").asText(),
                    convertirDate(nodo.path("date_of_birth").asText()),
                    nodo.path("country").asText(),
                    nodo.path("city").asText(),
                    nodo.path("address").asText(),
                    nodo.path("annual_income").asDouble(),
                    nodo.path("credit_score").asDouble(),
                    nodo.path("has_unpaid_debt").asBoolean(),
                    nodo.path("unpaid_debt_amount").asDouble(),
                    nodo.path("status").asText(),
                    convertirLocalDate(nodo.path("created_at").asText())
            );

            clientes.add(c);
        }

        return clientes;
    }

    private LocalDate convertirLocalDate(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            return null;
        }
        return LocalDate.parse(fecha.substring(0, 10));
    }

    private Date convertirDate(String fecha) {
        LocalDate fechaLocal = convertirLocalDate(fecha);
        return fechaLocal == null ? null : java.sql.Date.valueOf(fechaLocal);
    }
}