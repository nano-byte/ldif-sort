package de.nanobyte.common.base;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

public final class DefaultProjectInformationReaderFactory implements Supplier<ProjectInformationReader> {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static Path PROGRAM_NAME_PATH = Paths.get("PROGRAM");
    private final static Path VERSION_PATH = Paths.get("VERSION");
    private final static Path COPYING_PATH = Paths.get("COPYING");
    private final static Path AUTHORS_PATH = Paths.get("AUTHORS");
    //</editor-fold>

    /**
     * Creates a {@link ProjectInformationReader} by using default project
     * files.
     *
     * These files are (relative to the project base path):
     * <code>
     * <ul>
     * <li>PROGRAM (must contain the program name)</li>
     * <li>VERSION (must contain the program version)</li>
     * <li>COPYING (must contain the hint to the project license)</li>
     * <li>AUTHORS (must contain every author in a new line)</li>
     * <ul>
     * </code>
     *
     * @return The generated {@link ProjectInformationReader}.
     */
    @Override
    public ProjectInformationReader get() {
	return new ProjectInformationReader(PROGRAM_NAME_PATH, VERSION_PATH, COPYING_PATH, AUTHORS_PATH);
    }
}
