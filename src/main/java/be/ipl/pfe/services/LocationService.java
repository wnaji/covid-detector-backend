package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.NotFoundException;
import be.ipl.pfe.models.Location;
import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    @Qualifier("UuidGenerator")
    private IdGenerator idGenerator;

    public Location createLocation(Location location) {
        location.setId(this.idGenerator.generate());
        return this.locationRepository.save(location);
    }

    public List<Location> getEstablishmentLocations(String id) {
        return this.locationRepository.findLocationsByEstablishmentId(id);
    }

    public Location getLocationById(String locationId) {
        return this.locationRepository.findById(locationId).orElseThrow(() -> new NotFoundException("location", "id", locationId));
    }
}