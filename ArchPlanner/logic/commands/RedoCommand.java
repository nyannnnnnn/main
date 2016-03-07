package logic.commands;

import logic.Logic;

public class RedoCommand implements Command {

	private int _times;

	private final int DEFAULT_NUMBER_OF_TIMES = 1;

	public void setTimes(int times) {
		assert(times >= 1); //times can only be 1 or greater
		_times = times;
	}

	@Override
	public void execute(Logic logic) {

	}

	public void setTimes() {
		_times = DEFAULT_NUMBER_OF_TIMES;
	}

	public int getTimes() {
		return _times;
	}
}