package org.sopt.server.FirstSeminar.domain;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Person {
	private String firstName;
	private String lastName;
	private int age;
}
