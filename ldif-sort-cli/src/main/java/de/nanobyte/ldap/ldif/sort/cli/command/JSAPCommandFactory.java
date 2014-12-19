package de.nanobyte.ldap.ldif.sort.cli.command;

import com.martiansoftware.jsap.*;
import de.nanobyte.common.command.Association;
import de.nanobyte.common.command.Command;
import java.util.HashMap;
import java.util.function.Function;

public abstract class JSAPCommandFactory<TCommandResult> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    protected final HashMap<? super Parameter, Function<JSAPResult, ? extends Command<TCommandResult>>> commands = new HashMap<>();
    protected final JSAP cliParser = new JSAP();
    protected JSAPResult parseResult;
    //</editor-fold>

    public JSAPCommandFactory() {
	for (final Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<TCommandResult>>> association : argumentCommandAssociations()) {
	    try {
		commands.put(association.getKey(), association.getValue());
		cliParser.registerParameter(association.getKey());
	    } catch (final JSAPException exception) {
		throw new RuntimeException(exception);
	    }
	}
    }

    public Command<TCommandResult> parseArguments(final String[] cliArguments) {
	parseResult = cliParser.parse(cliArguments);
	return doParse();
    }

    protected abstract Iterable<Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<TCommandResult>>>> argumentCommandAssociations();

    protected abstract Command<TCommandResult> doParse();
}
