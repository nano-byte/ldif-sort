package de.silvalauinger.ldap.tools.ldifsort.core;

import com.google.common.collect.Ordering;
import java.util.Comparator;
import org.apache.directory.shared.ldap.model.name.Dn;

public class HierarchicalDnComparator implements Comparator<Dn> {

    @Override
    public int compare(final Dn left, final Dn right) {
	if (left.isAncestorOf(right)) {
	    return -1;
	} else if (left.isDescendantOf(right)) {
	    return 1;
	} else if (left.size() < right.size()) {
	    return -1;
	} else if (left.size() > right.size()) {
	    return 1;
	} else {
	    return Ordering.usingToString().compare(left, right);
	}
    }
}
