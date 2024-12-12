package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}
//________________________________________________________________________________

	private void launch() {
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;

		while (keepGoing) {
			System.out.println("_______________________________________________");
			System.out.println("\n\tFILM QUERY MENU: \n\n---PLEASE MAKE A SELECTION---");
			System.out.println("_______________________________________________\n");
			System.out.println("1. Find a Film by ID\n");
			System.out.println("2. Find a film by Keyword\n");
			System.out.println("3. Exit\n");
			System.out.println("_______________________________________________");
			int choice = input.nextInt();
			input.nextLine();

			switch (choice) {

			case 1:
				findFilmById(input);
				break;

			case 2:
				findFilmByKword(input);
				break;
			case 3:
				System.out.println("*GOODBYE*");
				keepGoing = false;
				break;
			default:
				System.out.println("*INVALID SELECTION. TRY AGAIN*");
			}
		}
		input.close();
	}

//________________________________________________________________________________

	private void findFilmById(Scanner input) {
		System.out.println("Enter Film ID: ");

		try {
			int filmId = input.nextInt();
			input.nextLine();
			Film film = db.findFilmById(filmId);

			if (film != null) {
				filmInfoWithId(film);
			} else {
				System.out.println("*Film ID Invalid*");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

//________________________________________________________________________________

	private void findFilmByKword(Scanner input) {
		System.out.println("\nEnter Film Keyword: ");
		String kw = input.nextLine();

		try {
			List<Film> filmKw = db.findFilmsByKeyword(kw);

			if (filmKw.isEmpty()) {
				System.out.println("No Film with Keyword: '" + kw + "' Located\n\n*PLEASE TRY AGAIN*");
			} else {
				System.out.println("===========================");
				System.out.println("\nFilms Found with Keyword:");
				System.out.println("===========================");
				for (Film film : filmKw) {
					System.out.println("| Film Info\n|--------");
					System.out.println("| ID: " + film.getId() + "\n|");
					System.out.println("| TITLE: " + film.getTitle() + "\n|");
					System.out.println("| YEAR: " + film.getReleaseYear() + "\n|");
					System.out.println("| RATING: " + film.getRating() + "\n|");
					System.out.println("| DESCRIPTION: " + film.getDescription() + "\n|________\n|");
					System.out.println("| LANGUAGE: " + film.getLanguageName() + "\n|");
					System.out.println("| CAST: " + film.getActors() + "\n|");
					System.out.println("===========================");
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

//________________________________________________________________________________

	private void filmInfoWithId(Film filmById) {
		System.out.println("===========================");
		System.out.println("| Film Info\n|--------");
		System.out.println("| ID: " + filmById.getId() + "\n|");
		System.out.println("| TITLE: " + filmById.getTitle() + "\n|");
		System.out.println("| YEAR: " + filmById.getReleaseYear() + "\n|");
		System.out.println("| RATING: " + filmById.getRating() + "\n|");
		System.out.println("| DESCRIPTION: " + filmById.getDescription() + "\n|________");
		System.out.println("| LANGUAGE: " + filmById.getLanguageName() + "\n|");
		System.out.println("| CAST: " + filmById.getActors() + "\n|");
		System.out.println("===========================");

	}
}
