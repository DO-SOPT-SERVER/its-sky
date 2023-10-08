package org.sopt.server.FirstSeminar.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class HealthCheckResponse<D> {
	private final int code;
	private final String status;
	private final boolean success;
	private final D data;

	public static <D> HealthCheckResponse<D> ofSuccess() {
		return of(200, "OK", true, null);
	}

	public static <D> HealthCheckResponse<D> ofSuccess(String msg, D data) {
		return of(200, msg, true, data);
	}

	public static <D> HealthCheckResponse<D> ofFail(int code, String msg) {
		return of(code, msg, false, null);
	}
}
