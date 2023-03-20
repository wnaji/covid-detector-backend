package be.ipl.pfe.repositories;

import be.ipl.pfe.models.DoctorScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorScanRepository extends JpaRepository<DoctorScan, String> {
}
