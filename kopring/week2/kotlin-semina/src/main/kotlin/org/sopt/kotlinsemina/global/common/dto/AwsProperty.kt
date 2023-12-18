package org.sopt.kotlinsemina.global.common.dto

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws-property")
data class AwsProperty(
    val accessKey: String,
    val secretKey: String,
    val region: String,
    val bucketName: String
)
