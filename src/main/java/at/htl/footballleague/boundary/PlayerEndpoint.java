package at.htl.footballleague.boundary;

import at.htl.footballleague.entity.Player;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("player")
public class PlayerEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@PathParam("id") long id) {
        return em.find(Player.class, id);
    }

    @GET
    @Path("allofaustria")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAustrianPlayers() {
        List<Player> austrianPlayers = em.createNamedQuery("Player.findAllAustrianPlayers", Player.class)
                .setParameter("ID", Long.parseLong("1"))
                .getResultList();
        return Response.ok(austrianPlayers).build();
    }

    @GET
    @Path("allofgermany")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGermanPlayers() {
        List<Player> germanPlayers = em.createNamedQuery("Player.findAllGermanPlayers", Player.class)
                .setParameter("ID", Long.parseLong("2"))
                .getResultList();
        return Response.ok(germanPlayers).build();
    }
}
