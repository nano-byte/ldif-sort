package de.silvalauinger.ldap.tools.ldifsort.cli.parse;

import com.martiansoftware.jsap.ParseException;
import com.martiansoftware.jsap.StringParser;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathParser extends StringParser {

    @Override
    public Path parse(final String string) throws ParseException {
	try {
	    return Paths.get(string);
	} catch (final IllegalArgumentException | FileSystemNotFoundException | SecurityException exception) {
	    throw new ParseException(exception.getLocalizedMessage());
	}
    }
}
