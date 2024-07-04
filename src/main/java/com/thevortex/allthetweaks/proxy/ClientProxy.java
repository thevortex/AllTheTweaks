package com.thevortex.allthetweaks.proxy;

import java.util.Locale;

import com.thevortex.allthetweaks.DRP;
import com.thevortex.allthetweaks.UpdateDRP;

import com.thevortex.allthetweaks.config.Configuration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.common.NeoForge;
public class ClientProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    public ClientLevel getClientWorld() {
        return Minecraft.getInstance().level;
    }

    @Override
    public LocalPlayer getClientPlayer() {
        return Minecraft.getInstance().player;
    }
    @Override
    public Minecraft getMinecraft() {
    	return Minecraft.getInstance();
    }

    
}