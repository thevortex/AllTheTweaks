package com.thevortex.allthetweaks;

import com.mojang.datafixers.TypeRewriteRule;
import com.thevortex.allthetweaks.DRP.EnumState;
import com.thevortex.allthetweaks.DRP.State;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.player.LocalPlayer;

import net.minecraftforge.client.event.ScreenEvent;

import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import net.minecraftforge.api.distmarker.Dist;

@EventBusSubscriber(modid = AllTheTweaks.MODID, value = Dist.CLIENT)
public class UpdateDRP {
		
		@SubscribeEvent
		public void on(ScreenEvent.Init.Pre event) {
			if (!DRP.isEnabled()) {
				return;
			}
			if (event.getScreen() instanceof TitleScreen || event.getScreen() instanceof SelectWorldScreen || event.getScreen() instanceof ConnectScreen) {
				final State state = DRP.getCurrent();
				if (state == null || state.getState() != EnumState.MENU) {
					DRP.setIdling();
				}
			}
		}
		
		@SubscribeEvent
		public void on(EntityJoinLevelEvent event) {
			if (!DRP.isEnabled()) {
				return;
			}
			if (event.getEntity() instanceof LocalPlayer) {
				final LocalPlayer player = (LocalPlayer) event.getEntity();
				if (player.getUUID().equals(Minecraft.getInstance().player.getUUID())) {
					DRP.setDimension(event.getLevel().dimension());

				}
			}
		}
	
}
