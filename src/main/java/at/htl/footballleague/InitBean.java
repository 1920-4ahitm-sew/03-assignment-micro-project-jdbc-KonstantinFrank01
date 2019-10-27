package at.htl.footballleague;

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
    }

    public void tearDown(@Observes @Destroyed(ApplicationScoped.class) Object init) {

    }
}
