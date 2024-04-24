# Currency Converter

This is a simple currency converter application that allows you to perform various currency conversion operations. The application provides the following functionalities:

## Features

1. **Get Top 10 Conversion Rates**: Displays the top 10 currency conversion rates based on the latest exchange rates. ([Example API URL](https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD))

2. **Get Historical Rates**: Retrieves the historical exchange rates between two currencies for a specific date. ([Example API URL](https://v6.exchangerate-api.com/v6/YOUR-API-KEY/history/USD/YEAR/MONTH/DAY))

3. **Get Historical Rates with Amount**: Retrieves the historical exchange rates between two currencies for a specific date and converts a given amount to the target currency. ([Example API URL](https://v6.exchangerate-api.com/v6/YOUR-API-KEY/history/USD/YEAR/MONTH/DAY/AMOUNT))

4. **Get Currency Pair**: Retrieves the current exchange rate between two currencies. ([Example API URL](https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP))

5. **Get Currency Pair with Amount**: Retrieves the current exchange rate between two currencies and converts a given amount to the target currency. ([Example API URL](https://v6.exchangerate-api.com/v6/YOUR-API-KEY/pair/EUR/GBP/AMOUNT))

6. **Convert Euros to Pesos**: Converts a given amount from Euros to Mexican Pesos based on the current exchange rate.

7. **Convert Dollars to Soles**: Converts a given amount from US Dollars to Peruvian Soles based on the current exchange rate.

8. **Convert Soles to Peso Colombiano**: Converts a given amount from Peruvian Soles to Colombian Pesos based on the current exchange rate.

9. **Convert Peso Argentino to Soles**: Converts a given amount from Argentine Pesos to Peruvian Soles based on the current exchange rate.

10. **Convert Peso Chileno to Bolivian Boliviano**: Converts a given amount from Chilean Pesos to Bolivian Bolivianos based on the current exchange rate.

11. **Display Conversion History**: Displays the history of currency conversions performed during the current session.

12. **Exit**: Exits the currency converter application.

## Usage

To use the currency converter, follow these steps:

1. Run the application.
2. The application will display a menu with the available options.
3. Enter the corresponding number for the desired operation.
4. Follow the prompts to provide any additional information required for the selected operation.
5. The application will perform the requested operation and display the result.
6. Repeat steps 3-5 for additional operations or select the "Exit" option to quit the application.

Note: This application fetches the latest exchange rates from the [ExchangeRate-API](https://www.exchangerate-api.com/docs/overview), so an internet connection is required for accurate currency conversion.

## Implementation Details

This application uses the `com.google.gson.Gson` library for parsing JSON data from the ExchangeRate-API. It also utilizes the `record` feature introduced in Java 16 for creating immutable data classes.