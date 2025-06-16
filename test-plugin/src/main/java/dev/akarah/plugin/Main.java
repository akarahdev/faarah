
package dev.akarah.plugin;

import dev.akarah.faarah.api.Plugin;

public class Main implements Plugin {
    @Override
    public void onStartup() {
        System.out.println("I've been started! Yay!");
    }

    @Override
    public void onShutdown() {

    }
}