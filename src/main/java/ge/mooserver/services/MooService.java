package ge.mooserver.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;

@Path("Moo")
public class MooService {
    private Farm farm = new Farm();
    @GET
    public String  get() throws IOException {
        System.out.println("get");
        return farm.getCows().toString();
    }

    @GET
    @Path("{cowId}")
    public String  getByID( @PathParam("cowId") int id) throws IOException {
        System.out.println("get :" + id);
        return farm.getCowInfo(id).toString();
    }


    @GET
    @Path("clear")
    public Response  cleat() throws IOException, URISyntaxException {
        System.out.println("clear");
        farm.clear();
        return Response.ok().build();
    }


    @POST
    @Path("update")
    public Response postUpdate(@QueryParam("cowId") int id, @QueryParam("lon") double lat, @QueryParam("lat")double lon) {
        System.out.println("post");
        long time = System.currentTimeMillis();
        try {
            farm.updateCow(id, time, lat, lon);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @POST
    @Path("register")
    public Response postReister(@QueryParam("cowId") int id, @QueryParam("name") String name) {
        try {
            farm.registerCow(name, id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

}
