package interpreter.parser;

import logic.TaskParameters;
import logic.commands.AddCommand;
import logic.commands.CommandInterface;
import logic.commands.InvalidCommand;
import interpreter.parser.time.TimeParserResult;
import interpreter.separater.AddInputSeparater;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @@author A0149647N
 * AddCommandParser parse add command with AddInputSeparator
 */
public class AddCommandParser {

    private static final int ADD_PARAMETER_INDEX = 4;
    private static final String INVALID_TIME = "Invalid Time!";
    private static final String INVALID_RANGE = "Invalid Time Range!";
    private static final String INVALID_TAG = "Invalid Tags";
    private static final String INVALID_EMPTY = "Invalid empty command";
    private static final String TAG_NOTATION = "#";

    private TaskParameters result = new TaskParameters();

    /**
     * Parse add command with AddInputSeparator
     * @param input user's command
     * @return Parsed command object
     */
    public CommandInterface parse(String input) {
        if (input.length() <= ADD_PARAMETER_INDEX) {
            return new InvalidCommand(INVALID_EMPTY);
        }
        AddInputSeparater addInputSeparator = new AddInputSeparater(input.substring(ADD_PARAMETER_INDEX));
        if (!addInputSeparator.hasDescription()) {
            return new InvalidCommand(INVALID_EMPTY);
        }
        result.setDescription(addInputSeparator.getDescription());
        TimeParserResult timeParserResult = getDateTime(addInputSeparator);
        result.setStartDate(timeParserResult.getFirstDate());
        result.setStartTime(timeParserResult.getFirstTime());
        result.setEndDate(timeParserResult.getSecondDate());
        result.setEndTime(timeParserResult.getSecondTime());
        result.setTagsList(new ArrayList<>());
        if (!timeParserResult.isTimeValid()) {
            return new InvalidCommand(INVALID_RANGE);
        }
        if (!checkTimeValidWithKeyword(addInputSeparator.getKeyWord(), timeParserResult)) {
            return new InvalidCommand(INVALID_TIME);
        }
        if (addInputSeparator.hasValidTag()) {
            if (addInputSeparator.getTags()[addInputSeparator.getTags().length - 1].equals(TAG_NOTATION)) {
                return new InvalidCommand(INVALID_TAG);
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                Collections.addAll(arrayList, addInputSeparator.getTags());
                result.setTagsList(arrayList);
            }
        } else if (addInputSeparator.hasTag()) {
            return new InvalidCommand(INVALID_TAG);
        }
        return new AddCommand(result);
    }

    /**
     * Get date and time from AddInputSeparator and return a TimeParserResult object
     * This method also update data for some default values
     * @param addInputSeparator The separator which contains initial data
     * @return Reformed TimeParserResult object
     */
    private TimeParserResult getDateTime(AddInputSeparater addInputSeparator) {
        TimeParserResult timeParserResult = new TimeParserResult();
        if (addInputSeparator.hasStartDate()) {
            timeParserResult.setFirstDate(addInputSeparator.getStartDate());
        }
        if (addInputSeparator.hasStartTime()) {
            timeParserResult.setFirstTime(addInputSeparator.getStartTime());
        }
        if (addInputSeparator.hasEndDate()) {
            timeParserResult.setSecondDate(addInputSeparator.getEndDate());
        }
        if (addInputSeparator.hasEndTime()) {
            timeParserResult.setSecondTime(addInputSeparator.getEndTime());
        }
        timeParserResult.updateDateTime();
        if (timeParserResult.getFirstTime() != null && timeParserResult.getFirstDate() == null) {
            timeParserResult.setFirstDate(LocalDate.now());
        }
        if (timeParserResult.getSecondTime() != null && timeParserResult.getSecondDate() == null) {
            timeParserResult.setSecondDate(LocalDate.now());
        }
        timeParserResult.checkInvalidTimeRange();
        return timeParserResult;
    }

    /**
     * check if the time is valid for the keyword
     * @param keyword This is the keyword
     * @param timeParserResult This contains all the date and time date
     * @return true if valid, else false
     */
    private boolean checkTimeValidWithKeyword(AddInputSeparater.AddKeyWordType keyword, TimeParserResult timeParserResult) {
        if (keyword == null) {
            return true;
        }
        switch (keyword) {
            case UNKNOWN:
                return true;
            case ON:
                if (timeParserResult.getFirstDate() == null || timeParserResult.getSecondDate() != null) {
                    return false;
                }
                break;
            case BY:
                if (timeParserResult.getFirstDate() != null || timeParserResult.getSecondDate() == null) {
                    return false;
                }
                break;
            case FROM:
                if (timeParserResult.getFirstDate() == null || timeParserResult.getSecondDate() == null) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

}