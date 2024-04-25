package app;

import domain.model.CurrencyHistoryResponse;
import domain.model.CurrencyPairResponse;
import domain.service.CurrencyConverterService;
import infrastructure.api.ExchangeRateApiClient;
import infrastructure.persistence.InMemoryConversionHistoryRepository;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConversionApp {
    private final CurrencyConverterService currencyConverterService;

    public CurrencyConversionApp() {
        ExchangeRateApiClient apiClient = new ExchangeRateApiClient();
        InMemoryConversionHistoryRepository historyRepository = new InMemoryConversionHistoryRepository();
        this.currencyConverterService = new CurrencyConverterService(apiClient, historyRepository);
    }

    public void run() {
        currencyConverterService.fetchConversionRates();
        Scanner scanner = new Scanner(System.in);
        int option;
        
        do {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    try {
                        System.out.print("Enter the base currency code (e.g., USD, PEN, EUR, JPY): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        currencyConverterService.fetchConversionRates(baseCurrency);
                        Map<String, Double> top10Rates = currencyConverterService.getTop10ConversionRates();
                        displayTop10Rates(top10Rates);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid currency code. Please enter a valid alphabetic code.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("......");
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter the base currency code (e.g., USD, PEN, EUR, JPY): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the year: ");
                        int year = scanner.nextInt();
                        System.out.print("Enter the month: ");
                        int month = scanner.nextInt();
                        System.out.print("Enter the day: ");
                        int day = scanner.nextInt();
                        scanner.nextLine();
                        CurrencyHistoryResponse historicalRates = currencyConverterService.fetchHistoricalRates(baseCurrency, year, month, day);
                        displayHistoricalRates(historicalRates);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid caracter.");
                        scanner.nextLine();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("......");
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter the base currency code (e.g., USD, PEN, EUR, JPY): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the year: ");
                        int year = scanner.nextInt();
                        System.out.print("Enter the month: ");
                        int month = scanner.nextInt();
                        System.out.print("Enter the day: ");
                        int day = scanner.nextInt();
                        System.out.print("Enter the amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        CurrencyHistoryResponse historicalRatesWithAmount = currencyConverterService.fetchHistoricalRatesWithAmount(baseCurrency, year, month, day, amount);
                        displayHistoricalRatesWithAmount(historicalRatesWithAmount);

                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid caracter.");
                        scanner.nextLine();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("......");
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Enter the base currency code (e.g., USD, PEN, EUR, JPY): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the target currency code (e.g., USD, PEN, EUR, JPY): ");
                        String targetCurrency = scanner.nextLine().toUpperCase();
                        CurrencyPairResponse currencyPair = currencyConverterService.fetchCurrencyPair(baseCurrency, targetCurrency);
                        displayCurrencyPair(currencyPair);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid currency code. Please enter a valid alphabetic code.");
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("......");
                    }
                    break;
                case 5:
                    try {
                        System.out.print("Enter the base currency code (e.g., USD, PEN, EUR, JPY): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the target currency code (e.g., USD, PEN, EUR, JPY): ");
                        String targetCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        CurrencyPairResponse currencyPairWithAmount = currencyConverterService.fetchCurrencyPairWithAmount(baseCurrency, targetCurrency, amount);
                        displayCurrencyPairWithAmount(currencyPairWithAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid caracter.");
                        scanner.nextLine();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        System.out.println("......");
                    }
                    break;
                case 6:
                    try {
                        System.out.print("Enter the amount in Euros [EUR - MXN]: ");
                        double amount = scanner.nextDouble();
                        double convertedAmount = currencyConverterService.convertEurosToPesos(amount);
                        System.out.printf("%.4f EUR = %.4f MXN\n", amount, convertedAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                case 7:
                    try {
                        System.out.print("Enter the amount in Dollars [USD - PEN]: ");
                        double amount = scanner.nextDouble();
                        double convertedAmount = currencyConverterService.convertDolaresToSoles(amount);
                        System.out.printf("%.4f USD = %.4f PEN\n", amount, convertedAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                case 8:
                    try {
                        System.out.print("Enter the amount in PEN [PEN - COP]: ");
                        double amount = scanner.nextDouble();
                        double convertedAmount = currencyConverterService.convertSolesToPeso(amount);
                        System.out.printf("%.4f PEN = %.4f COP\n", amount, convertedAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                case 9:
                    try {
                        System.out.print("Enter the amount in ARS [ARS - PEN]: ");
                        double amount = scanner.nextDouble();
                        double convertedAmount = currencyConverterService.convertPesoToSoles(amount);
                        System.out.printf("%.4f ARS = %.4f PEN\n", amount, convertedAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                case 10:
                    try {
                        System.out.print("Enter the amount in CLP [CLP - BOB]: ");
                        double amount = scanner.nextDouble();
                        double convertedAmount = currencyConverterService.convertPesoToBoliviano(amount);
                        System.out.printf("%.4f CLP = %.4f BOB\n", amount, convertedAmount);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Invalid entry. Please enter a valid number.");
                        scanner.nextLine();
                    }
                    break;
                case 11:
                    currencyConverterService.displayConversionHistory();
                    break;
                case 12:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (option != 12);
    }

    private void displayMenu() {
        System.out.println("\nCurrency Conversion Menu");
        System.out.println("1. Get Top 10 Conversion Rates");
        System.out.println("2. Get Historical Rates");
        System.out.println("3. Get Historical Rates with Amount");
        System.out.println("4. Get Currency Pair");
        System.out.println("5. Get Currency Pair with Amount");
        System.out.println("6. Convert Euros to Pesos");
        System.out.println("7. Convert Dollars to Soles");
        System.out.println("8. Convert Soles to Peso Colombiano");
        System.out.println("9. Convert Peso Argentino to Soles");
        System.out.println("10. Convert Peso Chileno to Bolivian Boliviano");
        System.out.println("11. Display Conversion History");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    private void displayTop10Rates(Map<String, Double> top10Rates) {
        System.out.println("\nTop 10 Conversion Rates:");
        int count = 1;
        for (Map.Entry<String, Double> entry : top10Rates.entrySet()) {
            System.out.println(count + ". " + entry.getKey() + ": " + entry.getValue());
            count++;
        }
    }

    private void displayHistoricalRates(CurrencyHistoryResponse response) {
        if (response.result().equals("success")) {
            System.out.println("\nHistorical Rates:");
            System.out.println("Year: " + response.year());
            System.out.println("Month: " + response.month());
            System.out.println("Day: " + response.day());
            System.out.println("Base Currency: " + response.base_code());
            for (Map.Entry<String, Double> entry : response.conversion_rates().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Error fetching historical rates: " + response.result());
        }
    }


    private void displayHistoricalRatesWithAmount(CurrencyHistoryResponse response) {
        if (response.result().equals("success")) {
            System.out.println("\nHistorical Rates with Amount:");
            System.out.println("Year: " + response.year());
            System.out.println("Month: " + response.month());
            System.out.println("Day: " + response.day());
            System.out.println("Base Currency: " + response.base_code());
            System.out.println("Requested Amount: " + response.requested_amount());
            for (Map.Entry<String, Double> entry : response.conversion_amounts().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Error fetching historical rates with amount: " + response.result());
        }
    }

    private void displayCurrencyPair(CurrencyPairResponse response) {
        if (response.result().equals("success")) {
            System.out.println("\nCurrency Pair:");
            System.out.println("Base Currency: " + response.base_code());
            System.out.println("Target Currency: " + response.target_code());
            System.out.println("Conversion Rate: " + response.conversion_rate());
        } else {
            System.out.println("Error fetching currency pair: " + response.result());
        }
    }

    private void displayCurrencyPairWithAmount(CurrencyPairResponse response) {
        if (response.result().equals("success")) {
            System.out.println("\nCurrency Pair with Amount:");
            System.out.println("Base Currency: " + response.base_code());
            System.out.println("Target Currency: " + response.target_code());
            System.out.println("Conversion Rate: " + response.conversion_rate());
            System.out.println("Conversion Result: " + response.conversion_result());
        } else {
            System.out.println("Error fetching currency pair with amount: " + response.result());
        }
    }

    public static void main(String[] args) {
        CurrencyConversionApp app = new CurrencyConversionApp();
        app.run();
    }
}
