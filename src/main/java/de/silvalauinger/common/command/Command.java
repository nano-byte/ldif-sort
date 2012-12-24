package de.silvalauinger.common.command;

public interface Command<TResult> {

    public TResult execute() throws CommandException;
}