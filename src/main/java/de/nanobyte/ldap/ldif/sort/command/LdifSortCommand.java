package de.nanobyte.ldap.ldif.sort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import de.nanobyte.ldap.ldif.sort.core.LdifEntryHierarchicalDnComparator;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifSortCommand implements Command<ImmutableList<LdifEntry>> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Iterable<LdifEntry> ldifEntries;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifSortCommand(final Iterable<LdifEntry> ldifEntries) {
	this.ldifEntries = checkNotNull(ldifEntries);
    }
    //</editor-fold>

    @Override
    public ImmutableList<LdifEntry> execute() throws CommandException {
	return Ordering.from(LdifEntryHierarchicalDnComparator.INSTANCE).immutableSortedCopy(ldifEntries);
    }
}