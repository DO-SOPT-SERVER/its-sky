package org.sopt.kotlinsemina.global.auth.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.sopt.kotlinsemina.global.auth.model.JwtValidationType
import org.sopt.kotlinsemina.global.auth.model.JwtValidationType.*
import org.sopt.kotlinsemina.global.auth.model.UserAuthentication
import org.sopt.kotlinsemina.global.auth.service.JwtProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    val jwtProvider: JwtProvider
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = getJwtFromRequest(request)
        if (token?.let { jwtProvider.validateToken(it) == VALID_JWT } == true) {
            val memberId = jwtProvider.getUserFromJwt(token)
            val authentication = UserAuthentication(memberId.toString(), null, null)
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length)
        }
        return null
    }
}