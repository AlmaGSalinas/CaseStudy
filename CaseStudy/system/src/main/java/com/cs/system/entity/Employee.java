package com.cs.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "Firstname",nullable = false,length = 50)
		private String Firstname;

		@Column(name = "Middlename",nullable = true,length = 50)
		private String Middlename;
	
		@Column(name = "Lastname",nullable = false,length = 50)
		private String Lastname;
		
		@Column(name = "Birthdate",nullable = false, length = 10)
		private String Birthdate;

		@Column (name = "Position", nullable = false, length = 50)
		private String Position;

		//Empty constructor
		public Employee() {
			
		}
		
		//Constructor with ID
		public Employee(int id, String firstname, String middlename, String lastname, String birthdate,
				String position) {
			super();
			this.id = id;
			Firstname = firstname;
			Middlename = middlename;
			Lastname = lastname;
			Birthdate = birthdate;
			Position = position;
		}
		
		//Getters y setters

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstname() {
			return Firstname;
		}

		public void setFirstname(String firstname) {
			Firstname = firstname;
		}

		public String getMiddlename() {
			return Middlename;
		}

		public void setMiddlename(String middlename) {
			Middlename = middlename;
		}

		public String getLastname() {
			return Lastname;
		}

		public void setLastname(String lastname) {
			Lastname = lastname;
		}

		public String getBirthdate() {
			return Birthdate;
		}

		public void setBirthdate(String birthdate) {
			Birthdate = birthdate;
		}

		public String getPosition() {
			return Position;
		}

		public void setPosition(String position) {
			Position = position;
		}

	
		
		
	

}
