package be.ipl.pfe.services;

import be.ipl.pfe.exceptions.NotFoundException;
import be.ipl.pfe.models.Doctor;
import be.ipl.pfe.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor getDoctorById(String doctorId) {
        return this.doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("doctor", "id", doctorId));
    }
}
