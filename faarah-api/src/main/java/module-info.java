import dev.akarah.faarah.api.plugin.Plugin;

module faarah.api {
    requires datafixerupper;

    exports dev.akarah.faarah.api.component;
    exports dev.akarah.faarah.api.entity;
    exports dev.akarah.faarah.api.event;
    exports dev.akarah.faarah.api.event.player;
    exports dev.akarah.faarah.api.plugin;
    exports dev.akarah.faarah.api.registry;
    exports dev.akarah.faarah.api.util;

    uses Plugin;
}