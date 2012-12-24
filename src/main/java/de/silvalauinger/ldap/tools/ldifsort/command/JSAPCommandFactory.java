package de.silvalauinger.ldap.tools.ldifsort.command;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Parameter;
import de.silvalauinger.common.command.Association;
import de.silvalauinger.common.command.Command;
import java.util.HashMap;

public abstract class JSAPCommandFactory<TCommandResult> {

    //<editor-fold defaultstate="collapsed" desc="attributes">
    protected final HashMap<? super Parameter, Function<JSAPResult, ? extends Command<TCommandResult>>> commands = Maps.newHashMap();
    protected final JSAP cliParser = new JSAP();
    protected JSAPResult parseResult;
    //</editor-fold>

    public JSAPCommandFactory() throws JSAPException {
	for (final Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<TCommandResult>>> association : argumentCommandAssociations()) {
	    commands.put(association.getKey(), association.getValue());
	    cliParser.registerParameter(association.getKey());
	}
    }

    public Command<TCommandResult> parseArguments(final String[] cliArguments) {
	parseResult = cliParser.parse(cliArguments);
	return doParse();
    }

    protected abstract Iterable<Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<TCommandResult>>>> argumentCommandAssociations();

    protected abstract Command<TCommandResult> doParse();
}
