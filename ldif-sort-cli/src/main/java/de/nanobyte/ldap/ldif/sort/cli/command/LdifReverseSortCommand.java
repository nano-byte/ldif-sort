package de.nanobyte.ldap.ldif.sort.cli.command;

import com.google.common.collect.ImmutableList;
import de.nanobyte.common.command.CommandException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifReverseSortCommand extends LdifSortCommand {

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifReverseSortCommand(final Iterable<LdifEntry> ldifToSort) {
	super(ldifToSort);
    }
    //</editor-fold>

    @Override
    public ImmutableList<LdifEntry> execute() throws CommandException {
	return super.execute().reverse();
    }
}