package de.silvalauinger.ldap.ldifsort.core;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.name.Dn;

public enum LdifEntryHierarchicalDnComparator implements Comparator<LdifEntry> {

    INSTANCE;

    @Override
    public int compare(final LdifEntry left, final LdifEntry right) {
	return Ordering.from(HierarchicalDnComparator.INSTANCE).onResultOf(new Function<LdifEntry, Dn>() {
	    @Override
	    public Dn apply(final LdifEntry input) {
		return input.getDn();
	    }
	}).compare(left, right);
    }
}