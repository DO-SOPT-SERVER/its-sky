package org.sopt.kotlinsemina.global.auth.service

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.sopt.kotlinsemina.global.auth.model.JwtValidationType
import org.sopt.kotlinsemina.global.common.dto.JwtProperty
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


private const val MEMBER_ID = "memberId"

@Component
class JwtProvider(
    private val jwtProperty: JwtProperty
) {
    private var JWT_SECRET = jwtProperty.secret

    @PostConstruct
    fun init() {
        JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(Charsets.UTF_8))
    }

    fun generateToken(authentication: Authentication, tokenExpirationTime: Long): String {
        val now = Date()
        val claims = Jwts.claims()
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenExpirationTime))

        claims.put(MEMBER_ID, authentication.principal)

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(claims)
            .signWith(getSigningKey())
            .compact()
    }

    private fun getSigningKey(): SecretKey {
        val encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray())
        return Keys.hmacShaKeyFor(encodedKey.toByteArray())
    }

    fun validateToken(token: String): JwtValidationType {
        return try {
            val claims: Claims = getBody(token)
            JwtValidationType.VALID_JWT
        } catch (ex: MalformedJwtException) {
            JwtValidationType.INVALID_JWT_TOKEN
        } catch (ex: ExpiredJwtException) {
            JwtValidationType.EXPIRED_JWT_TOKEN
        } catch (ex: UnsupportedJwtException) {
            JwtValidationType.UNSUPPORTED_JWT_TOKEN
        } catch (ex: IllegalArgumentException) {
            JwtValidationType.EMPTY_JWT
        }
    }

    fun getBody(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun getUserFromJwt(token: String): Long {
        val claims = getBody(token)
        return claims[MEMBER_ID].toString().toLong()
    }
}