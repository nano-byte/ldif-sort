package de.silvalauinger.ldap.tools.ldifsort.command;

import static com.google.common.base.Throwables.propagate;
import de.silvalauinger.common.base.DefaultProjectInformationReaderFactory;
import de.silvalauinger.common.base.ProjectInformation;
import de.silvalauinger.common.command.Command;
import de.silvalauinger.common.command.CommandException;
import java.io.IOException;
import static java.lang.System.lineSeparator;

public final class VersionCommand implements Command<String> {

    @Override
    public String execute() throws CommandException {
	try {
	    final ProjectInformation read = new DefaultProjectInformationReaderFactory().get().read();
	    final StringBuilder versionBuilder = new StringBuilder()
		    .append(read.getProgramName()).append(" ").append(read.getVersion()).append(lineSeparator())
		    .append(read.getCopying()).append(lineSeparator()).append(lineSeparator())
		    .append("Authors: ").append(lineSeparator());
	    for (final String author : read.getAuthors()) {
		versionBuilder.append(author).append(lineSeparator());
	    }

	    return versionBuilder.toString();
	} catch (final IOException exception) {
	    throw propagate(exception);
	}
    }
}
