package logic;

import logic.commands.ICommand;

public class LogicFacade {
    public LogicFacade() {

    }

    public void applyCommand(ICommand c) {
        c.apply();
    }
}