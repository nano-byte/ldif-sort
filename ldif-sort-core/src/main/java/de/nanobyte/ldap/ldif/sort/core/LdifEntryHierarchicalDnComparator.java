package de.nanobyte.ldap.ldif.sort.core;

import java.util.Comparator;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public enum LdifEntryHierarchicalDnComparator implements Comparator<LdifEntry> {

    INSTANCE;

    @Override
    public int compare(final LdifEntry left, final LdifEntry right) {
        return Comparator.comparing(LdifEntry::getDn, HierarchicalDnComparator.INSTANCE).compare(left, right);
    }
}