package de.silvalauinger.common.base;

import com.google.common.base.Charsets;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.Manifest;

/**
 * @author <a href="mailto:simonsilvalauinger@gmail.com">Simon E. Silva
 * Lauinger</a>
 */
public final class ProjectInformationReader {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Path manifestMfPath;
    private final Path copyingPath;
    private final Path authorsPath;
    private final String manifestVersionKey;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ProjectInformationReader(final Path toManifestMf, final Path toCopying, final Path toAuthors, final String manifestVersionKey) {
	this.manifestMfPath = checkNotNull(toManifestMf);
	this.copyingPath = checkNotNull(toCopying);
	this.authorsPath = checkNotNull(toAuthors);
	this.manifestVersionKey = checkNotNull(manifestVersionKey);
    }
    //</editor-fold>

    /**
     * Generates a {@link ProjectInformation} by reading out information from
     * the default project files {@link ProjectInformationReader#manifestMfPath},
     * {@link ProjectInformationReader#copyingPath} and
     * {@link ProjectInformationReader#authorsPath}.
     *
     * @return A {@link ProjectInformation} generated by reading default project
     * files.
     * @throws IllegalArgumentException If {@link ProjectInformationReader#manifestMfPath},
     * {@link ProjectInformationReader#copyingPath} or
     * {@link ProjectInformationReader#authorsPath} could not be found.
     * @throws IOException If an I/O exception occurs while reading {@link ProjectInformationReader#manifestMfPath},
     * {@link ProjectInformationReader#copyingPath} or
     * {@link ProjectInformationReader#authorsPath}.
     */
    public ProjectInformation read() throws IllegalArgumentException, IOException {
	return new ProjectInformation(readVersionFromManifest(), readCopying(), readAuthors());
    }

    /**
     * Reads the program version from the attribute
     * {@link ProjectInformationReader#manifestVersionKey} in
     * {@link ProjectInformationReader#manifestMfPath}.
     *
     * @return The current program version.
     * @throws IllegalArgumentException If
     * {@link ProjectInformationReader#manifestMfPath} could not be found.
     * @throws IOException If an I/O exception occurs while reading the
     * Manifest.
     */
    private String readVersionFromManifest() throws IllegalArgumentException, IOException {
	try (final InputStream manifestStream = Resources.getResource(manifestMfPath.toString()).openStream()) {
	    final Manifest projectManifest = new Manifest(manifestStream);
	    return projectManifest.getMainAttributes().getValue(manifestVersionKey);
	}
    }

    /**
     * Reads the program's authors from
     * {@link ProjectInformationReader#authorsPath}. Every line in the file
     * corresponds to one author.
     *
     * @return The lines of {@link ProjectInformationReader#authorsPath}.
     * @throws IllegalArgumentException If
     * {@link ProjectInformationReader#authorsPath} could not be found.
     * @throws IOException If an I/O exception occurs while reading
     * {@link ProjectInformationReader#authorsPath}.
     */
    private ImmutableList<String> readAuthors() throws IllegalArgumentException, IOException {
	return ImmutableList.copyOf(Resources.readLines(Resources.getResource(authorsPath.toString()), Charsets.ISO_8859_1));
    }

    /**
     * Reads tho program's copying from
     * {@link ProjectInformationReader#copyingPath}.
     *
     * @return The content of {@link ProjectInformationReader#copyingPath}.
     * @throws IllegalArgumentException If
     * {@link ProjectInformationReader#copyingPath} could not be found.
     * @throws IOException If an I/O exception occurs while reading
     * {@link ProjectInformationReader#copyingPath}.
     */
    private String readCopying() throws IllegalArgumentException, IOException {
	return Resources.toString(Resources.getResource(copyingPath.toString()), Charsets.ISO_8859_1);
    }
}
