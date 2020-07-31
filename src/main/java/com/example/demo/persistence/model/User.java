package com.example.demo.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true, nullable = false, insertable = false)
	private Long userId;

	@Column(name = "name", length = 200)
	private String name;

	@Column(name = "phone_number", length = 20)
	private String phoneNumber;
}