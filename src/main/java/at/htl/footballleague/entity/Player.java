package at.htl.footballleague.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Player.findByShirtNo",
                query = "select p from Player p where p.shirtNumber = :SHIRTNO"
        )
})
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Long teamId;
    private int shirtNumber;

    public Player(String firstName, String lastName, Long teamId, int shirtNumber) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setTeamId(teamId);
        this.setShirtNumber(shirtNumber);
    }

    public Player() {

    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    @Override
    public String toString() {
        return String.format("%d: %s %s %s %s", + id, firstName, lastName, teamId, shirtNumber);
    }
}
