module faarah.test_plugin {
    requires faarah.api;

    provides dev.akarah.faarah.api.Plugin with dev.akarah.plugin.Main;
}