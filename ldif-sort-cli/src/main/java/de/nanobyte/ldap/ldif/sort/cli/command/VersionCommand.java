package de.nanobyte.ldap.ldif.sort.cli.command;

import static com.google.common.base.Throwables.propagate;
import de.nanobyte.common.base.DefaultProjectInformationReaderFactory;
import de.nanobyte.common.base.ProjectInformation;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import java.io.IOException;
import static java.lang.System.lineSeparator;
import org.apache.commons.lang.text.StrBuilder;

public final class VersionCommand implements Command<String> {

    @Override
    public String execute() throws CommandException {
	try {
	    final ProjectInformation read = new DefaultProjectInformationReaderFactory().get().read();
            return new StrBuilder()
		    .append(read.getProgramName()).append(" ").appendln(read.getVersion())
		    .appendln(read.getCopying()).appendNewLine()
		    .appendln("Authors: ")
                    .appendWithSeparators(read.getAuthors(), lineSeparator()).toString();
	} catch (final IOException exception) {
	    throw propagate(exception);
	}
    }
}
