package com.lschaan.dog;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/dog")
public class DogResource {

    private static final Logger logger = LoggerFactory.getLogger(DogResource.class);

    @GET
    @Path("{id}")
    public Dog get(@PathParam("id") String id) {
        logger.info("Finding dog for id {}", id);
        return Dog.findById(new ObjectId(id));
    }

    @GET
    public List<Dog> list(@QueryParam("name") String name, @QueryParam("age") Integer age) {
        logger.info("Listing dogs for params name {} and age {}", name, age);

        Map<String, Object> params = new HashMap<>();
        if (name != null) params.put("name", name);
        if (age != null) params.put("age", age);

        if (params.isEmpty()) {
            return Dog.listAll();
        }

        return Dog.list(params);
    }

    @POST
    public Dog create(Dog dog) {
        logger.info("Creating dog for request {}", dog);
        dog.persist();
        return dog;
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) {
        Dog.deleteById(new ObjectId(id));
    }

    @PUT
    @Path("{id}")
    public Dog update(@PathParam("id") String id, Dog request) {
        Dog dog = Dog.findById(id);

        if (dog == null) throw new NotFoundException();

        if (request.getName() != null) dog.setName(request.getName());
        if (request.getAge() != null) dog.setAge(request.getAge());

        dog.update();
        return dog;
    }

}