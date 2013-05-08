package de.nanobyte.common.base;

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * This class is a container for some basic project information like the project
 * version, the copying information and the project authors.
 *
 * @author <a href="mailto:simonsilvalauinger@gmail.com">Simon E. Silva
 * Lauinger</a>
 */
public final class ProjectInformation {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String programName;
    private final String version;
    private final String copying;
    private final ImmutableList<String> authors;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ProjectInformation(final String programName, final String version, final String copying, final List<String> authors) {
	this.programName = checkNotNull(programName);
	this.version = checkNotNull(version);
	this.copying = checkNotNull(copying);
	this.authors = ImmutableList.copyOf(authors);
    }
    //</editor-fold>

    public String getProgramName() {
	return programName;
    }

    public String getVersion() {
	return version;
    }

    public String getCopying() {
	return copying;
    }

    public ImmutableList<String> getAuthors() {
	return authors;
    }
}