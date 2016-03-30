package logic;

import java.util.ArrayList;

public class HistoryManager {
	private ArrayList<String> _previousUserInputList;
	private ArrayList<String> _nextUserInputList;
	private ArrayList<RollbackItem> _undoList;
	private ArrayList<RollbackItem> _redoList;
	
	public HistoryManager() {
		_previousUserInputList = new ArrayList<String>();
		_nextUserInputList = new ArrayList<String>();
		_undoList = new ArrayList<RollbackItem>();
		_redoList = new ArrayList<RollbackItem>();
	}
	
	public void setPreviousUserInputList(ArrayList<String> previousUserInputList) {
		_previousUserInputList = new ArrayList<String>();
		_previousUserInputList.addAll(previousUserInputList);
	}
	
	public void setNextUserInputList(ArrayList<String> nextUserInputList) {
		_nextUserInputList = new ArrayList<String>();
		_nextUserInputList.addAll(nextUserInputList);
	}

	public void setUndoList(ArrayList<RollbackItem> undoList) {
		_undoList = new ArrayList<RollbackItem>();
		_undoList.addAll(undoList);
	}

	public void setRedoList(ArrayList<RollbackItem> redoList) {
		_redoList = new ArrayList<RollbackItem>();
		_redoList.addAll(redoList);
	}
	
	public ArrayList<String> getPreviousUserInputList() {
		return _previousUserInputList;
	}
	
	public ArrayList<String> getNextUserInputList() {
		return _nextUserInputList;
	}

	public ArrayList<RollbackItem> getUndoList() {
		return _undoList;
	}

	public ArrayList<RollbackItem> getRedoList() {
		return _redoList;
	}
}
