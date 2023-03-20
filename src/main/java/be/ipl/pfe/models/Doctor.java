package be.ipl.pfe.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
	@Id
	private String id;

	@JsonProperty(required = true, value = "first_name")
	@Column(name = "first_name", nullable = false)
	public String firstName;

	@JsonProperty(required = true, value = "last_name")
	@Column(name = "last_name", nullable = false)
	public String lastName;

	public Doctor() {}

	@JsonCreator
	public Doctor(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
