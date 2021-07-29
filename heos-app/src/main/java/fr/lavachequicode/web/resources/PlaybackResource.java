package fr.lavachequicode.web.resources;

import fr.lavachequicode.heos.sdk.model.AVTCurrentState;
import fr.lavachequicode.services.HeosPlaybackService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/playback")
@Slf4j
public class PlaybackResource {

  @Inject
  HeosPlaybackService heosPlaybackService;

  @GET()
  @Path("/status/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public AVTCurrentState status(@PathParam("id") String zoneId) {
    return heosPlaybackService.getStatus(zoneId);
  }

  @GET()
  @Path("/play/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void play(@PathParam("id") String zoneId) {
    heosPlaybackService.play(zoneId);
  }

  @GET()
  @Path("/pause/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void pause(@PathParam("id") String zoneId) {
    heosPlaybackService.pause(zoneId);
  }
}
