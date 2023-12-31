package org.sopt.kotlinsemina.global.common.service

import org.sopt.kotlinsemina.global.common.dto.AwsProperty
import org.sopt.kotlinsemina.global.common.dto.PresignedUrlVO
import org.sopt.kotlinsemina.global.config.AWSConfig
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest
import java.time.Duration
import java.util.*

@Component
class S3Service(
    val awsProperty: AwsProperty,
    val awsConfig: AWSConfig
) {

    fun getUploadPreSignedUrl(prefix: String): PresignedUrlVO {
        val fileName = generateFileName()
        val key = prefix + fileName

        val preSigner = awsConfig.getS3Presigner()

        val putObjectRequest = PutObjectRequest.builder()
            .bucket(awsProperty.bucketName)
            .key(key)
            .build()

        val preSignedUrlRequest = PutObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(PRE_SIGNED_URL_EXPIRE_MINUTE))
            .putObjectRequest(putObjectRequest)
            .build()

        val url = preSigner.presignPutObject(preSignedUrlRequest).url().toString()

        return PresignedUrlVO(fileName, url)
    }

    fun uploadImage(directoryPath: String, image: MultipartFile): String {
        validateExtension(image)
        validateFileSize(image)

        val key = directoryPath + generateFileName()
        val s3Client = awsConfig.getS3Client()

        val request = PutObjectRequest.builder()
            .bucket(awsProperty.bucketName)
            .contentType(image.contentType)
            .contentDisposition("inline")
            .build()

        val requestBody = RequestBody.fromBytes(image.bytes)
        s3Client.putObject(request, requestBody)
        return key
    }

    fun deleteImage(key: String) {
        val s3Client = awsConfig.getS3Client()

        s3Client.deleteObject{ builder ->
            builder
            .bucket(awsProperty.bucketName)
            .key(key)
            .build()
        }
    }

    private fun generateFileName(): String {
        return UUID.randomUUID().toString() + ".jpg"
    }

    private fun validateExtension(image: MultipartFile) {
        val contentType = image.contentType
        if (!IMAGE_EXTENSIONS.contains(contentType)) {
            throw RuntimeException("이미지 확장자는 jpg, png, webp만 가능합니다.")
        }
    }

    private fun validateFileSize(image: MultipartFile) {
        if (image.size > MAX_FILE_SIZE) {
            throw RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.")
        }
    }

    companion object {
        private val IMAGE_EXTENSIONS: List<String> = listOf("image/jpeg", "image/png", "image/jpg", "image/webp")
        private const val MAX_FILE_SIZE = 5 * 1024 * 1024L
        private const val PRE_SIGNED_URL_EXPIRE_MINUTE = 1L
    }
}