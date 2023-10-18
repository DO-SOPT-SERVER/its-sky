package org.sopt.FirstSemina.controller

import org.sopt.FirstSemina.domain.Person
import org.sopt.FirstSemina.dto.HealthCheckResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.HashMap

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping("/v1")
    fun healthCheckV1(): Map<String, String> {
        val response = HashMap<String, String>();
        response.put("status", "OK")
        return response
    }

    @GetMapping("/v2")
    fun healthCheckV2(): ResponseEntity<String> {
        return ResponseEntity.ok().body("OK")
    }

    @GetMapping("/v3")
    fun healthCheckV3(): Person {
        return Person(firstName = "민철", lastName = "신", age = 25)
    } // 코틀린에서는 파라미터의 이름을 지정해서 값을 할당할 수 있기 때문에 빌더 패턴이 필요없음.

    @GetMapping("/v4")
    fun healthCheckV4(): ResponseEntity<Map<String, String>> {
        val response = HashMap<String, String>()
        response["status"] = "OK" // 파이썬처럼 put 가능
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/v5")
    fun healthCheckV5(): ResponseEntity<HealthCheckResponse> {
        return ResponseEntity.ok().body(HealthCheckResponse())
    }
}