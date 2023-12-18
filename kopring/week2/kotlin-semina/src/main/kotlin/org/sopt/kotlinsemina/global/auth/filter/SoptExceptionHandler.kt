package org.sopt.kotlinsemina.global.auth.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class SoptExceptionHandler: AuthenticationEntryPoint, AccessDeniedHandler {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        setResponse(response!!, HttpServletResponse.SC_UNAUTHORIZED)
    }

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        setResponse(response!!, HttpServletResponse.SC_FORBIDDEN)
    }

    private fun setResponse(response: HttpServletResponse, code: Int) {
        response.status = code
    }
}