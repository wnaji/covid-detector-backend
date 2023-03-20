package be.ipl.pfe.adapters;

import be.ipl.pfe.ports.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component(value = "UuidGenerator")
public class UuidGenerator implements IdGenerator {
	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}
}
