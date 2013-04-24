package de.silvalauinger.ldap.ldifsort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import de.silvalauinger.common.command.Command;
import de.silvalauinger.common.command.CommandException;
import de.silvalauinger.ldap.ldifsort.core.LdifEntryHierarchicalDnComparator;
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