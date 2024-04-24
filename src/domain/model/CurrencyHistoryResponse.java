package domain.model;

import java.util.Map;

public record CurrencyHistoryResponse(
        String result,
        int year,
        int month,
        int day,
        String base_code,
        double requested_amount,
        Map<String, Double> conversion_rates,
        Map<String, Double> conversion_amounts
) {
}
