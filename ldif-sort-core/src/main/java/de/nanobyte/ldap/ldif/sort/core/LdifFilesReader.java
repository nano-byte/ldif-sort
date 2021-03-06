package de.nanobyte.ldap.ldif.sort.core;

import java.io.File;
import static java.lang.String.format;
import java.nio.file.Path;
import java.util.*;
import org.apache.directory.api.ldap.model.ldif.*;
import org.apache.directory.api.ldap.model.name.Dn;

public final class LdifFilesReader implements Iterable<LdifEntry> {

    private final Set<LdifEntry> ldifEntries;

    public LdifFilesReader(final Collection<File> ldifFiles) throws LdapLdifException {
        final int guessedDnSize = ldifFiles.size() * 100;
        ldifEntries = new HashSet<>(guessedDnSize);
        final Map<Dn, Path> takenDns = new HashMap<>(guessedDnSize);
        
        for (final File ldifFile : ldifFiles) {
            final Path currentLdifPath = ldifFile.toPath();            
            for (final LdifEntry ldifEntry : new LdifReader(ldifFile)) {
                final Dn currentEntryDn = ldifEntry.getDn();
                if (takenDns.containsKey(currentEntryDn)) {
                    throw new LdapLdifException(format("The dn %s in ldif file %s is already used in ldif file %s", currentEntryDn, currentLdifPath, takenDns
                            .get(currentEntryDn)));
                } else {
                    takenDns.put(currentEntryDn, currentLdifPath);
                    ldifEntries.add(ldifEntry);
                }
            }
        }
    }

    @Override
    public Iterator<LdifEntry> iterator() {
        return ldifEntries.iterator();
    }
}
