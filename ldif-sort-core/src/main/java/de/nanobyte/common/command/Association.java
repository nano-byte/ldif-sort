package de.nanobyte.common.command;

import static java.util.Objects.requireNonNull;

public final class Association<TKey, TValue> {

    private final TKey key;
    private final TValue value;

    Association(final TKey key, final TValue value) {
	this.key = requireNonNull(key);
	this.value = requireNonNull(value);
    }

    public TKey getKey() {
	return key;
    }

    public TValue getValue() {
	return value;
    }
}