package at.htl.footballleague.control;

import at.htl.footballleague.entity.Player;
import at.htl.footballleague.entity.Team;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    private static final String PLAYERS_TEAMS_FILE_NAME = "players-teams.csv";

    @PersistenceContext
    EntityManager em;

    @Transactional
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("*** it works ***");

        /*Player messi = new Player("Lionel", "Messi", (long) 3, 10);
        em.persist(messi);
        em.persist(new Player("Frenkie", "De Jong", (long) 3, 21));
        em.persist(new Player("Gianluigi", "Buffon", (long) 5, 1));*/

        readPlayersAndTeamsFromFile(PLAYERS_TEAMS_FILE_NAME);
    }

    private void readPlayersAndTeamsFromFile(String playersTeamsFileName) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(playersTeamsFileName);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8)) {
            stream.skip(1).map((String s) -> s.split(";"))
                    .forEach(this::persistPlayersAndTeams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void persistPlayersAndTeams(String[] line) {
        Team team = null;
        try {
            team = em.createNamedQuery("Team.findByName", Team.class)
                    .setParameter("NAME", line[3])
                    .getSingleResult();
        } catch (NoResultException e) {
            team = new Team(line[3], Integer.parseInt(line[4]));
            em.persist(team);
        }
        Player player = null;
        try {
            player = em.createNamedQuery("Player.findByFirstAndLastName", Player.class)
                    .setParameter("FIRSTNAME", line[0]).setParameter("LASTNAME", line[1])
                    .getSingleResult();
        } catch (NoResultException ex) {
            player = new Player(line[0], line[1], team.getId(), Integer.parseInt(line[2]));
            em.persist(player);
        }
    }


    public void tearDown(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        //when app is undeployed
    }
}
