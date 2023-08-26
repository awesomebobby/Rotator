package bobby.rotator;

import bobby.rotator.config.Configs;
import fi.dy.masa.malilib.config.ConfigManager;

import fi.dy.masa.malilib.event.InputEventHandler;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;


public class InitHandler implements IInitializationHandler {

    @Override
    public void registerModHandlers() {

        ConfigManager.getInstance().registerConfigHandler(Rotator.MOD_ID, new Configs());

        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
        InputEventHandler.getInputManager().registerMouseInputHandler(InputHandler.getInstance());

        KeyCallbacks.init();
    }
}
