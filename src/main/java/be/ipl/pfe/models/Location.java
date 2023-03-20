package be.ipl.pfe.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Data
public class Location {

    @Id
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty(required = true, access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(referencedColumnName = "id")
    private Establishment establishment;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Location() {
    }

    public Location(String id, Establishment establishment, String name, String description) {
        this.id = id;
        this.establishment = establishment;
        this.name = name;
        this.description = description;
    }
}
