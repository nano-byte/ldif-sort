package de.nanobyte.ldap.ldif.sort.cli.command;

import com.martiansoftware.jsap.JSAP;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import static java.util.Objects.requireNonNull;

public class UsageTextCommand implements Command<String> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String usageText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public UsageTextCommand(final JSAP cliParser) {
        this.usageText = "Usage: " + requireNonNull(cliParser).getUsage();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
        return usageText;
    }
}