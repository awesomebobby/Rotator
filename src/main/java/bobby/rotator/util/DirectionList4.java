package bobby.rotator.util;

import fi.dy.masa.malilib.config.IConfigOptionListEntry;
import fi.dy.masa.malilib.util.StringUtils;

public enum DirectionList4 implements IConfigOptionListEntry {

    NORTH("north", "rotator.util.north"),
    EAST("east", "rotator.util.east"),
    SOUTH("south", "rotator.util.south"),
    WEST("west", "rotator.util.west");

    private final String configString;
    private final String unlocName;

    DirectionList4(String configString, String unlocName) {
        this.configString = configString;
        this.unlocName = unlocName;
    };

    @Override
    public String getStringValue() {
        return this.configString;
    }

    @Override
    public String getDisplayName() {
        return StringUtils.translate(this.unlocName);
    }

    @Override
    public IConfigOptionListEntry cycle(boolean forward) {

        int id = this.ordinal();

        if (forward) {
            if (++id >= values().length) {
                id = 0;
            }
        }
        else {
            if (--id < 0) {
                id = values().length - 1;
            }
        }

        return values()[id % values().length];
    }

    @Override
    public DirectionList4 fromString(String name) {
        return fromStringStatic(name);
    }

    public static DirectionList4 fromStringStatic(String name) {

        for (DirectionList4 direction : DirectionList4.values()) {
            if (direction.configString.equalsIgnoreCase(name)) {
                return direction;
            }
        }

        return DirectionList4.NORTH;
    }
}
