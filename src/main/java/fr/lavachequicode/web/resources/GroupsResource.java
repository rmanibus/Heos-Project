package fr.lavachequicode.web.resources;

import fr.lavachequicode.model.Group;
import fr.lavachequicode.services.HeosGroupService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/api/groups")
@Slf4j
public class GroupsResource {

    @Inject
    HeosGroupService heosGroupService;

    @GET()
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Group> list() {
        return heosGroupService.getGroups();
    }

    @Data
    @AllArgsConstructor
    public static class GroupDto {
        String udn;
        String friendlyName;
        String status;
    }

}
