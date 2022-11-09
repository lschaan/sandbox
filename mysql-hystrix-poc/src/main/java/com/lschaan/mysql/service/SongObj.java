package com.lschaan.mysql.service;

public class SongObj {

	private String name;
	private int ID;

	public SongObj(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

}
