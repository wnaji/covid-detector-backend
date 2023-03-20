package be.ipl.pfe.services;

import be.ipl.pfe.models.LocationScan;
import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.repositories.LocationScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LocationScanService {
    @Autowired
    private LocationScanRepository locationScanRepository;

    @Autowired
    @Qualifier("UuidGenerator")
    private IdGenerator idGenerator;

    public LocationScan createLocationScan(LocationScan locationScan) {
        locationScan.setId(this.idGenerator.generate());
        locationScan.setVisitDateTime(LocalDateTime.now());
        return this.locationScanRepository.save(locationScan);
    }
}
