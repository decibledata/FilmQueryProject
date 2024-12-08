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
	
	//________________________________________________________________________________
	
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = "SELECT * FROM film WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
		if (rs.next()) {
			film = new Film();
			film.setId(rs.getInt("id"));
			film.setTitle(rs.getString("title"));
			film.setDescription(rs.getString("description"));
			film.setReleaseYear(rs.getInt("release_year"));
			film.setLanguageId(rs.getInt("language_id"));
			film.setRentalDuration(rs.getInt("rental_duration"));
			film.setRentalRate(rs.getDouble("rental_rate"));
			film.setLength(rs.getInt("length"));
			film.setReplacementCost(rs.getDouble("replacement_cost"));
			film.setRating(rs.getString("rating"));
			film.setSpecialFeatures(rs.getString("special_features"));
			film.setActors(findActorsByFilmId(filmId));
		}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return film;
	}

	//________________________________________________________________________________
	

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			
		if (rs.next()) {
			actor = new Actor();
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
		}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actor;
	}
	
	//________________________________________________________________________________
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorsByFilm = new ArrayList<>();
		String sql = "SELECT actor.id, actor.first_name, actor.last_name " + "FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id " + "WHERE film_actor.film_id = ?";

		try (Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actorsByFilm.add(actor);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return actorsByFilm;

	}

	
}
