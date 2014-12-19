package de.nanobyte.ldap.ldif.sort.cli.command;

import com.martiansoftware.jsap.JSAP;
import de.nanobyte.common.command.CommandException;
import static java.util.Objects.requireNonNull;
import org.apache.commons.lang.text.StrBuilder;

public class HelpTextCommand extends UsageTextCommand {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String helpText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public HelpTextCommand(final JSAP cliParser) {
        super(cliParser);
        this.helpText = new StrBuilder(super.execute()).appendNewLine()
                .append(requireNonNull(cliParser).getHelp()).toString();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
        return helpText;
    }
}
