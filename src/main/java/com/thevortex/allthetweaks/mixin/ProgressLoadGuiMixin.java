package com.thevortex.allthetweaks.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.TypeRewriteRule;
import com.thevortex.allthetweaks.config.*;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.ProgressScreen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ReloadInstance;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraftforge.client.loading.ForgeLoadingOverlay;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.loading.progress.ProgressMeter;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.thevortex.allthetweaks.AllTheTweaks;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntSupplier;


@Mixin(value = ForgeLoadingOverlay.class, priority = 1, remap = false)
public abstract class ProgressLoadGuiMixin extends Overlay {

	@Shadow @Final private ProgressMeter progress;
	private static final ResourceLocation MOJANG_DRUNK = new ResourceLocation("allthetweaks",																				  "textures/gui/title/mojangstudios.png");
	private static ResourceLocation FLAME = new ResourceLocation("minecraft","textures/block/fire_0.png");

	public ProgressLoadGuiMixin(Minecraft p_96172_, ReloadInstance p_96173_, Consumer<Optional<Throwable>> p_96174_, boolean p_96175_) {

	}
	/**
	 * @author
	 * @reason
	 */
	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V"), cancellable = true)
	private void myRender(GuiGraphics matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_,
						  CallbackInfo ci) {

		//LoadingOverlay.MOJANG_STUDIOS_LOGO_LOCATION = MOJANG_DRUNK;
		int i = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		int j = Minecraft.getInstance().getWindow().getGuiScaledHeight();
		int j2 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledWidth() * 0.5D);
		int j4 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledWidth() * 0.25D);
		int i1 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledHeight() * 0.5D);
		int i4 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledHeight() * 0.25D);

		double d0 = Math.min((double)Minecraft.getInstance().getWindow().getGuiScaledWidth() * 0.75D, Minecraft.getInstance().getWindow().getGuiScaledHeight()) * 0.25D;
		int j1 = (int)(d0 * 0.5D);
		double d1 = d0 * 4.0D;
		int k1 = (int)(d1 * 0.5D);
		//FLAME = new ResourceLocation("allthetweaks" , "textures/gui/options_background." + Configuration.COMMON.mainmode.get() + ".png");
		FLAME = new ResourceLocation("allthetweaks","textures/item/atm_star.png");
		RenderSystem.assertOnRenderThreadOrInit();
		int packmode;
		if(AllTheTweaks.configFire) {
			packmode = Configuration.COMMON.mainmode.get();
		} else {
			packmode = 0;
		}
		switch (packmode) {
		case 0:
			matrixStack.blit(cfgMain.BACKGROUND, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);

			matrixStack.blit(MOJANG_DRUNK, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);

			renderStack(matrixStack, FLAME,i4 + 5,j2-k1,i,j,k1);
			break;
		case 1:
			FLAME = new ResourceLocation("allthetweaks","textures/gui/title/pie-loading.png");


			matrixStack.blit(cfgSLOP.BACKGROUND, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);

			matrixStack.blit(MOJANG_DRUNK, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			//renderStack(matrixStack, FLAME,i4,j2-k1,i,j,k1);
			break;

		case 2:
			matrixStack.blit(cfgSKY.BACKGROUND, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			//renderStack(matrixStack, FLAME,i4,j2-k1,i,j,k1);
			break;
		case 3:
			matrixStack.blit(cfgMAGIC.BACKGROUND, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			matrixStack.blit(MOJANG_DRUNK, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);

			//renderStack(matrixStack, FLAME,i4,j2-k1,i,j,k1);
			break;
		case 4:
			matrixStack.blit(cfgMain.BACKGROUND, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);

			//renderStack(matrixStack, FLAME,i4,j2-k1,i,j,k1);
			break;
	}
ci.cancel();
	}

	private void renderStack(GuiGraphics matrixStack, ResourceLocation flame,int i4,int j4, int i, int j, int k1) {
		int w = Mth.ceil((float)(i4*3-j4 + k1/4) * this.progress.progress());
		matrixStack.blit(flame, j4, i4*3,j4+w,16,0.0F,0.0F,j4+w,16,16,16);

	}


	private static int replaceAlpha(int p_169325_, int p_169326_) {
		return p_169325_ & 16777215 | p_169326_ << 24;
	}

	private void drawProgressBar(GuiGraphics p_283125_, int p_96184_, int p_96185_, int p_96186_, int p_96187_, float p_96188_) {
		int i = Mth.ceil((float)(p_96186_ - p_96184_ - 2) * this.progress.progress());
		int j = Math.round(p_96188_ * 255.0F);
		int k = FastColor.ARGB32.color(j, 255, 255, 255);
		p_283125_.fill(p_96184_ + 2, p_96185_ + 2, p_96184_ + i, p_96187_ - 2, k);
		p_283125_.fill(p_96184_ + 1, p_96185_, p_96186_ - 1, p_96185_ + 1, k);
		p_283125_.fill(p_96184_ + 1, p_96187_, p_96186_ - 1, p_96187_ - 1, k);
		p_283125_.fill(p_96184_, p_96185_, p_96184_ + 1, p_96187_, k);
		p_283125_.fill(p_96186_, p_96185_, p_96186_ - 1, p_96187_, k);
	}




}

