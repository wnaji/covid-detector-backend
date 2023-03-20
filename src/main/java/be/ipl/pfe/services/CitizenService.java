package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.NotFoundException;
import be.ipl.pfe.models.Citizen;
import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    @Qualifier("UuidGenerator")
    private IdGenerator idGenerator;

    public Citizen getCitizenById(String citizenId) {
        return this.citizenRepository.findById(citizenId).orElseThrow(() -> new NotFoundException("citizen", "id", citizenId));
    }

    public Citizen createCitizen(Citizen citizen) {
        citizen.setId(this.idGenerator.generate());
        return this.citizenRepository.save(citizen);
    }

    public Citizen getCitizenByNotificationToken(String notificationToken) {
        return this.citizenRepository.findByNotificationToken(notificationToken).orElse(null);
    }
}
