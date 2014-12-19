package de.nanobyte.ldap.ldif.sort.cli.command;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPResult;
import de.nanobyte.common.command.CommandException;
import static java.util.Objects.requireNonNull;
import org.apache.commons.lang.text.StrBuilder;

public final class ErrorMessageWithUsageTextCommand extends UsageTextCommand {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final String errorMessageWithHelpText;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public ErrorMessageWithUsageTextCommand(final JSAP cliParser, final JSAPResult parseResult) {
        super(cliParser);
        this.errorMessageWithHelpText = new StrBuilder()
                .appendln(requireNonNull(parseResult).getErrorMessageIterator().next())
                .appendln(super.execute())
                .appendln("Try --help for more information.").toString();
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
        return errorMessageWithHelpText;
    }
}
