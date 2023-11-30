package org.sopt.kotlinsemina.global.config

import org.sopt.kotlinsemina.global.common.dto.AwsProperty
import org.sopt.kotlinsemina.global.common.service.S3Service
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AwsProperty::class)
class AdditionalConfig