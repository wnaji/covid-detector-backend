package be.ipl.pfe.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "citizens", uniqueConstraints = {@UniqueConstraint(columnNames = {"notification_token"})})
@Data
public class Citizen {
    @Id
    private String id;

    @JsonProperty(value = "first_name")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty(required = true, value = "notification_token")
    @Column(name = "notification_token", nullable = false)
    private String notificationToken;

    public Citizen() {

    }

    @JsonCreator
    public Citizen(String firstName, String lastName, String notificationToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.notificationToken = notificationToken;
    }
}
