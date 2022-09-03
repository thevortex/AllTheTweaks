package com.thevortex.allthetweaks.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.TypeRewriteRule;
import com.thevortex.allthetweaks.config.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.gui.screens.ProgressScreen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.thevortex.allthetweaks.AllTheTweaks;

import static net.minecraft.client.gui.GuiComponent.fill;

@Mixin(value = LoadingOverlay.class, priority = 1)
public abstract class ProgressLoadGuiMixin {


	@Shadow
	public float currentProgress;

	private static final String launch = Minecraft.getInstance().gameDirectory.getAbsolutePath();
	private static final ResourceLocation MOJANG_DRUNK = new ResourceLocation("allthetweaks",
																				  "textures/gui/title/mojangstudios.png");
	private static ResourceLocation FLAME = new ResourceLocation("minecraft","textures/block/fire_0.png");

	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"))
	private void myRender(PoseStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_,
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
		switch (Configuration.COMMON.mainmode.get()) {
		case 0:
			RenderSystem.setShaderTexture(0,cfgMain.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);
			RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			RenderSystem.setShaderTexture(0,FLAME);
			renderStack(matrixStack,i4 + 5,j2-k1,i,j,k1);
			break;
		case 1:
			FLAME = new ResourceLocation("allthetweaks","textures/gui/title/pie-loading.png");

			RenderSystem.setShaderTexture(0,cfgSLOP.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			RenderSystem.setShaderTexture(0,FLAME);
			renderStack(matrixStack,i4,j2-k1,i,j,k1);
			break;

		case 2:
			RenderSystem.setShaderTexture(0,cfgSKY.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			RenderSystem.setShaderTexture(0,FLAME);
			renderStack(matrixStack,i4,j2-k1,i,j,k1);
			break;
		case 3:
			RenderSystem.setShaderTexture(0,cfgMAGIC.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			RenderSystem.setShaderTexture(0,FLAME);
			renderStack(matrixStack,i4,j2-k1,i,j,k1);
			break;
		case 4:
			RenderSystem.setShaderTexture(0,cfgMain.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);
			RenderSystem.setShaderTexture(0,FLAME);
			renderStack(matrixStack,i4,j2-k1,i,j,k1);
			break;
	}

	}

	private void renderStack(PoseStack stack,int i4,int j4, int i, int j, int k1) {
		int w = Mth.ceil((float)(i4*3-j4 + k1/4) * this.currentProgress);
		LoadingOverlay.blit(stack, j4, i4*3,j4+w,16,0.0F,0.0F,j4+w,16,16,16);

	}

	@Overwrite
	private static int replaceAlpha(int one, int two) {
		return 0;
	}



	@Inject(method = "drawProgressBar", at = @At(value = "HEAD", target = "Lnet/minecraft/client/gui/screens/LoadingOverlay;drawProgressBar(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIF)V"), cancellable = true)
	private void cancelGoHome(PoseStack pPoseStack, int pMinX, int pMinY, int pMaxX, int pMaxY, float pPartialTick,CallbackInfo cin) {
		cin.cancel();
	}




}

