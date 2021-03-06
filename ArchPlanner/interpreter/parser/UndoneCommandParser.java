package interpreter.parser;

import interpreter.separater.InputSeparater;
import logic.commands.CommandInterface;
import logic.commands.InvalidCommand;
import logic.commands.UndoneCommand;

/**
 * @@author A0149647N
 * UndoneCommandParser parse undone command with InputSeparator
 */
public class UndoneCommandParser {

    private static final String INVALID_ID = "Invalid: ID not found";
    private static final String INVALID_COMMAND = "Undone command is invalid!";
    private static final String INVALID_OUT_OF_RANGE = "Undone index out of range!";

    /**
     * Parse delete command with InputSeparator
     * @param input User's input
     * @param viewListSize Current view list's size
     * @return Parsed command object
     */
    public CommandInterface parse(String input, int viewListSize) {
        InputSeparater separator = new InputSeparater(input);
        if (separator.getID() == null) {
            return new InvalidCommand(INVALID_ID);
        }
        if (separator.isIdOnly()) {
            if (separator.isIdRangeValid(separator.getID(), viewListSize)) {
                return new UndoneCommand(separator.getID());
            } else {
                return new InvalidCommand(INVALID_OUT_OF_RANGE);
            }
        }
        if (separator.hasTwoValidId(viewListSize)) {
            return new UndoneCommand(separator.getID(), separator.getSecondId());
        }
        if (!separator.isIdRangeValid(separator.getSecondId(), viewListSize)) {
            return new InvalidCommand(INVALID_OUT_OF_RANGE);
        }
        return new InvalidCommand(INVALID_COMMAND);
    }
}
