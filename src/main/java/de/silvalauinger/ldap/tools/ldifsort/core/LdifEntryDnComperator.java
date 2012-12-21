package de.silvalauinger.ldap.tools.ldifsort.core;

import com.google.common.collect.Ordering;
import java.util.Comparator;
import org.apache.directory.shared.ldap.model.ldif.LdifEntry;
import org.apache.directory.shared.ldap.model.name.Dn;

public final class LdifEntryDnComperator implements Comparator<LdifEntry> {

    @Override
    public int compare(final LdifEntry left, final LdifEntry right) {
	final Dn leftDn = left.getDn();
	final Dn rightDn = right.getDn();
	if (leftDn.isAncestorOf(rightDn)) {
	    return -1;
	} else if (leftDn.isDescendantOf(rightDn)) {
	    return 1;
	} else if (leftDn.size() < rightDn.size()) {
	    return -1;
	} else if (leftDn.size() > rightDn.size()) {
	    return 1;
	} else {
	    return Ordering.usingToString().compare(left, right);
	}
    }
}
