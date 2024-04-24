package domain.service;

import domain.model.CurrencyConversionResponse;
import domain.model.CurrencyHistoryResponse;
import domain.model.CurrencyPairResponse;
import infrastructure.api.ExchangeRateApiClient;
import infrastructure.persistence.ConversionHistoryRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyConverterService {
    private final ExchangeRateApiClient apiClient;
    private final ConversionHistoryRepository historyRepository;
    private final Map<String, Double> conversionRates = new HashMap<>();


    public CurrencyConverterService(ExchangeRateApiClient apiClient, ConversionHistoryRepository historyRepository) {
        this.apiClient = apiClient;
        this.historyRepository = historyRepository;
    }

    public void fetchConversionRates(String baseCurrency) {
        try {
            CurrencyConversionResponse response = apiClient.fetchConversionRates(baseCurrency);
            if (response.result().equals("success")) {
                conversionRates.clear();
                conversionRates.putAll(response.conversion_rates());
            } else {
                System.out.println("Error fetching conversion rates.");
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("......");
        }

    }

    public Map<String, Double> getTop10ConversionRates() {
        return conversionRates.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public void fetchConversionRates() {
        try {
            CurrencyConversionResponse response = apiClient.fetchConversionRates();
            if (response.result().equals("success")) {
                conversionRates.clear();
                conversionRates.putAll(response.conversion_rates());
            } else {
                System.out.println("Error fetching conversion rates.");
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("......");
        }

    }

    public CurrencyHistoryResponse fetchHistoricalRates(String baseCurrency, int year, int month, int day) {
        return apiClient.fetchHistoricalRates(baseCurrency, year, month, day);
    }

    public CurrencyHistoryResponse fetchHistoricalRatesWithAmount(String baseCurrency, int year, int month, int day, double amount) {
        return apiClient.fetchHistoricalRatesWithAmount(baseCurrency, year, month, day, amount);
    }

    public CurrencyPairResponse fetchCurrencyPair(String baseCurrency, String targetCurrency) {
        return apiClient.fetchCurrencyPair(baseCurrency, targetCurrency);
    }

    public CurrencyPairResponse fetchCurrencyPairWithAmount(String baseCurrency, String targetCurrency, double amount) {
        return apiClient.fetchCurrencyPairWithAmount(baseCurrency, targetCurrency, amount);
    }

    public double convertEurosToPesos(double amount) {
        double convertedAmount = convertCurrency("EUR", "MXN", amount);
        historyRepository.saveConversion("EUR to MXN", convertedAmount);
        return convertedAmount;
    }

    public double convertDolaresToSoles(double amount) {
        double convertedAmount = convertCurrency("USD", "PEN", amount);
        historyRepository.saveConversion("USD to PEN", convertedAmount);
        return convertedAmount;
    }

    public double convertSolesToPeso(double amount) {
        double convertedAmount = convertCurrency("PEN", "COP", amount);
        historyRepository.saveConversion("PEN to COP", convertedAmount);
        return convertedAmount;
    }

    public double convertPesoToSoles(double amount) {
        double convertedAmount = convertCurrency("ARS", "PEN", amount);
        historyRepository.saveConversion("ARS to PEN", convertedAmount);
        return convertedAmount;
    }

    public double convertPesoToBoliviano(double amount) {
        double convertedAmount = convertCurrency("CLP", "BOB", amount);
        historyRepository.saveConversion("CLP to BOB", convertedAmount);
        return convertedAmount;
    }


    private double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }


    public void displayConversionHistory() {
        Map<String, Double> history = historyRepository.getConversionHistory();
        if (history.isEmpty()) {
            System.out.println("No conversion history available.");
        } else {
            System.out.println("Conversion History:");
            for (Map.Entry<String, Double> entry : history.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }


}
