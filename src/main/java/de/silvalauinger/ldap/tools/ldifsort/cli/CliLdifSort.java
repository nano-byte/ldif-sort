package de.silvalauinger.ldap.tools.ldifsort.cli;

import static com.google.common.base.Throwables.propagate;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;
import de.silvalauinger.ldap.tools.ldifsort.cli.parse.PathParser;
import static org.apache.directory.shared.util.Strings.isNotEmpty;

public final class CliLdifSort {

    public static void main(final String[] arguments) {
	try {
	    final String programOutput = new Program(arguments).run();
	    if (isNotEmpty(programOutput)) {
		System.out.println(programOutput);
	    }
	    exitWithoutError();
	} catch (final Exception exception) {
	    System.err.println(exception.getLocalizedMessage());
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
	private final static UnflaggedOption filesOption = new UnflaggedOption("ldifFile", new PathParser(), null, true, true, "The LDIF files to sort.");
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
	Program(final String[] cliArguments) throws Exception {
	    parseResult = tryParse(cliArguments);
	}

	private JSAPResult tryParse(final String[] arguments) throws IllegalArgumentException {
	    try {
		return cliParser.parse(arguments);
	    } catch (final Exception exception) {
		throw new IllegalArgumentException(generateHelpMessage(exception.getLocalizedMessage()));
	    }
	}
	//</editor-fold>

	public String run() throws Exception {
	    throw new UnsupportedOperationException();
	}

	private String generateHelpMessage(final String exceptionMessage) {
	    throw new UnsupportedOperationException();
	}
    }
}