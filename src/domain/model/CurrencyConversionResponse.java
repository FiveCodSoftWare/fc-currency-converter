package domain.model;

import java.util.Map;

public record CurrencyConversionResponse(
        String result,
        String base_code,
        Map<String, Double> conversion_rates
        ) {}
