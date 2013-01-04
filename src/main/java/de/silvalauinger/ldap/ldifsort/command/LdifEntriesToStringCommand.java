package de.silvalauinger.ldap.ldifsort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import de.silvalauinger.common.command.Command;
import de.silvalauinger.common.command.CommandException;
import org.apache.directory.shared.ldap.model.ldif.LdifEntry;

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