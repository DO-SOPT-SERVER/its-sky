package org.sopt.Seminar.domain.member.dto;

import lombok.Data;
import org.sopt.Seminar.domain.member.model.Part;

@Data
public class MemberProfileUpdateRequest {
	private int generation;
	private Part part;
}
