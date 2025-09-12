package swf.army.mil.aircraft.aircraft;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository r) {
        this.aircraftRepository = r;
    }

    public Aircraft saveAircraft(Aircraft a) {
        return aircraftRepository.save(a);
    }

    public Aircraft getAircraftByID(Long id) {
        return aircraftRepository.findById(id).get();
    }

    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }
}
