package com.thevortex.allthetweaks.proxy;

import java.io.IOException;
import java.io.InputStream;

import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.DRP;
import com.thevortex.allthetweaks.UpdateDRP;

import com.thevortex.allthetweaks.config.Configuration;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
public class ClientProxy implements IProxy {
    @Override
    public void init() {
        if(!System.getProperties().get("os.name").toString().toLowerCase().contains("mac")){
            MyCons.setWindowIcon();
        }
        if(Configuration.COMMON.discord.get()) {
            MinecraftForge.EVENT_BUS.register(new UpdateDRP());
            DRP.start();
        }
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

    @Override
    public void assignBG(int packmode){
        Gui.BACKGROUND_LOCATION = new ResourceLocation("allthetweaks" , "textures/gui/options_background." + packmode + ".png");

    }
}