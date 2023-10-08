package org.domain;

public abstract class Poketmon {
	private String name;
	private PoketmonType type;

	public Poketmon(String name, PoketmonType type) {
		this.name = name;
		this.type = type;
	}
}
