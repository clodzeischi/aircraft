package swf.army.mil.aircraft.aircraft;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService s) {
        this.aircraftService = s;
    }

    @PostMapping
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft) {
        return new ResponseEntity<>(
                aircraftService.saveAircraft(aircraft),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Aircraft>> getAllAircraft() {
        return new ResponseEntity<>(
                aircraftService.getAllAircraft(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        return new ResponseEntity<>(
                aircraftService.getAircraftByID(id),
                HttpStatus.OK);
    }
}
