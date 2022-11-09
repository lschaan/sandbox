package com.lschaan.mysql.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lschaan.mysql.db.DBConfig;
import com.lschaan.mysql.db.DBConnection;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SongCommand extends HystrixCommand<List<SongObj>> {
	private ApplicationContext appContext;

	public SongCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("DB")).andCommandPropertiesDefaults(
				HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));
		this.appContext = new AnnotationConfigApplicationContext(DBConfig.class);
	}

	public List<SongObj> run() throws BeansException, SQLException {
		String command = "SELECT * FROM songs";
		List<SongObj> songs = new ArrayList<>();
		
		Connection con = ((DBConnection) appContext.getBean("connection")).getConnection();
		PreparedStatement stmt = con.prepareStatement(command);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			songs.add(new SongObj(rs.getString("name"), rs.getInt("ID")));
		}
		return songs;
	}

	public List<SongObj> getFallback() {
		return new ArrayList<SongObj>();
	}
}
