package at.htl.footballleague.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Team.findByName",
                query = "select t from Team t where t.name = :NAME"
        )
})
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    long id;
    private String name;
    private int totalPoints;

    public Team() {

    }

    public Team(String name, int totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public long getId() {
        return id;
    }

    /*public void setId(long id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return String.format("%d: %s %s", id, name, totalPoints);
    }
}
