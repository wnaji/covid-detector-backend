package be.ipl.pfe.repositories;

import be.ipl.pfe.models.LocationScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationScanRepository extends JpaRepository<LocationScan, String> {
}
