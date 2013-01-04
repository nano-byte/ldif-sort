package de.silvalauinger.common.base;

import com.google.common.base.Supplier;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:simonsilvalauinger@gmail.com">Simon E. Silva
 * Lauinger</a>
 */
public final class DefaultProjectInformationReaderFactory implements Supplier<ProjectInformationReader> {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static Path MANIFEST_MF_PATH = Paths.get("META-INF/MANIFEST.MF");
    private final static Path COPYING_PATH = Paths.get("COPYING");
    private final static Path AUTHORS_PATH = Paths.get("AUTHORS");
    private final static String MANIFEST_VERSION_KEY = "Implementation-Version";
    //</editor-fold>

    /**
     * Creates a {@link ProjectInformationReader} by using default project
     * files.
     *
     * These files are (relative to the project base path):
     * <code>
     * <ul>
     * <li>META-INF/MANIFEST.MF (must have the attribute "Implementation-Version")</li>
     * <li>COPYING (must contain the hint to the project license)</li>
     * <li>AUTHORS (must contain every author in a new line)</li>
     * <ul>
     * </code>
     *
     * @return The generated {@link ProjectInformationReader}.
     */
    @Override
    public ProjectInformationReader get() {
	return new ProjectInformationReader(MANIFEST_MF_PATH, COPYING_PATH, AUTHORS_PATH, MANIFEST_VERSION_KEY);
    }
}
