package de.silvalauinger.ldap.ldifsort.command;

import static com.google.common.base.Preconditions.checkNotNull;
import de.silvalauinger.common.command.Command;
import de.silvalauinger.common.command.CommandException;
import de.silvalauinger.ldap.ldifsort.core.LdifFilesReader;
import java.io.File;
import java.util.Iterator;
import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;

public class LdifFilesReadCommand implements Command<Iterable<LdifEntry>>, Iterable<LdifEntry> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    private final Iterable<File> ldifFiles;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifFilesReadCommand(final Iterable<File> ldifFiles) {
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