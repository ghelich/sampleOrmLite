package com.behsa.sampleormlite.database;

import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;


public class sample implements Serializable{

	@DatabaseField(generatedId = true, columnName = "id")
	public int Id;

	// Define a String type field to hold teacher's name
	@DatabaseField(columnName = "name")
	public String Name;
	
	public sample(){
		
	}
	
	public sample(final String name){
		this.Name = name;
	}
	
}
