package org.camunda.bpm.cockpit.plugin.sample.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginRootResource;
import org.camunda.bpm.cockpit.plugin.sample.SamplePlugin;
import org.camunda.bpm.cockpit.plugin.sample.dto.*;
import org.camunda.bpm.cockpit.plugin.sample.resources.Email.EmailResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.News.FeedResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.News.NewsResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.Report.CommentResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.Report.ReportResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation.SocialInformationDeveloperResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation.SocialInformationRaterResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation.SocialInformationResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation.SocialInformationTagResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.SocialProfile.SocialProfileResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.Wiki.PartResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.Wiki.RevisionResource;
import org.camunda.bpm.cockpit.plugin.sample.resources.Wiki.WikiResource;


@Path("plugin/" + SamplePlugin.ID)
public class SamplePluginRootResource extends AbstractCockpitPluginRootResource {

  public SamplePluginRootResource() {
    super(SamplePlugin.ID);
  }

  @Path("{engineName}/social-profile")
  public SocialProfileResource getSocialProfile(@PathParam("engineName") String engineName, @QueryParam("userId") String userId) {
    return  subResource(new SocialProfileResource(engineName, userId), engineName);
  }

  @Path("{engineName}/social-profile/post")
  public SocialProfileResource postSocialProfile(@PathParam("engineName") String engineName, SocialProfileDto user) {
    return  subResource(new SocialProfileResource(engineName, user), engineName);
  }

  @Path("{engineName}/social-information")
  public SocialInformationResource getSocialInformation(@PathParam("engineName") String engineName, @QueryParam("processId") String processId) {
    return  subResource(new SocialInformationResource(engineName, processId), engineName);
  }

  @Path("{engineName}/social-information/post")
  public SocialInformationResource postSocialInformation(@PathParam("engineName") String engineName, SocialInformationDto socialInformation) {
    return  subResource(new SocialInformationResource(engineName, socialInformation), engineName);
  }

  @Path("{engineName}/social-information/update")
  public SocialInformationResource updateSocialInformation(@PathParam("engineName") String engineName, SocialInformationDto socialInformation) {
    return  subResource(new SocialInformationResource(engineName, socialInformation), engineName);
  }

  @Path("{engineName}/social-information/rater/update")
  public SocialInformationRaterResource updateRater(@PathParam("engineName") String engineName, RaterDto rater) {
    return  subResource(new SocialInformationRaterResource(engineName, rater), engineName);
  }

  @Path("{engineName}/social-information/rater")
  public SocialInformationRaterResource postRater(@PathParam("engineName") String engineName, RaterDto rater) {
    return  subResource(new SocialInformationRaterResource(engineName, rater), engineName);
  }

  @Path("{engineName}/social-information/tag")
  public SocialInformationTagResource postTag(@PathParam("engineName") String engineName, TagDto tag) {
    return  subResource(new SocialInformationTagResource(engineName, tag), engineName);
  }

  @Path("{engineName}/social-information/tag/delete")
  public SocialInformationTagResource deleteTag(@PathParam("engineName") String engineName, @QueryParam("tagId") int tagId) {
    return  subResource(new SocialInformationTagResource(engineName, tagId), engineName);
  }

  @Path("{engineName}/social-information/developer/post")
  public SocialInformationDeveloperResource postDeveloper(@PathParam("engineName") String engineName, DeveloperDto developer) {
    return  subResource(new SocialInformationDeveloperResource(engineName, developer), engineName);
  }

  @Path("{engineName}/social-information/developer/delete")
  public SocialInformationDeveloperResource deleteDeveloper(@PathParam("engineName") String engineName, @QueryParam("developerId") int developerId) {
    return  subResource(new SocialInformationDeveloperResource(engineName, developerId), engineName);
  }

  @Path("{engineName}/wiki")
  public WikiResource getWiki(@PathParam("engineName") String engineName, @QueryParam("processId") String processId) {
    return  subResource(new WikiResource(engineName, processId), engineName);
  }

  @Path("{engineName}/wiki/post")
  public WikiResource postWiki(@PathParam("engineName") String engineName, WikiDto wiki) {
    return  subResource(new WikiResource(engineName, wiki), engineName);
  }

  @Path("{engineName}/wiki/part/post")
  public PartResource postPart(@PathParam("engineName") String engineName, PartDto part) {
    return  subResource(new PartResource(engineName, part), engineName);
  }

  @Path("{engineName}/wiki/part/update")
  public PartResource updatePart(@PathParam("engineName") String engineName, PartDto part) {
    return  subResource(new PartResource(engineName, part), engineName);
  }

  @Path("{engineName}/wiki/history")
  public RevisionResource getHistory(@PathParam("engineName") String engineName, @QueryParam("partId") String partId) {
    return  subResource(new RevisionResource(engineName, partId), engineName);
  }

  @Path("{engineName}/wiki/history/post")
  public RevisionResource postRevision(@PathParam("engineName") String engineName, RevisionDto revision) {
    return  subResource(new RevisionResource(engineName, revision), engineName);
  }

  @Path("{engineName}/report")
  public ReportResource getReport(@PathParam("engineName") String engineName, @QueryParam("processId") String processId) {
    return  subResource(new ReportResource(engineName, processId), engineName);
  }

  @Path("{engineName}/report/post")
  public ReportResource postReport(@PathParam("engineName") String engineName, ReportDto report) {
    return  subResource(new ReportResource(engineName, report), engineName);
  }

  @Path("{engineName}/report/update")
  public ReportResource updateReport(@PathParam("engineName") String engineName, ReportDto report) {
    return  subResource(new ReportResource(engineName, report), engineName);
  }

  @Path("{engineName}/report/comment/post")
  public CommentResource postComment(@PathParam("engineName") String engineName, CommentDto comment) {
    return  subResource(new CommentResource(engineName, comment), engineName);
  }

  @Path("{engineName}/news")
  public NewsResource getNews(@PathParam("engineName") String engineName, @QueryParam("max")int max ) {
    return  subResource(new NewsResource(engineName, max), engineName);
  }

  @Path("{engineName}/news/post")
  public NewsResource getNews(@PathParam("engineName") String engineName, NewsDto news) {
    return  subResource(new NewsResource(engineName, news), engineName);
  }

  @Path("{engineName}/feed")
  public FeedResource getFeed(@PathParam("engineName") String engineName, @QueryParam("profileId") String profileId, @QueryParam("max") int max) {
    return subResource(new FeedResource(engineName, profileId, max), engineName);
  }

  @Path("{engineName}/feed/post")
  public FeedResource postFeed(@PathParam("engineName") String engineName, FeedDto feed) {
    return subResource(new FeedResource(engineName, feed), engineName);
  }

  @Path("{engineName}/feed/delete")
  public FeedResource deleteFeed(@PathParam("engineName") String engineName,
                                 @QueryParam("profileId") String profileId, @QueryParam("objectKey") int objectKey, @QueryParam("type") String type) {
    return subResource(new FeedResource(engineName, profileId, objectKey, type), engineName);
  }

  @Path("{engineName}/email")
  public EmailResource sendEmail(@PathParam("engineName") String engineName) {
    return subResource(new EmailResource(engineName), engineName);
  }

}
