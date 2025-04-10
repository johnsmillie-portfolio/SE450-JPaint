package controller;

import logic.Clipboard;
import logic.commands.CommandHistory;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    

    public JPaintController(IUiModule uiModule,
            IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
      
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE,
                () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR,
                () -> applicationState
                        .setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR,
                () -> applicationState
                        .setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE,
                () -> applicationState
                        .setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE,
                () -> applicationState
                        .setActiveStartAndEndPointMode());
        uiModule.addEvent(EventName.UNDO,
                () -> CommandHistory.undo());
        uiModule.addEvent(EventName.REDO,
                () -> CommandHistory.redo());
        uiModule.addEvent(EventName.COPY, 
                () -> Clipboard.copyToClipboard());
        uiModule.addEvent(EventName.PASTE, 
                () -> Clipboard.paste());
        uiModule.addEvent(EventName.DELETE, 
                () -> Clipboard.delete());
        uiModule.addEvent(EventName.GROUP, 
                () -> Clipboard.group());
        uiModule.addEvent(EventName.UNGROUP, 
                () -> Clipboard.ungroup());
    }
}
