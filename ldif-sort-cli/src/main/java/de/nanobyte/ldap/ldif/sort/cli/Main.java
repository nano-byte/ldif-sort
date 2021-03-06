package de.nanobyte.ldap.ldif.sort.cli;

import de.nanobyte.common.command.CommandException;

import de.nanobyte.ldap.ldif.sort.cli.command.LdifSortCommandFactory;
import static org.apache.directory.api.util.Strings.isNotEmpty;

public final class Main {

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