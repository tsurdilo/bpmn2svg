package org.tsurdilo.util;

import bpsim.impl.BpsimPackageImpl;
import org.eclipse.bpmn2.*;
import org.eclipse.bpmn2.Process;
import org.jboss.drools.impl.DroolsPackageImpl;
import org.jbpm.designer.web.profile.IDiagramProfile;
import org.jbpm.designer.web.profile.impl.DefaultProfileImpl;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {
    DefaultProfileImpl profile = new DefaultProfileImpl();
    IDiagramProfile.IDiagramUnmarshaller marshaller = profile.createUnmarshaller();
    IDiagramProfile.IDiagramMarshaller unmarshaller = profile.createMarshaller();

    public Definitions getDefinitionsFromBpmn2(Class testClass, String fileName) throws Exception {
        URL fileURL = testClass.getResource(fileName);
        String bpmn2str = new String(Files.readAllBytes(Paths.get(fileURL.toURI())));

        DroolsPackageImpl.init();
        BpsimPackageImpl.init();
        String processJson = marshaller.parseModel(bpmn2str, profile, "Email,HelloWorkItemHandler,Log,Rest,WebService");

        return unmarshaller.getDefinitions(processJson, "Email,HelloWorkItemHandler,Log,Rest,WebService");
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
