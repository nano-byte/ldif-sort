package de.nanobyte.ldap.ldif.sort.cli.command;

import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import java.util.Collection;
import static java.util.Objects.requireNonNull;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifEntriesToStringCommand implements Command<String> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Command<? extends Collection<LdifEntry>> ldifEntriesCommand;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifEntriesToStringCommand(final Command<? extends Collection<LdifEntry>> ldifEntriesCommand) {
	this.ldifEntriesCommand = requireNonNull(ldifEntriesCommand);
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
        return new StrBuilder(1000).appendAll(ldifEntriesCommand.execute()).toString();
    }
}