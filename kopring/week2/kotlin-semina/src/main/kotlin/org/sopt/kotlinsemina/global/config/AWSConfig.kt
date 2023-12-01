package org.sopt.kotlinsemina.global.config

import org.sopt.kotlinsemina.global.common.dto.AwsProperty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.presigner.S3Presigner

@Configuration
class AWSConfig(
    val awsProperty: AwsProperty
) {

    @Bean
    fun getS3Presigner(): S3Presigner {
        return S3Presigner.builder()
            .region(getRegion())
            .credentialsProvider(systemPropertyCredentialsProvider())
            .build()
    }

    @Bean
    fun systemPropertyCredentialsProvider(): SystemPropertyCredentialsProvider {
        System.setProperty(AWS_ACCESS_KEY_ID, awsProperty.accessKey)
        System.setProperty(AWS_SECRET_ACCESS_KEY, awsProperty.secretKey)
        return SystemPropertyCredentialsProvider.create()
    }

    @Bean
    fun getRegion(): Region {
        return Region.of(awsProperty.region)
    }

    @Bean
    fun getS3Client(): S3Client {
        return S3Client.builder()
            .region(getRegion())
            .credentialsProvider(systemPropertyCredentialsProvider())
            .build()
    }

    companion object {
        private const val AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        private const val AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
    }
}