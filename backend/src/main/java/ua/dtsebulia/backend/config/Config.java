package ua.dtsebulia.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        String key = getSecretKeyFromParameterStore();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + key);
            return execution.execute(request, body);
        });
        return restTemplate;
    }

    private String getSecretKeyFromParameterStore() {
        SsmClient ssmClient = SsmClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.EU_CENTRAL_1)
                .build();

        GetParameterRequest parameterRequest = GetParameterRequest.builder()
                .name("/movie-generator-key")
                .build();

        GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
        return parameterResponse.parameter().value();
    }
}
