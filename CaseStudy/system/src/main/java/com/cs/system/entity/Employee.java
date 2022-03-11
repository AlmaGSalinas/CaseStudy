package com.cs.system.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "Firstname",nullable = false,length = 50)
		private String firstname;

		@Column(name = "Middlename",nullable = true,length = 50)
		private String middlename;
	
		@Column(name = "Lastname",nullable = false,length = 50)
		private String lastname;
		
		@Column(name = "Birthdate",nullable = false, length = 10)
		private String birthdate;

		@Column (name = "Position", nullable = false, length = 50)
		private String position;

		@OneToMany(mappedBy =  "id_fk", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private Set<Compensation> compensations;
		//Empty constructor
		public Employee() {
			
		}

		//Constructor with ID
		public Employee(int id, String firstname, String middlename, String lastname, String birthdate,
		String position) {
		this.id = id;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.position = position;
}
		
		//Getters y setters
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getMiddlename() {
			return middlename;
		}

		public void setMiddlename(String middlename) {
			this.middlename = middlename;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getBirthdate() {
			return birthdate;
		}

		public void setBirthdate(String birthdate) {
			this.birthdate = birthdate;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}
		
}
		