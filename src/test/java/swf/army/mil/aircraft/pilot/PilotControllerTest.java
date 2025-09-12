package swf.army.mil.aircraft.pilot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PilotController.class)
@AutoConfigureMockMvc
public class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PilotService pilotService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePilot() throws Exception {

        // Arrange: intercept command from controller to service
        final Pilot testPilot = new Pilot(5L, "Alice", "Smith", 24);
        String testPilotJSON = objectMapper.writeValueAsString(testPilot);
        when(pilotService.savePilot(any(Pilot.class))).thenReturn(testPilot);

        // Act: command controller to create AC
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/pilot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testPilotJSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.firstName").value("Alice"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.age").value(24));

        // Assert: check if the AC command was passed to service
        verify(pilotService).savePilot(any(Pilot.class));
    }

    @Test
    void shouldGetAllPilots() throws Exception {

        // Arrange: create a mock list, catch the command from controller to the service
        ArrayList<Pilot> pilots = new ArrayList<>();
        pilots.add(new Pilot(4L, "Alice", "Smith", 24));
        pilots.add(new Pilot(5L, "Bob", "Daniels", 28));
        when(pilotService.getAllPilots()).thenReturn(pilots);

        // Act: command controller to ask service for pilots
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)));

        // Assert: check to see if controller fired command to service
        verify(pilotService).getAllPilots();
    }

    @Test
    void shouldGetPilotByID() throws Exception {

        // Arrange: create mock pilot, intercept command from controller to service
        Pilot testPilot = new Pilot(7L, "Bob", "Ross", 39);
        when(pilotService.getPilotByID(7L)).thenReturn(testPilot);

        // Act
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot/7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));

        // Assert
        verify(pilotService).getPilotByID(7L);
    }
}
