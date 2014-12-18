package de.nanobyte.common.base;

import java.util.*;
import static java.util.Objects.requireNonNull;

/**
 * This class is a container for some basic project information like the project
 * version, the copying information and the project authors.
 */
public final class ProjectInformation {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String programName;
    private final String version;
    private final String copying;
    private final List<String> authors;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ProjectInformation(final String programName, final String version, final String copying, final Collection<String> authors) {
        this.programName = requireNonNull(programName);
        this.version = requireNonNull(version);
        this.copying = requireNonNull(copying);
        this.authors = Collections.unmodifiableList(new ArrayList<>(requireNonNull(authors)));
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

    public List<String> getAuthors() {
        return authors;
    }
}
