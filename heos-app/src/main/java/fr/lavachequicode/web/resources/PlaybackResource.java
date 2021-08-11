package fr.lavachequicode.web.resources;

import fr.lavachequicode.heos.sdk.model.PositionInfo;
import fr.lavachequicode.services.HeosPlaybackService;
import fr.lavachequicode.web.dto.PlaybackStatusDto;
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
  public PlaybackStatusDto status(@PathParam("id") String zoneId) {
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

  @GET()
  @Path("/next/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void next(@PathParam("id") String zoneId) {
    heosPlaybackService.next(zoneId);
  }

  @GET()
  @Path("/previous/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void previous(@PathParam("id") String zoneId) {
    heosPlaybackService.previous(zoneId);
  }

  @GET()
  @Path("/volume/{id}/{volume}")
  @Produces(MediaType.APPLICATION_JSON)
  public void volume(@PathParam("id") String zoneId, @PathParam("volume") Integer volume) {
    heosPlaybackService.volume(zoneId, volume);
  }

}
