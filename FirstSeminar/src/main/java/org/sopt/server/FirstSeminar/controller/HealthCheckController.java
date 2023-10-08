package org.sopt.server.FirstSeminar.controller;

import org.sopt.server.FirstSeminar.domain.Person;
import org.sopt.server.FirstSeminar.dto.HealthCheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

	@GetMapping("/v1")
	public Map<String, String> healthCheckV1() {
		Map<String, String> response = new HashMap<>();
		response.put("status", "OK");
		return response;
	}

	@GetMapping("/v2")
	public ResponseEntity<String> healthCheckV2() {
		return ResponseEntity.ok().body("OK");
	}

	@GetMapping("/v3")
	public String healthCheckV3() {
		Person myself = Person.builder()
				.firstName("민철")
				.lastName("신")
				.age(25)
				.build();
		return myself.toString();
	}

	@GetMapping("/v4")
	public ResponseEntity<Map<String, String>> healthCheckV4() {
		Map<String, String> response = new HashMap<>();
		response.put("status", "OK");
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/v5")
	public ResponseEntity<HealthCheckResponse> healthCheckV5() {
		return ResponseEntity.ok().body(new HealthCheckResponse());
		// HealthCheckReponse 클래스에 @Getter를 붙인 이유는 Json으로 직렬화할 때 값을 getter로 읽어와야 하는데 빼게 되면
		// org.springframework.web.HttpMediaTypeNotAcceptableException: No acceptable representation 라는 예외가 터진다.
	}
}
