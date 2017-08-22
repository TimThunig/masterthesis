package org.camunda.bpm.cockpit.plugin.sample;

import java.util.*;

import org.camunda.bpm.cockpit.plugin.sample.resources.SamplePluginRootResource;
import org.camunda.bpm.cockpit.plugin.spi.impl.AbstractCockpitPlugin;

public class SamplePlugin extends AbstractCockpitPlugin {

  public static final String ID = "sample-plugin";

  public String getId() {
    return ID;
  }

  @Override
  public Set<Class<?>> getResourceClasses() {
    Set<Class<?>> classes = new HashSet<Class<?>>();

    classes.add(SamplePluginRootResource.class);

    return classes;
  }

  @Override
  public List<String> getMappingFiles() {
    List<String> mappingFiles = new ArrayList<String>();
    mappingFiles.add("org/camunda/bpm/cockpit/plugin/sample/queries/socialProfile.xml");
    mappingFiles.add("org/camunda/bpm/cockpit/plugin/sample/queries/socialInformation.xml");
    mappingFiles.add("org/camunda/bpm/cockpit/plugin/sample/queries/news.xml");
    mappingFiles.add("org/camunda/bpm/cockpit/plugin/sample/queries/report.xml");
    mappingFiles.add("org/camunda/bpm/cockpit/plugin/sample/queries/wiki.xml");
    return mappingFiles;
  }
}
