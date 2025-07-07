package com.example.S3Uploader.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Bean
    public S3Client s3Client() {
        // ✅ Log values to verify
        System.out.println("🔍 S3 Client Configuration:");
        System.out.println("Region: " + region);
        System.out.println("Bucket Name: " + bucketName);
        System.out.println("AWS Endpoint: https://s3." + region + ".amazonaws.com");
        System.out.println("🔐 Access Key (injected): " + accessKey);
        System.out.println("🔐 Secret Key (starts with): " + secretKey.substring(0, 4));

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
