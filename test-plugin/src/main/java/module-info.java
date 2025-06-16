module faarah.test_plugin {
    requires faarah.api;

    provides dev.akarah.faarah.api.Plugin with dev.akarah.plugin.Main;
    provides dev.akarah.faarah.api.EventHandler with dev.akarah.plugin.EventHandlerImpl;
}