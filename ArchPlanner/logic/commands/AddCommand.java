package logic.commands;

import java.util.ArrayList;
import java.util.Calendar;

import logic.Storage;
import logic.Task;

public class AddCommand implements Command {

	private String _description;
	private String _tag;
	private Calendar _startDateTime;
	private Calendar _endDateTime;


	public AddCommand(String description, String tag, Calendar startDateTime, Calendar endDateTime) {
		_description = description;
		_tag = tag;
		_startDateTime = startDateTime;
		_endDateTime = endDateTime;
	}

	public void setDescription(String description) {
		assert(description != null); //description cannot be null
		_description = description;
	}

	public void setTag(String tag) {
		_tag = tag;
	}

	public void setStartDateTime(Calendar startDateTime) {
		_startDateTime = startDateTime;
	}

	public void setEndDateTime(Calendar endDateTime) {
		_endDateTime = endDateTime;
	}

	public String getDescription() {
		return _description;
	}

	public String getTag() {
		return _tag;
	}

	public Calendar getStartDateTime() {
		return _startDateTime;
	}

	public Calendar getEndDateTime() {
		return _endDateTime;
	}

	@Override
	public boolean execute(ArrayList<Task> mainList, ArrayList<Task> viewList, ArrayList<String> tagsList) {
		boolean isSuccessful;

		Storage storage = new Storage();

		Task task = new Task(_description, _tag, _startDateTime, _endDateTime);
		isSuccessful = mainList.add(task);
		viewList.add(task);
		tagsList = updateTagsList(tagsList);

		return isSuccessful;
	}

	private ArrayList<String> updateTagsList(ArrayList<String> tagsList) {
		if (_tag != null && !tagsList.contains(_tag)) {
			tagsList.add(_tag);
			return tagsList;
		} else {
			return tagsList;
		}
	}
}