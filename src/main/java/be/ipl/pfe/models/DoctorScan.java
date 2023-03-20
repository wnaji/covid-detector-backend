package be.ipl.pfe.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "doctors_scans")
@Data
public class DoctorScan {
    @Id
    private String doctorQRCode;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Citizen citizen;

    public DoctorScan() {
    }

    public DoctorScan(String doctorQRCode, Doctor doctor, Citizen citizen) {
        this.doctorQRCode = doctorQRCode;
        this.doctor = doctor;
        this.citizen = citizen;
    }
}
