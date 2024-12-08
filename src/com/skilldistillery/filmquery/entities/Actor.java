package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor {

	private int id;
	private String firstName;
	private String lastName;
	
	//________________________________________________________________________________
	
	public Actor () {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String toString () {
		return "Actor id =" + id + "| firstName = " + firstName + "| lastName = " + lastName;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Actor actor = (Actor) obj;
		return id == actor.id;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(id);
	}
	
}
