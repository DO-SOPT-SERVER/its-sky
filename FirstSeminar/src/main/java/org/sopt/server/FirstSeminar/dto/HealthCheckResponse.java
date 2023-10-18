package org.sopt.server.FirstSeminar.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class HealthCheckResponse<T> {
	private final int code;
	private final String status;
	private final boolean success;
	private final T data;

	public static <T> HealthCheckResponse<T> ofSuccess() {
		return of(200, "OK", true, null);
	}

	public static <T> HealthCheckResponse<T> ofSuccess(String msg, T data) {
		return of(200, msg, true, data);
	}

	public static <T> HealthCheckResponse<T> ofFail(int code, String msg) {
		return of(code, msg, false, null);
	}
}
