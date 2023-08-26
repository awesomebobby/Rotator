package bobby.rotator;

import bobby.rotator.config.Configs;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import fi.dy.masa.malilib.hotkeys.IMouseInputHandler;

public class InputHandler implements IKeybindProvider, IMouseInputHandler {

    private static final InputHandler INSTANCE = new InputHandler();

    private InputHandler()
    {
        super();
    }

    public static InputHandler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (IHotkey hotkey : Configs.Generic.HOTKEY_LIST) {
            manager.addKeybindToMap(hotkey.getKeybind());
        }

        for (IHotkey hotkey : Configs.Toggle.OPTIONS) {
            manager.addKeybindToMap(hotkey.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        manager.addHotkeysForCategory(Rotator.MOD_NAME, "rotator.hotkey.generic", Configs.Generic.HOTKEY_LIST);
        manager.addHotkeysForCategory(Rotator.MOD_NAME, "rotator.hotkey.switch", Configs.Toggle.OPTIONS);
    }
}
