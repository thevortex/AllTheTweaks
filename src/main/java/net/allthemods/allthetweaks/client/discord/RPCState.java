package net.allthemods.allthetweaks.client.discord;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;

public enum RPCState {
    STARTING("Starting..."),
    MAIN_MENU("Main Menu"),
    PLAYING("Playing"),
    SHUTTING_DOWN("Shutting Down...");
    
    private final String display;
    
    RPCState(String display) {
        this.display = display;
    }
    
    public String display() {
        return this.display;
    }
    
    public static RPCState resolve() {
        Minecraft minecraft = Minecraft.getInstance();
        Screen currentScreen = minecraft.screen;
        Overlay currentOverlay = minecraft.getOverlay();
        
        if (minecraft.level != null && minecraft.player != null) {
            return RPCState.PLAYING;
        }
        
        if (currentOverlay != null) {
            return RPCState.STARTING;
        }
        
        if (currentScreen == null) {
            return RPCState.STARTING;
        }
        
        return RPCState.MAIN_MENU;
    }
}