package bobby.rotator.config;

import bobby.rotator.Rotator;
import bobby.rotator.util.DirectionList4;
import bobby.rotator.util.DirectionList6;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.IHotkeyTogglable;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class Configs implements IConfigHandler {

    private static final String CONFIG_FILE_NAME = Rotator.MOD_ID + ".json";
    private static final int CONFIG_VERSION = 1;

    public static class Generic{

        public static final ConfigHotkey OPEN_CONFIG_GUI = new ConfigHotkey("openConfigGui", "", "A hotkey to open the in-game Config GUI");

        public static final ConfigOptionList OBSERVER_DIRECTIONS = new ConfigOptionList("observerDirections", DirectionList6.DOWN, "Placement directions for observer blocks");
        public static final ConfigOptionList PISTON_DIRECTIONS = new ConfigOptionList("pistonDirections", DirectionList6.DOWN, "Placement directions for (sticky)piston blocks");
        public static final ConfigOptionList DISPENSER_DIRECTIONS = new ConfigOptionList("dispenserDirections", DirectionList6.DOWN, "Placement directions for dispenser blocks");
        public static final ConfigOptionList DROPPER_DIRECTIONS = new ConfigOptionList("dropperDirections", DirectionList6.DOWN, "Placement directions for dropper blocks");
        public static final ConfigOptionList HOPPER_DIRECTIONS = new ConfigOptionList("hopperDirections", DirectionList4.NORTH, "Placement directions for hopper blocks");

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(

            OPEN_CONFIG_GUI,

            OBSERVER_DIRECTIONS,
            PISTON_DIRECTIONS,
            DISPENSER_DIRECTIONS,
            DROPPER_DIRECTIONS,
            HOPPER_DIRECTIONS

        );

        public static final List<IHotkey> HOTKEY_LIST = ImmutableList.of(
                OPEN_CONFIG_GUI
        );
    }

    public static class Toggle {

        public static final ConfigBooleanHotkeyed MAIN_TOGGLE = new ConfigBooleanHotkeyed("mainToggle", true, "", "The main toggle for Rotator mod");

        public static final ConfigBooleanHotkeyed OPPOSITE_PLACEMENT = new ConfigBooleanHotkeyed("oppositePlacementToggle", false, "", "Sets the placement direction to the reverse of the default direction.\n(When you turn this on, the following block toggles will not work)");

        public static final ConfigBooleanHotkeyed OBSERVER_TOGGLE = new ConfigBooleanHotkeyed("observerToggle", false, "", "Enables a player to choose a specific direction \nfrom the direction list to place observer blocks.");
        public static final ConfigBooleanHotkeyed PISTON_TOGGLE = new ConfigBooleanHotkeyed("pistonToggle", false, "", "Enables a player to choose a specific direction \nfrom the direction list to place (sticky)piston blocks.");
        public static final ConfigBooleanHotkeyed DISPENSER_TOGGLE = new ConfigBooleanHotkeyed("dispenserToggle", false, "", "Enables a player to choose a specific direction \nfrom the direction list to place dispenser blocks.");
        public static final ConfigBooleanHotkeyed DROPPER_TOGGLE = new ConfigBooleanHotkeyed("dropperToggle", false, "", "Enables a player to choose a specific direction \nfrom the direction list to place dropper blocks.");
        public static final ConfigBooleanHotkeyed HOPPER_TOGGLE = new ConfigBooleanHotkeyed("hopperToggle", false, "", "Enables a player to choose a specific direction \nfrom the direction list to place hopper blocks.");

        //TODO  flipper: flipping blocks with hand

        public static final ImmutableList<IHotkeyTogglable> OPTIONS = ImmutableList.of(

                MAIN_TOGGLE,

                OPPOSITE_PLACEMENT,

                OBSERVER_TOGGLE,
                PISTON_TOGGLE,
                DISPENSER_TOGGLE,
                DROPPER_TOGGLE,
                HOPPER_TOGGLE

        );

    }

    public Configs(){
        this.load();
    }

    @Override
    public void load() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();

                ConfigUtils.readConfigBase(root, "Generic", Configs.Generic.OPTIONS);
                ConfigUtils.readHotkeyToggleOptions(root, "Hotkeys", "Toggle", Toggle.OPTIONS);
            }
        }
    }

    @Override
    public void save() {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();

            ConfigUtils.writeConfigBase(root, "Generic", Configs.Generic.OPTIONS);
            ConfigUtils.writeHotkeyToggleOptions(root, "Hotkeys", "Toggle", Toggle.OPTIONS);

            root.add("config_version", new JsonPrimitive(CONFIG_VERSION));

            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }
}
