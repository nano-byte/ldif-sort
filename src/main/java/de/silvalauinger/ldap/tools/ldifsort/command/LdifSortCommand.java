package de.silvalauinger.ldap.tools.ldifsort.command;

import com.google.common.base.Function;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableList;
import static com.google.common.collect.Ordering.from;
import de.silvalauinger.common.command.Command;
import de.silvalauinger.common.command.CommandException;
import de.silvalauinger.ldap.tools.ldifsort.core.HierarchicalDnComparator;
import org.apache.directory.shared.ldap.model.ldif.LdifEntry;
import org.apache.directory.shared.ldap.model.name.Dn;

public class LdifSortCommand implements Command<ImmutableList<LdifEntry>> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Iterable<LdifEntry> ldifToSort;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifSortCommand(final Iterable<LdifEntry> ldifToSort) {
	this.ldifToSort = checkNotNull(ldifToSort);
    }
    //</editor-fold>

    @Override
    public ImmutableList<LdifEntry> execute() throws CommandException {
	return from(new HierarchicalDnComparator()).onResultOf(new Function<LdifEntry, Dn>() {
	    @Override
	    public Dn apply(final LdifEntry toSort) {
		return toSort.getDn();
	    }
	}).immutableSortedCopy(ldifToSort);
    }
}