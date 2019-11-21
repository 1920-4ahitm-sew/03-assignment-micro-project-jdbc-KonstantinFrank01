package at.htl.footballleague.control;

import at.htl.footballleague.entity.Player;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class InitBean {

    @PersistenceContext
    EntityManager em;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("*** it works ***");

        Player messi = new Player("Lionel", "Messi", (long) 3, 10);
        em.persist(messi);
        em.persist(new Player("Frenkie", "De Jong", (long) 3, 21));
        em.persist(new Player("Gianluigi", "Buffon", (long) 5, 1));
    }

    public void tearDown(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        //when app is undeployed
    }
}
