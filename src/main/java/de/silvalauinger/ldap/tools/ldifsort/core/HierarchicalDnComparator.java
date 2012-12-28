package de.silvalauinger.ldap.tools.ldifsort.core;

import com.google.common.collect.Ordering;
import static java.lang.Integer.signum;
import java.util.Comparator;
import org.apache.directory.shared.ldap.model.name.Dn;

public enum HierarchicalDnComparator implements Comparator<Dn> {

    INSTANCE;

    @Override
    public int compare(final Dn left, final Dn right) {
        if (left.equals(right)) {
            return 0;
        } else if (left.isAncestorOf(right)) {
            return -1;
        } else if (left.isDescendantOf(right)) {
            return 1;
        } else if (left.size() < right.size()) {
            return -1;
        } else if (left.size() > right.size()) {
            return 1;
        } else {
            return signum(Ordering.usingToString().compare(left, right));
        }
    }
}