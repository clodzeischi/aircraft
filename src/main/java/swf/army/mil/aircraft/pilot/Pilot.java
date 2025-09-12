package swf.army.mil.aircraft.pilot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pilot {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private int age;

    public Pilot(Long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Pilot() {this(0L, "none", "none", 0);}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
}
