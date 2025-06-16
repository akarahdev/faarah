import dev.akarah.faarah.api.event.EventHandler;
import dev.akarah.faarah.api.plugin.Plugin;

module faarah.test_plugin {
    requires faarah.api;

    provides Plugin with dev.akarah.plugin.Main;
    provides EventHandler with dev.akarah.plugin.EventHandlerImpl;
}