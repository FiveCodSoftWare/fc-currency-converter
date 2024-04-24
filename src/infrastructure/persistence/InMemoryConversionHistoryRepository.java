package infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConversionHistoryRepository implements ConversionHistoryRepository {
    private final Map<String, Double> conversionHistory = new HashMap<>();

    @Override
    public void saveConversion(String conversionKey, double amount) {
        conversionHistory.put(conversionKey, amount);
    }

    @Override
    public Map<String, Double> getConversionHistory() {
        return new HashMap<>(conversionHistory);
    }
}
