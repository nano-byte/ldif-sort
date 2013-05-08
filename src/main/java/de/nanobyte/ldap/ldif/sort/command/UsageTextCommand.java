package de.nanobyte.ldap.ldif.sort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import com.martiansoftware.jsap.JSAP;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;

public class UsageTextCommand implements Command<String> {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static String programName = "ldifsort";
    private final static String programInvocation = "java -jar " + programName + ".jar";
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String usageText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public UsageTextCommand(final JSAP cliParser) {
	this.usageText = "Usage: " + programInvocation + " " + checkNotNull(cliParser).getUsage();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
	return usageText;
    }
}