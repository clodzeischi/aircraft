package swf.army.mil.aircraft.pilot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swf.army.mil.aircraft.aircraft.Aircraft;
import swf.army.mil.aircraft.aircraft.AircraftRepository;
import swf.army.mil.aircraft.aircraft.AircraftService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PilotServiceTest {

    @Mock
    PilotRepository pilotRepository;

    @InjectMocks
    PilotService pilotService;

    @Test
    void shouldSavePilot() {

        // Arrange: for command from service to repo
        Pilot testPilot = new Pilot(3L, "Bob", "Ross", 49);
        when(pilotRepository.save(testPilot)).thenReturn(testPilot);

        // Act: send command from service to repo to save a pilot
        Pilot actualPilot = pilotService.savePilot(testPilot);

        // Assert: that repo received the command.
        verify(pilotRepository, times(1))
                .save(any(Pilot.class));
        assertThat(actualPilot).isEqualTo(testPilot);
    }

    @Test
    void shouldGetPilotByID() {

        // Arrange
        Pilot testPilot = new Pilot(3L, "Bob", "Ross", 49);
        when(pilotRepository.findById(3L)).thenReturn(Optional.of(testPilot));

        // Act
        Pilot actualPilot = pilotService.getPilotByID(3L);

        // Assert
        verify(pilotRepository, times(1))
                .findById(any(Long.class));
        assertThat(actualPilot).isEqualTo(testPilot);
    }

    @Test
    void shouldGetAllPilots() {

        // Arrange
        List<Pilot> allPilots = new ArrayList<Pilot>();
        allPilots.add(new Pilot(8L, "Darth", "Vader", 50));
        when(pilotRepository.findAll()).thenReturn(allPilots);

        // Act
        List<Pilot> actualPilots = pilotService.getAllPilots();

        // Assert
        verify(pilotRepository, times(1))
                .findAll();
        assertThat(actualPilots).isEqualTo(allPilots);
    }
}
