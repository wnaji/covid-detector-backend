package be.ipl.pfe.repositories;

import be.ipl.pfe.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    @Query(value = "SELECT * FROM locations WHERE establishment_id = ?1", nativeQuery = true)
    List<Location> findLocationsByEstablishmentId(String id);
}
