package swf.army.mil.aircraft.aircraft;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swf.army.mil.aircraft.pilot.Pilot;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AircraftController.class)
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AircraftService aircraftService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAnAircraft() throws Exception {

        final Aircraft b17 = new Aircraft(2, "B17", new Pilot());
        String b17JSON = objectMapper.writeValueAsString(b17);
        when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(b17);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(b17JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.airframe").value("B17"))
                .andExpect(jsonPath("$.pilot.*", hasSize(4)));
        verify(aircraftService).saveAircraft(any(Aircraft.class));
    }

    @Test
    void shouldGetAllAircraft() throws Exception {

        // Arrange
        ArrayList<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(new Aircraft(1, "Biplane", null));
        aircraftList.add(new Aircraft(2, "Triplane", null));
        when(aircraftService.getAllAircraft()).thenReturn(aircraftList);

        // Act
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));

        // Assert
        verify(aircraftService).getAllAircraft();
    }

    @Test
    void shouldGetAircraftByID() throws Exception {
        final Aircraft b17 = new Aircraft(17L, "Deathstar", null);
        when(aircraftService.getAircraftByID(17L)).thenReturn(b17);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft/17"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("17"));
        verify(aircraftService).getAircraftByID(17L);
    }
 }
