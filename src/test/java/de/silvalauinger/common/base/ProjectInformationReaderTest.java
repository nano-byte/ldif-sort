package de.silvalauinger.common.base;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ProjectInformationReaderTest {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static String TEST_VERSION = "1.0-SNAPSHOT";
    private final static String TEST_COPYING = "TESTCOPYING";
    private final static String[] TEST_AUTHORS = new String[]{
	"Author1 <authormail1@domain1.tld1>",
	"Author2 <authormail2@domain2.tld2>",
	"Author3 <authormail3@domain3.tld3>"};
    //</editor-fold>

    @Test
    public void testRead() throws Exception {
	final ProjectInformation projectInformation = new DefaultProjectInformationReaderFactory().get().read();
	assertThat(projectInformation.getVersion(), is(TEST_VERSION));
	assertThat(projectInformation.getCopying(), is(TEST_COPYING));
	assertThat(projectInformation.getAuthors(), contains(TEST_AUTHORS));
    }
}