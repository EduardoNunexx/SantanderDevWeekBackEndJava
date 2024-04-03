package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.adapters.out;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.GenerativeAiApi;
import feign.FeignException;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ia.provider", havingValue = "GEMINI", matchIfMissing = true)
@FeignClient(name = "geminiApi", url = "${gemini.base-url}", configuration = GoogleGeminiApi.Config.class)

public interface GoogleGeminiApi extends GenerativeAiApi {
    @PostMapping("/v1beta/models/gemini-pro:generateContent")
    GoogleGeminiResponse textOnlyInput(GoogleGeminiRequest request);
    @Override
    default String generateContent(String objective, String context){
        String prompt = """
                %s
                %s
                """.formatted(objective,context);
        GoogleGeminiRequest request = new GoogleGeminiRequest(List.of(new Content(List.of(new Part(prompt)))));
        try {
            GoogleGeminiResponse response = textOnlyInput(request);
            return response.candidates().getFirst().content().parts().getFirst().text();
        }catch (FeignException httpErrors){
            return "Ops! Api communication error !";
        }catch (Exception unexpectedError){
            return "Super ps! Google gemini Api returns a not expected value";
        }
    }
    //mapping the request jason
    record GoogleGeminiRequest(List<Content> contents){}
    record Content(List<Part> parts){}
    record Part(String text){}
    //mapping the response json
    record GoogleGeminiResponse(List<Candidate> candidates){}
    record Candidate(Content content){}



    class Config{
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey){
            return requestTemplate -> requestTemplate.query("key",apiKey);
        }
    }
}
