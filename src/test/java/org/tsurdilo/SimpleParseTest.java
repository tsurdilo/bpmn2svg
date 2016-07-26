package org.tsurdilo;


import org.eclipse.bpmn2.*;
import org.eclipse.bpmn2.Process;
import org.junit.Test;
import org.tsurdilo.util.TestUtil;

import static org.junit.Assert.assertNotNull;

public class SimpleParseTest {
    private TestUtil testUtil = new TestUtil();

    @Test
    public void testParseBpmn2() throws Exception {
        Definitions definitions = testUtil.getDefinitionsFromBpmn2(SimpleParseTest.class, "simpletestprocess.bpmn2");

        assertNotNull(definitions);
        Process process = testUtil.getRootProcess(definitions);
        assertNotNull(process);
    }
}
