package org.sopt.Seminar.member.dto;

import lombok.Data;
import org.sopt.Seminar.member.domain.Part;

@Data
public class MemberProfileUpdateRequest {
	private int generation;
	private Part part;
}
