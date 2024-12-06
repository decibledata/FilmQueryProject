package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

		} catch (SQLException sqle) {
			System.err.println("Error getting film" + filmId);
			sqle.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsByFilm = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name " + "FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				
				Actor actor = new Actor();
				actorsByFilm.add(actor);
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actorsByFilm;

	}

}
