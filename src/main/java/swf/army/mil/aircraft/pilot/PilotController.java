package swf.army.mil.aircraft.pilot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService s) {
        this.pilotService = s;
    }

    @PostMapping
    public ResponseEntity<Pilot> createPilot(@RequestBody Pilot pilot) {
        return new ResponseEntity<>(
                pilotService.savePilot(pilot),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pilot>> getAllPilots() {
        return new ResponseEntity<>(
                pilotService.getAllPilots(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pilot> getPilotByID(@PathVariable Long id) {
        return new ResponseEntity<>(
                pilotService.getPilotByID(id),
                HttpStatus.OK);
    }
}
