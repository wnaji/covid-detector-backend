package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.AlreadyExistsException;
import be.ipl.pfe.models.DoctorScan;
import be.ipl.pfe.models.Note;
import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.repositories.CitizenRepository;
import be.ipl.pfe.repositories.DoctorScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorScanService {

    @Autowired
    private DoctorScanRepository doctorScanRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    @Qualifier("UuidGenerator")
    private IdGenerator idGenerator;


    public DoctorScan createScan(DoctorScan doctorScan) {
        if (this.doctorScanRepository.existsById(doctorScan.getDoctorQRCode()))
            throw new AlreadyExistsException(doctorScan.getDoctorQRCode());
        //TODO send notifications
        List<String> tokens = this.citizenRepository.getPotentialInfectedCitizens(doctorScan.getCitizen().getId());
        if (tokens.size() > 0) {
            Note note = new Note("Notification", "Vous avez été en contact avec une personne infectée dans les 10 derniers jours, veuillez consulter un médecin");
            this.notificationService.sendNotifications(note, tokens);
        }
        return this.doctorScanRepository.save(doctorScan);
    }

}
