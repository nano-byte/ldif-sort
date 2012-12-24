package de.silvalauinger.ldap.tools.ldifsort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import com.martiansoftware.jsap.JSAP;
import de.silvalauinger.common.command.CommandException;

public class HelpTextCommand extends UsageTextCommand {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String helpText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public HelpTextCommand(final JSAP cliParser) {
	super(cliParser);
	this.helpText = super.execute() + System.lineSeparator()
		+ checkNotNull(cliParser).getHelp();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
	return helpText;
    }
}