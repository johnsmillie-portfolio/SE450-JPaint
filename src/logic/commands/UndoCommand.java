package logic.commands;

public class UndoCommand implements ICommand {
    @Override
    public void invoke() {
        CommandHistory.undo();
    }
}
