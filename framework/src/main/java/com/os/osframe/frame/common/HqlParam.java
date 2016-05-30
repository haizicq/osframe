package com.os.osframe.frame.common;

import org.hibernate.type.Type;

public class HqlParam {
	private String name;

	private Object value;

	private Type type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public HqlParam(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public HqlParam(String name, Object value, Type type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}
}
