package de.nanobyte.common.command;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Association<TKey, TValue> {

    private final TKey key;
    private final TValue value;

    Association(final TKey key, final TValue value) {
	this.key = checkNotNull(key);
	this.value = checkNotNull(value);
    }

    public TKey getKey() {
	return key;
    }

    public TValue getValue() {
	return value;
    }
}