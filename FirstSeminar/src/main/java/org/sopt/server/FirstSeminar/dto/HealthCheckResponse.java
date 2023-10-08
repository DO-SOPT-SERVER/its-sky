package org.sopt.server.FirstSeminar.dto;

import lombok.Getter;

@Getter
public class HealthCheckResponse {
	private static final String STATUS = "OK";
	private String status;

	public HealthCheckResponse() {
		this.status = STATUS;
	}
}
