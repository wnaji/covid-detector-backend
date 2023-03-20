package be.ipl.pfe.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "establishments")
@Data
public class Establishment {
	@Id
	private String id;

	@JsonProperty(required = true)
	@Column(name = "name", nullable = false)
	private String name;

	public Establishment() {}

	@JsonCreator
	public Establishment(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
