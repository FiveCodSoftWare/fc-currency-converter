package infrastructure.api;

import com.google.gson.Gson;
import domain.model.CurrencyConversionResponse;
import domain.model.CurrencyHistoryResponse;
import domain.model.CurrencyPairResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiClient {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/9609bedda42a65e48522a383";
    private static final Gson GSON = new Gson();

    //GET All :: https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD
    public CurrencyConversionResponse fetchConversionRates() {
        String apiUrl = API_BASE_URL + "/latest/" + "USD";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyConversionResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching conversion rates: " + e.getMessage());
        }
    }

    //GET Currency :: https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest
    public CurrencyConversionResponse fetchConversionRates(String baseCurrency) {
        String apiUrl = API_BASE_URL + "/latest/" + baseCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyConversionResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching conversion rates: " + e.getMessage());
        }
    }

    //GET Historical :: https://v6.exchangerate-api.com/v6/YOUR-API-KEY/history/USD/YEAR/MONTH/DAY
    public CurrencyHistoryResponse fetchHistoricalRates(String baseCurrency, int year, int month, int day) {
        String apiUrl = API_BASE_URL + "/history/" + baseCurrency + "/" + year + "/" + month + "/" + day;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyHistoryResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching historical rates:" + e.getMessage());
        }
    }

    // GET Historical Amount :: https://v6.exchangerate-api.com/v6/YOUR-API-KEY/history/USD/YEAR/MONTH/DAY/AMOUNT
    public CurrencyHistoryResponse fetchHistoricalRatesWithAmount(String baseCurrency, int year, int month, int day, double amount) {
        String apiUrl = API_BASE_URL + "/history/" + baseCurrency + "/" + year + "/" + month + "/" + day + "/" + amount;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyHistoryResponse.class);
        } catch (Exception  e) {
            throw new RuntimeException("Error fetching historical rates with amount:" + e.getMessage());
        }
    }

    // GET Pair Conversion :: https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP
    public CurrencyPairResponse fetchCurrencyPair(String baseCurrency, String targetCurrency) {
        String apiUrl = API_BASE_URL + "/pair/" + baseCurrency + "/" + targetCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyPairResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching currency pair:" + e.getMessage());
        }
    }

    // GET Pair Conversion Amount :: GET https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT
    public CurrencyPairResponse fetchCurrencyPairWithAmount(String baseCurrency, String targetCurrency, double amount) {
        String apiUrl = API_BASE_URL + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), CurrencyPairResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("rror fetching currency pair with amount:" + e.getMessage());
        }
    }
}
