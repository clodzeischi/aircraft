package swf.army.mil.aircraft.aircraft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {

    // Create simulated repository class
    @Mock
    AircraftRepository aircraftRepository;

    // Link the mock repo to service layer
    @InjectMocks
    AircraftService aircraftService;


    @Test
    void shouldSaveAircraft() {

        // Arrange: fork command from service to repository, and return a fake plane.
        Aircraft testAircraft = new Aircraft(12, "Normandy", null);
        when(aircraftRepository.save(testAircraft)).thenReturn((testAircraft));

        // Act: send the command from service to repo to save an aircraft.
        Aircraft actualAircraft = aircraftService.saveAircraft(testAircraft);

        // Assert: that repo received a call.
        verify(aircraftRepository, times(1))
                .save(any(Aircraft.class));
        assertThat(actualAircraft).isEqualTo(testAircraft);
    }

    @Test
    void shouldGetAircraftByID() {

        // Arrange: intercept call from service to repo
        Aircraft testAircraft = new Aircraft(12L, "Normandy", null);
        when(aircraftRepository.findById(12L)).thenReturn(Optional.of(testAircraft));

        // Act: send command to retrieve AC by ID
        Aircraft actualAircraft = aircraftService.getAircraftByID(12L);

        // Assert: check to make sure repo is receiving the call
        verify(aircraftRepository, times(1))
                .findById(any(Long.class));
        assertThat(actualAircraft).isEqualTo(testAircraft);
    }

    @Test
    void shouldGetAllAircraft() {

        // Arrange: intercept call from service to repo
        List<Aircraft> allAircraft = new ArrayList<Aircraft>();
        allAircraft.add(new Aircraft(8, "Death Star", null));
        when(aircraftRepository.findAll()).thenReturn(allAircraft);

        // Act: send command to Service
        List<Aircraft> actualACs = aircraftService.getAllAircraft();

        // Assert: check to make sure that repo received the command
        verify(aircraftRepository, times(1))
                .findAll();
        assertThat(actualACs).isEqualTo(allAircraft);
    }
}