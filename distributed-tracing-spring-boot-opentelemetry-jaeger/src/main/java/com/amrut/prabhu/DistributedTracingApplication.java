package com.amrut.prabhu;

/*import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.logs.GlobalLoggerProvider;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.SimpleLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;*/
import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DistributedTracingApplication {
    public static void main(String[] args) {
//        initializeOpenTelemetry();

        SpringApplication.run(DistributedTracingApplication.class, args);
    }

   /* private static void initializeOpenTelemetry() {
        OpenTelemetrySdk sdk =
                OpenTelemetrySdk.builder()
                        .setLoggerProvider(
                                SdkLoggerProvider.builder()
                                        *//*.setResource(
                                                Resource.getDefault().toBuilder()
                                                        .put(ResourceAttributes.SERVICE_NAME, "app")
                                                        .build())*//*
                                        .addLogRecordProcessor(
                                                SimpleLogRecordProcessor.create(
                                                        OtlpHttpLogRecordExporter.builder()
                                                                        .setEndpoint("http://192.168.1.33:4318/v1/logs")
                                                                        .build())
                                                        )
                                        .build())
                        .build();
        // Find OpenTelemetryAppender in logback configuration and install openTelemetrySdk
        OpenTelemetryAppender.install(sdk);
    }*/

    /*@Bean
    public SdkLoggerProvider sdkLoggerProvider() {
        Resource resource = Resource.getDefault()
                .merge(Resource.create(Attributes.of(ResourceAttributes.SERVICE_NAME, "app")));
        SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder().setResource(resource)
                .addLogRecordProcessor(
                        SimpleLogRecordProcessor.create(
                                OtlpHttpLogRecordExporter.builder()
                                        .setEndpoint("http://192.168.1.33:4318/v1/logs")
                                        .build()))
                .build();

        GlobalLoggerProvider.set(sdkLoggerProvider);
        return sdkLoggerProvider;
    }*/


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.
                build();
    }
}
