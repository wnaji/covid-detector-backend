package be.ipl.pfe.services;

import be.ipl.pfe.ports.IdGenerator;
import be.ipl.pfe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QRCodeService {

	@Autowired
	@Qualifier("UuidGenerator")
	private IdGenerator idGenerator;

	public List<String> generate(int amount, String doctorId) {
		List<String> hashes = new ArrayList<>();
		for (int i = 0; i < amount; i++)
			hashes.add(JwtUtils.createQRCodeContentToken(doctorId, this.idGenerator.generate()));
		return hashes;
	}

}
