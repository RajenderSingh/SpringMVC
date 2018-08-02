package com.demo.model.v1;

import java.io.Serializable;

public class Code implements Serializable {

	private static final long serialVersionUID = 1L;

	int id;
	String code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Code(int id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	@Override
	public String toString() {
		return "Code ==> id = " + id + ", code = " + code;
	}

}