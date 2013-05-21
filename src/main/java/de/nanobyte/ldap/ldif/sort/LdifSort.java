package de.nanobyte.ldap.ldif.sort;

import com.google.common.base.Optional;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static java.awt.GraphicsEnvironment.isHeadless;
import java.util.List;

public final class LdifSort {

    private final static String BATCH_COMMAND = "--cli";
    private final static String BATCH_COMMAND_SHORT = "-c";

    public static void main(final String[] arguments) {
        final Optional<Integer> batchIndex = findBatchSwitch(arguments);
        if (isHeadless() || batchIndex.isPresent()) {
            CliLdifSort.main(removeFromArray(batchIndex.get(), arguments));
        } else {
            new SwingLdifSort(arguments).setVisible(true);
        }
    }

    private static Optional<Integer> findBatchSwitch(final String[] arguments) {
        for (int index = 0; index < arguments.length; index++) {
            if (BATCH_COMMAND.equals(arguments[index]) || BATCH_COMMAND_SHORT.equals(arguments[index])) {
                return Optional.of(index);
            }
        }
        return Optional.absent();
    }

    private static String[] removeFromArray(final int index, final String[] removeFrom) {
        final List<String> reduced = newArrayList(removeFrom);
        reduced.remove(index);
        return from(reduced).toArray(String.class);
    }
}
