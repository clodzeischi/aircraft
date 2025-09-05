package swf.army.mil.aircraft.aircraft;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    @PostMapping
    public void handlePostMapping() {
        System.out.println("Hello world");
    }

}
