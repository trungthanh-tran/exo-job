package org.exoplatform.samples;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.exoplatform.services.organization.GroupHandler;
import org.exoplatform.services.organization.Group;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.log.ExoLogger;


public class SpaceCleanerService implements Job{

  static Log log = ExoLogger.getLogger(SpaceCleanerService.class);
  String groupId = "test";

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    try {
      GroupHandler groupHandler = CommonsUtils.getService(OrganizationService.class).getGroupHandler();
      Group group = groupHandler.findGroupById(groupId);
      if(group != null) {
        groupHandler.removeGroup(group, true);
      } else {
        log.warn(groupId + " group does not exist!");
      }
    } catch (Exception e) {
      log.warn("Exception occurs when removing " + groupId, e);
    }
  }
}