package swf.army.mil.aircraft.aircraft;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import swf.army.mil.aircraft.pilot.Pilot;

@Entity
public class Aircraft {

    @Id
    private long id;
    private String airframe;

    @ManyToOne
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    public Aircraft(long id, String airframe, Pilot pilot) {
        this.id = id;
        this.airframe = airframe;
        this.pilot = pilot;
    }

    public Aircraft() {
        this(0, "none", null);
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getAirframe() {return airframe;}
    public void setAirframe(String airframe) {this.airframe = airframe;}
    public Pilot getPilot() {return pilot; }
    public void setPilot(Pilot pilot) {this.pilot = pilot;}
}
