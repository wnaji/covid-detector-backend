package be.ipl.pfe.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locations_scans")
@Data
public class LocationScan {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Citizen citizen;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Location location;

    @Column(name = "visit_date_time", nullable = false)
    private LocalDateTime visitDateTime;

    @Column(name = "exit_date_time")
    private LocalDateTime exitDateTime;

    public LocationScan() {
    }

    public LocationScan(Location location, Citizen citizen) {
        this.location = location;
        this.citizen = citizen;
    }
}
