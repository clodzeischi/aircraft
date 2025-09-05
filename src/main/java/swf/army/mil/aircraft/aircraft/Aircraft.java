package swf.army.mil.aircraft.aircraft;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aircraft {

    @Id
    private long id;
    private String airframe;
    private String pilot;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getAirframe() { return airframe; }
    public void setAirframe(String airframe) { this.airframe = airframe; }
    public String getPilot() { return pilot; }
    public void setPilot(String pilot) { this.pilot = pilot; }

    public Aircraft(long id, String airframe, String pilot) {
        this.id = id;
        this.airframe = airframe;
        this.pilot = pilot;
    }

    public Aircraft() {
        this(0, "none", "none");
    }
}
