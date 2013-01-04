package de.silvalauinger.ldap.tools.ldifsort.command;

import com.google.common.base.Function;
import static com.google.common.collect.Lists.newArrayList;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.Parameter;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;
import com.martiansoftware.jsap.stringparsers.FileStringParser;
import de.silvalauinger.common.command.Association;
import static de.silvalauinger.common.command.AssociationBuilder.associate;
import de.silvalauinger.common.command.Command;
import static java.util.Arrays.asList;

public final class LdifSortCommandFactory extends JSAPCommandFactory<String> {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static Switch reverseOption = new Switch("reverse", 'r', "reverse", "Reverses the sort order.");
    private final static Switch helpOption = new Switch("help", 'h', "help", "Shows this help message.");
    private final static Switch versionOption = new Switch("version", 'v', "version", "Shows version information.");
    private final static UnflaggedOption filesOption = new UnflaggedOption("ldifFile", FileStringParser.getParser(), null, true, true, "The LDIF files to sort.");
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public LdifSortCommandFactory() {
    }
    //</editor-fold>

    @Override
    @SuppressWarnings("unchecked")
    protected Iterable<Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<String>>>> argumentCommandAssociations() {
	return newArrayList(associate(helpOption).with(new Function<JSAPResult, HelpTextCommand>() {
	    @Override
	    public HelpTextCommand apply(final JSAPResult parseResult) {
		return new HelpTextCommand(cliParser);
	    }
	}), associate(versionOption).with(new Function<JSAPResult, VersionCommand>() {
	    @Override
	    public VersionCommand apply(final JSAPResult parseResult) {
		return new VersionCommand();
	    }
	}), associate(reverseOption).with(new Function<JSAPResult, LdifEntriesToStringCommand>() {
	    @Override
	    public LdifEntriesToStringCommand apply(final JSAPResult parseResult) {
		return new LdifEntriesToStringCommand(new LdifReverseSortCommand(new LdifFilesReadCommand(asList(parseResult.getFileArray(filesOption.getID())))));
	    }
	}), associate(filesOption).with(new Function<JSAPResult, LdifEntriesToStringCommand>() {
	    @Override
	    public LdifEntriesToStringCommand apply(final JSAPResult parseResult) {
		return new LdifEntriesToStringCommand(new LdifSortCommand(new LdifFilesReadCommand(asList(parseResult.getFileArray(filesOption.getID())))));
	    }
	}));
    }

    @Override
    public Command<String> doParse() {
	if (shallPrintHelp()) {
	    return commands.get(helpOption).apply(parseResult);
	} else if (shallPrintVersion()) {
	    return commands.get(versionOption).apply(parseResult);
	} else if (parsingErrorsDetected()) {
	    return new ErrorMessageWithUsageTextCommand(cliParser, parseResult);
	} else if (shallReverseSort()) {
	    return commands.get(reverseOption).apply(parseResult);
	} else {
	    return commands.get(filesOption).apply(parseResult);
	}
    }

    private boolean shallPrintHelp() {
	return parseResult.getBoolean(helpOption.getID(), false);
    }

    private boolean shallPrintVersion() {
	return parseResult.getBoolean(versionOption.getID(), false);
    }

    private boolean parsingErrorsDetected() {
	return !parseResult.success();
    }

    private boolean shallReverseSort() {
	return parseResult.getBoolean(reverseOption.getID());
    }
}