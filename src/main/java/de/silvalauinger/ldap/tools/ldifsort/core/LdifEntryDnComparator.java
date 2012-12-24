package de.silvalauinger.ldap.tools.ldifsort.core;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import org.apache.directory.shared.ldap.model.ldif.LdifEntry;
import org.apache.directory.shared.ldap.model.name.Dn;

public final class LdifEntryDnComparator implements Comparator<LdifEntry> {

    @Override
    public int compare(final LdifEntry left, final LdifEntry right) {
	return Ordering.from(new HierarchicalDnComparator()).onResultOf(new Function<LdifEntry, Dn>() {
	    @Override
	    public Dn apply(final LdifEntry input) {
		return input.getDn();
	    }
	}).compare(left, right);
    }
}