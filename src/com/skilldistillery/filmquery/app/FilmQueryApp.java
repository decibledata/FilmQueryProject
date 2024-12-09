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
    //app.test();
    app.launch();
    //
  }

 // private void test() {
 //   Film film = db.findFilmById(1);
   // System.out.println(film);
  //}

//________________________________________________________________________________
	
  
  private void launch() {
    Scanner input = new Scanner(System.in);
    boolean keepGoing = true;
    
    while (keepGoing) {
    	System.out.println("\nFILM QUERY MENU: \nPLEASE MAKE A SELECTION\n");
    	System.out.println("1. Find a Film by ID\n");
    	System.out.println("2. Find a film by Keyword\n");
    	System.out.println("3. Exit\n");
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
    		System.out.println("Goodbye.");
    		keepGoing = false;
    		break;
    	default:
    		System.out.println("INVALID SELECTION. TRY AGAIN.");
    	}
    }
    input.close();
  }
  
//________________________________________________________________________________
	

  private void findFilmById(Scanner input) {
	  System.out.println("Enter Film ID: ");
	  int filmId = input.nextInt();
	  Film filmById = db.findFilmById(filmId);
	  
	  if (filmById != null) {
		  System.out.println(filmById);
  }
	  else {
		  System.out.println("Film ID Invalid");
	  }
  }
  
//________________________________________________________________________________
	
  
private void findFilmByKword(Scanner input) {
	System.out.println("Enter Film Keyword: ");
	String kw = input.nextLine();
	List<Film> filmKw = db.findFilmsByKeyword(kw);
	
	 if (filmKw.isEmpty()) {
		  System.out.println("No Film with Keyword: " + kw +  " Located");
 }
	  else {
		  for (Film filmById : filmKw) {
			  System.out.println(filmById);
			  System.out.println("__________________");
		  }
	  }

    }
  }

    
