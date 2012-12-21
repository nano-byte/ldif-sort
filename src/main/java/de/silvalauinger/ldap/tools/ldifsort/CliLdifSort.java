package de.silvalauinger.ldap.tools.ldifsort;

import com.google.common.base.Function;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.FluentIterable.from;
import com.google.common.collect.ImmutableList;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;
import com.martiansoftware.jsap.stringparsers.FileStringParser;
import de.silvalauinger.ldap.tools.ldifsort.core.LdifEntryDnComperator;
import java.io.File;
import static java.util.Arrays.asList;
import org.apache.directory.shared.ldap.model.ldif.LdapLdifException;
import org.apache.directory.shared.ldap.model.ldif.LdifEntry;
import org.apache.directory.shared.ldap.model.ldif.LdifReader;
import static org.apache.directory.shared.util.Strings.isNotEmpty;

public final class CliLdifSort {

    public static void main(final String[] arguments) {
	try {
	    final String programOutput = new Program(arguments).run();
	    if (isNotEmpty(programOutput)) {
		System.out.print(programOutput);
	    }
	    exitWithoutError();
	} catch (final Exception exception) {
	    System.err.print(exception.getCause() == null ? exception.getLocalizedMessage() : exception.getCause().getLocalizedMessage());
	    exitWithError();
	}
    }

    private static void exitWithoutError() throws IllegalArgumentException {
	System.exit(0);
    }

    private static void exitWithError() throws IllegalArgumentException {
	System.exit(1);
    }

    private static class Program {

	//<editor-fold defaultstate="collapsed" desc="statics">
	private final static String programName = "ldifsort";
	private final static String programInvocation = "java -jar " + programName + ".jar";
	private final static Switch reverseOption = new Switch("reverse", 'r', "reverse", "Reverses the sort order.");
	private final static Switch helpOption = new Switch("help", 'h', "help", "Shows this help message.");
	private final static UnflaggedOption filesOption = new UnflaggedOption("ldifFile", FileStringParser.getParser(), null, true, true, "The LDIF files to sort.");
	private final static JSAP cliParser = new JSAP();

	static {
	    try {
		cliParser.registerParameter(helpOption);
		cliParser.registerParameter(reverseOption);
		cliParser.registerParameter(filesOption);
	    } catch (final JSAPException exception) {
		propagate(exception);
	    }
	}
	//</editor-fold>
	//<editor-fold defaultstate="collapsed" desc="attributes">
	final JSAPResult parseResult;
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="constructor">
	Program(final String[] cliArguments) {
	    parseResult = cliParser.parse(cliArguments);
	}
	//</editor-fold>

	public String run() throws Exception {
	    if (shallPrintHelp()) {
		return usageWithHelp();
	    } else if (detectedParsingErrors()) {
		throw new IllegalArgumentException(errorMessageWithUsage());
	    }

	    if (reverseSort()) {
		return ldifEntriesToString(sortLdifEntries().reverse());
	    } else {
		return ldifEntriesToString(sortLdifEntries());
	    }
	}

	private boolean shallPrintHelp() {
	    return parseResult.getBoolean(helpOption.getID());
	}

	private String usageWithHelp() {
	    return usage() + System.lineSeparator() + cliParser.getHelp();
	}

	private String usage() {
	    return "usage: " + programInvocation + " " + cliParser.getUsage();
	}

	private boolean detectedParsingErrors() {
	    return !parseResult.success();
	}

	private String errorMessageWithUsage() {
	    return parseResult.getErrorMessageIterator().next() + System.lineSeparator()
		    + usage() + System.lineSeparator()
		    + "Try --help for more information." + System.lineSeparator();
	}

	private boolean reverseSort() {
	    return parseResult.getBoolean(reverseOption.getID());
	}

	private ImmutableList<LdifEntry> sortLdifEntries() {
	    return from(asList(parseResult.getFileArray(filesOption.getID()))).transformAndConcat(new Function<File, Iterable<LdifEntry>>() {
		@Override
		public Iterable<LdifEntry> apply(final File ldif) {
		    try {
			return new LdifReader(ldif);
		    } catch (final LdapLdifException exception) {
			throw propagate(exception);
		    }
		}
	    }).toSortedImmutableList(new LdifEntryDnComperator());
	}

	private static String ldifEntriesToString(final Iterable<LdifEntry> ldifEntries) {
	    final StringBuilder concatedLdifEntries = new StringBuilder();
	    for (final LdifEntry ldifEntry : ldifEntries) {
		concatedLdifEntries.append(ldifEntry);
	    }
	    return concatedLdifEntries.toString();
	}
    }
}
