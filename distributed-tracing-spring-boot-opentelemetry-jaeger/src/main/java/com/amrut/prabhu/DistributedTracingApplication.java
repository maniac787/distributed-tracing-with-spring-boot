package com.amrut.prabhu;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;

@SpringBootApplication
public class DistributedTracingApplication {

    private static final org.apache.logging.log4j.Logger log4jLogger =
            LogManager.getLogger("log4j-logger");
    private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger("slf4j-logger");
    private static final java.util.logging.Logger julLogger = Logger.getLogger("jul-logger");

    public static void main(String[] args) {
        initializeOpenTelemetry();
        /*OpenTelemetrySdk openTelemetrySdk = // Configure OpenTelemetrySdk

                // Find OpenTelemetryAppender in log4j configuration and install openTelemetrySdk
                OpenTelemetryAppender.install(openTelemetrySdk);

        // ... proceed with application
*/
        SpringApplication.run(DistributedTracingApplication.class, args);
    }

    private static void initializeOpenTelemetry() {
        OpenTelemetrySdk sdk =
                OpenTelemetrySdk.builder()
                        .setLoggerProvider(
                                SdkLoggerProvider.builder()
                                        .setResource(
                                                Resource.getDefault().toBuilder()
                                                        .put(ResourceAttributes.SERVICE_NAME, "log4j-example")
                                                        .build())
                                        .addLogRecordProcessor(
                                                BatchLogRecordProcessor.builder(
                                                                OtlpGrpcLogRecordExporter.builder()
                                                                        .setEndpoint("http://localhost:4317")
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();
        GlobalOpenTelemetry.set(sdk);

        // Add hook to close SDK, which flushes logs
        Runtime.getRuntime().addShutdownHook(new Thread(sdk::close));
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.
                build();
    }
}
