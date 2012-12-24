package de.silvalauinger.common.command;

import static com.google.common.base.Preconditions.checkNotNull;

public class AssociationBuilder<TKey> {

    private final TKey key;

    private AssociationBuilder(final TKey key) {
	this.key = key;
    }

    public static <TKey> AssociationBuilder<TKey> associate(final TKey key) {
	return new AssociationBuilder<>(checkNotNull(key));
    }

    public <TValue> Association<TKey, TValue> with(final TValue value) {
	return new Association<>(key, checkNotNull(value));
    }
}
