package infrastructure.persistence;

import java.util.Map;

public interface ConversionHistoryRepository {
    void saveConversion(String conversionKey, double amount);
    Map<String, Double> getConversionHistory();
}
