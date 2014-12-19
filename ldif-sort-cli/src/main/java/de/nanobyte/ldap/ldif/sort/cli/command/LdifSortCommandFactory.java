package de.nanobyte.ldap.ldif.sort.cli.command;

import com.martiansoftware.jsap.*;
import com.martiansoftware.jsap.stringparsers.FileStringParser;
import de.nanobyte.common.command.Association;
import static de.nanobyte.common.command.AssociationBuilder.associate;
import de.nanobyte.common.command.Command;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.function.Function;

public final class LdifSortCommandFactory extends JSAPCommandFactory<String> {

    //<editor-fold defaultstate="collapsed" desc="statics">
    private final static Switch reverseOption = new Switch("reverse", 'r', "reverse", "Reverses the sort order.");
    private final static Switch helpOption = new Switch("help", 'h', "help", "Shows this help message.");
    private final static Switch versionOption = new Switch("version", 'v', "version", "Shows version information.");
    private final static UnflaggedOption filesOption = new UnflaggedOption("ldifFile", FileStringParser.getParser(), null, true, true, "The LDIF files to sort.");
    //</editor-fold>

    @Override
    @SuppressWarnings("unchecked")
    protected Iterable<Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<String>>>> argumentCommandAssociations() {
        ArrayList<Association<? extends Parameter, ? extends Function<JSAPResult, ? extends Command<String>>>> argumentCommandAssociations = new ArrayList<>();
        argumentCommandAssociations.add(associate(helpOption).with(parseResult -> new HelpTextCommand(cliParser)));
        argumentCommandAssociations.add(associate(versionOption).with(parseResult -> new VersionCommand()));
        argumentCommandAssociations.add(associate(reverseOption).with(parseResult -> new LdifEntriesToStringCommand(
                new LdifReverseSortCommand(new LdifFilesReadCommand(asList(parseResult.getFileArray(filesOption.getID())))))));
        argumentCommandAssociations.add(associate(filesOption).with(parseResult -> new LdifEntriesToStringCommand(
                new LdifSortCommand(new LdifFilesReadCommand(asList(parseResult.getFileArray(filesOption.getID())))))));
        return argumentCommandAssociations;
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
