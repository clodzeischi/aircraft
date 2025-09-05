package swf.army.mil.aircraft.aircraft;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    private ArrayList<Aircraft> aircraftList = new ArrayList<>();

    public AircraftService(AircraftRepository r) {
        this.aircraftRepository = r;
    }

    public Aircraft saveAircraft(Aircraft a) {
        aircraftList.add(a);
        return a;
    }

    public Aircraft getAircraftByID(Long id) {
        return null;
    }

    public ArrayList<Aircraft> getAllAircraft() {
        return aircraftList;
    }
}
