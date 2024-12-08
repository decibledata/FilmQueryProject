package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
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

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    try {
    System.out.println("Enter a Film ID: ");
    int filmId = input.nextInt();
    Film filmlaunch = db.findFilmById(filmId);
    
    
    if (filmlaunch != null) {
    	System.out.println(filmlaunch);
    }
    else {
    	System.out.println("Film not found. Invalid ID.");
    }
    } catch (InputMismatchException sql) {
    	System.out.println("Invalid Input. Please enter a Valid Film ID");
    } catch (Exception sql) {
    	System.out.println("Error: " + sql.getMessage());
    	sql.printStackTrace();
    }
    finally  {
    	input.close();    	
    }
  }
}
    
