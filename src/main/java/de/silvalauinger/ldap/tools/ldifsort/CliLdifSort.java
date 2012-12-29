package de.silvalauinger.ldap.tools.ldifsort;

import de.silvalauinger.common.command.CommandException;
import de.silvalauinger.ldap.tools.ldifsort.command.LdifSortCommandFactory;
import static org.apache.directory.shared.util.Strings.isNotEmpty;

public final class CliLdifSort {

    public static void main(final String[] arguments) {
	try {
	    final String programOutput = new LdifSortCommandFactory().parseArguments(arguments).execute();
	    if (isNotEmpty(programOutput)) {
		System.out.print(programOutput);
	    }
	    exitWithoutError();
	} catch (final CommandException exception) {
	    System.err.print(exception.getLocalizedMessage());
	    exitWithError();
	}
    }

    private static void exitWithoutError() {
	System.exit(0);
    }

    private static void exitWithError() {
	System.exit(1);
    }
}