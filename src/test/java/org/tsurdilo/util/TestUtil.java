package org.tsurdilo.util;

import org.eclipse.bpmn2.*;
import org.eclipse.bpmn2.Process;
import org.jbpm.designer.web.profile.impl.JbpmProfileImpl;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {

    public Definitions getDefinitionsFromBpmn2(Class testClass, String fileName) throws Exception {
        URL fileURL = testClass.getResource(fileName);
        String bpmn2str = new String(Files.readAllBytes(Paths.get(fileURL.toURI())));

       return new JbpmProfileImpl().getDefinitions(bpmn2str);
    }

    public org.eclipse.bpmn2.Process getRootProcess(Definitions def) {
        for(RootElement nextRootElement : def.getRootElements()) {
            if(nextRootElement instanceof Process) {
                return (Process) nextRootElement;
            }
        }
        return null;
    }
}
