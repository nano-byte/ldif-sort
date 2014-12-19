package de.nanobyte.ldap.ldif.sort.cli.command;

import static com.google.common.base.Preconditions.checkNotNull;
import de.nanobyte.common.command.Command;
import de.nanobyte.common.command.CommandException;
import de.nanobyte.ldap.ldif.sort.core.LdifFilesReader;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifFilesReadCommand implements Command<Iterable<LdifEntry>>, Iterable<LdifEntry> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Collection<File> ldifFiles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifFilesReadCommand(final Collection<File> ldifFiles) {
	this.ldifFiles = checkNotNull(ldifFiles);
    }
    //</editor-fold>

    @Override
    public Iterable<LdifEntry> execute() throws CommandException {
	try {
	    return new LdifFilesReader(ldifFiles);
	} catch (final LdapLdifException exception) {
	    throw new CommandException(exception.getLocalizedMessage());
	}
    }

    @Override
    public Iterator<LdifEntry> iterator() {
	return execute().iterator();
    }
}