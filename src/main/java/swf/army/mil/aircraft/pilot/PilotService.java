package swf.army.mil.aircraft.pilot;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {

    private PilotRepository pilotRepository;

    public PilotService(PilotRepository r) {
        this.pilotRepository = r;
    }

    public Pilot savePilot(Pilot p) {
        return pilotRepository.save(p);
    }

    public Pilot getPilotByID(Long id) {
        return pilotRepository.findById(id).get();
    }

    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }
}
