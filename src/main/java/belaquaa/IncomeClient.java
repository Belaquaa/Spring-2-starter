package belaquaa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class IncomeClient {

    @Value("${income.url}")
    private String incomeURL;

    private final RestTemplate restTemplate;

    @Autowired
    public IncomeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getAnnualIncome(Long userId) {
        String url = String.format("%s?id=%d", incomeURL, userId);
        try {
            String response = this.restTemplate.getForObject(url, String.class);
            return extractIncomeFromResponse(response, userId);
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("User income not found for user with ID: " + userId);
        } catch (HttpClientErrorException e) {
            System.err.println("Failed to retrieve user income for user with ID: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private double extractIncomeFromResponse(String response, Long userId) {
        if (response == null || response.isEmpty()) return 0.0;

        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.optLong("id") == userId) {
                    return jsonObject.optDouble("income", 0.0) * 12;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
