package de.nanobyte.common.command;

import static java.util.Objects.requireNonNull;

public class AssociationBuilder<TKey> {

    private final TKey key;

    private AssociationBuilder(final TKey key) {
	this.key = key;
    }

    public static <TKey> AssociationBuilder<TKey> associate(final TKey key) {
	return new AssociationBuilder<>(requireNonNull(key));
    }

    public <TValue> Association<TKey, TValue> with(final TValue value) {
	return new Association<>(key, requireNonNull(value));
    }
}
