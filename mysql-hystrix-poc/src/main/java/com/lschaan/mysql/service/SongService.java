package com.lschaan.mysql.service;

import java.util.List;

public class SongService {
	
	public static List<SongObj> getSong() {
		return new SongCommand().execute();
	}

}
