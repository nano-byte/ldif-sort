package de.nanobyte.ldap.ldif.sort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifEntriesToStringCommand implements Command<String> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Command<? extends Iterable<LdifEntry>> ldifEntriesCommand;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifEntriesToStringCommand(final Command<? extends Iterable<LdifEntry>> ldifEntriesCommand) {
	this.ldifEntriesCommand = checkNotNull(ldifEntriesCommand);
    }
    //</editor-fold>

    @Override
    public String execute() throws CommandException {
	final StringBuilder concatenatedLdifEntries = new StringBuilder(1000);
	for (final LdifEntry ldifEntry : ldifEntriesCommand.execute()) {
	    concatenatedLdifEntries.append(ldifEntry);
	}
	return concatenatedLdifEntries.toString();
    }
}