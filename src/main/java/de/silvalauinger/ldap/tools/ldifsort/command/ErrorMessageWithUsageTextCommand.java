package de.silvalauinger.ldap.tools.ldifsort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import de.silvalauinger.common.command.CommandException;

public final class ErrorMessageWithUsageTextCommand extends UsageTextCommand {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String errorMessageWithHelpText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ErrorMessageWithUsageTextCommand(final JSAP cliParser, final JSAPResult parseResult) {
	super(cliParser);
	this.errorMessageWithHelpText = checkNotNull(parseResult).getErrorMessageIterator().next() + System.lineSeparator()
		+ super.execute() + System.lineSeparator()
		+ "Try --help for more information." + System.lineSeparator();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
	return errorMessageWithHelpText;
    }
}