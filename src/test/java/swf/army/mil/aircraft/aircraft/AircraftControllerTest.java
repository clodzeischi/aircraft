package swf.army.mil.aircraft.aircraft;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
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

        final Aircraft b17 = new Aircraft(2, "B17", "Bob");
        String b17JSON = objectMapper.writeValueAsString(b17);
        Mockito.when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(b17);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(b17JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.airframe").value("B17"))
                .andExpect(jsonPath("$.pilot").value("Bob"));
        Mockito.verify(aircraftService).saveAircraft(any(Aircraft.class));
    }

    @Test
    void shouldGetAllAircraft() throws Exception {
        ArrayList<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(new Aircraft(1, "Biplane", "Alice"));
        aircraftList.add(new Aircraft(2, "Triplane", "Bob"));

        Mockito.when(aircraftService.getAllAircraft()).thenReturn(aircraftList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));
        Mockito.verify(aircraftService).getAllAircraft();
    }

    @Test
    void shouldGetAircraftByID() throws Exception {
        final Aircraft b17 = new Aircraft(17L, "Deathstar", "Darth");
        Mockito.when(aircraftService.getAircraftByID(17L)).thenReturn(b17);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft/17"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("17"));
        Mockito.verify(aircraftService).getAircraftByID(17L);
    }
 }
