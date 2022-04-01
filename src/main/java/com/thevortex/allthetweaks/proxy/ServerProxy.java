package com.thevortex.allthetweaks.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public class ServerProxy implements IProxy {
    @Override
    public void init() {

    }

    @Override
    public ClientLevel getClientWorld() {
        throw new IllegalStateException("Cannot be run on the server!");
    }

    @Override
    public LocalPlayer getClientPlayer() {
        throw new IllegalStateException("Cannot be run on the server!");
    }

	@Override
	public Minecraft getMinecraft() {throw new IllegalStateException("Cannot be run on the server!");}
    @Override
    public void assignBG(int packmode) {   }
}