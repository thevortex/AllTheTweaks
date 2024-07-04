package com.thevortex.allthetweaks.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;

public interface IProxy {
   void init();
   ClientLevel getClientWorld();
   LocalPlayer getClientPlayer();
   Minecraft getMinecraft();
}
