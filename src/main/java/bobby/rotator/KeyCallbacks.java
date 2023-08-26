package bobby.rotator;

import bobby.rotator.config.Configs;
import bobby.rotator.config.GuiConfigs;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import net.minecraft.client.MinecraftClient;

public class KeyCallbacks {

    public static void init() {
        Callbacks callback = new Callbacks();

        Configs.Generic.OPEN_CONFIG_GUI.getKeybind().setCallback(callback);
    }

    public static class Callbacks implements IHotkeyCallback {
        @Override
        public boolean onKeyAction(KeyAction action, IKeybind key) {
            MinecraftClient mc = MinecraftClient.getInstance();

            if (mc.player == null) {
                return false;
            }

            if (key == Configs.Generic.OPEN_CONFIG_GUI.getKeybind()) {
                GuiBase.openGui(new GuiConfigs());
            }

            return true;
        }
    }
}
