package com.thevortex.allthetweaks.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.TypeRewriteRule;
import com.thevortex.allthetweaks.config.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.gui.screens.ProgressScreen;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.thevortex.allthetweaks.AllTheTweaks;

@Mixin(value = LoadingOverlay.class, priority = 1)
public abstract class ProgressLoadGuiMixin {

	@Shadow
	public static final long FADE_IN_TIME = 0L;
	private static final String launch = Minecraft.getInstance().gameDirectory.getAbsolutePath();
	private static final ResourceLocation MOJANG_DRUNK = new ResourceLocation("allthetweaks",
																				  "textures/gui/title/mojangstudios.png");


	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/resources/ResourceLocation;)V"))
	private void myRender(PoseStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_,
						  CallbackInfo ci) {

		//LoadingOverlay.MOJANG_STUDIOS_LOGO_LOCATION = MOJANG_DRUNK;
		int i = Minecraft.getInstance().getWindow().getGuiScaledWidth();
		int j = Minecraft.getInstance().getWindow().getGuiScaledHeight();
		int j2 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledWidth() * 0.5D);
		int i1 = (int)((double)Minecraft.getInstance().getWindow().getGuiScaledHeight() * 0.5D);
		double d0 = Math.min((double)Minecraft.getInstance().getWindow().getGuiScaledWidth() * 0.75D, AllTheTweaks.proxy.getMinecraft().getWindow().getGuiScaledHeight()) * 0.25D;
		int j1 = (int)(d0 * 0.5D);
		double d1 = d0 * 4.0D;
		int k1 = (int)(d1 * 0.5D);


		switch (Configuration.COMMON.mainmode.get()) {
		case 0:
			RenderSystem.setShaderTexture(0,cfgMain.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);
			//RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			//LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			//LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);

			break;
		case 1:
			RenderSystem.setShaderTexture(0,cfgSLOP.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			//RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			//LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			//LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);

			break;

		case 2:
			RenderSystem.setShaderTexture(0,cfgSKY.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			//RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			//LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			//LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);

			break;
		case 3:
			RenderSystem.setShaderTexture(0,cfgMAGIC.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F, 0.0F, 120, 120, 120, 120);
			//RenderSystem.setShaderTexture(0,MOJANG_DRUNK);
			//LoadingOverlay.blit(matrixStack, j2 - k1, i1 - j1, k1, (int)d0, -0.0625F, 0.0F, 120, 60, 120, 120);
			//LoadingOverlay.blit(matrixStack, j2, i1 - j1, k1, (int)d0, 0.0625F, 60.0F, 120, 60, 120, 120);
			break;
		case 4:
			RenderSystem.setShaderTexture(0,cfgMain.BACKGROUND);
			LoadingOverlay.blit(matrixStack, 0, 0, i, j, -0.0625F,0.0F, 120, 120, 120, 120);

			break;
	}

	}


/*
	@ModifyArg(method = {
			"func_238629_a_" }, at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/client/gui/ResourceLoadProgressGui;func_238467_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIII)V", remap = false), index = 5)
	private int barBorderColor(int color) {
		return ColorHelper.PackedColor.func_233006_a_(255, 185, 55, 35);
	}

	@ModifyArg(method = {
			"func_238629_a_" }, at = @At(value = "INVOKE", ordinal = 2, target = "Lnet/minecraft/client/gui/ResourceLoadProgressGui;func_238467_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIII)V", remap = false), index = 5)
	private int barBorderColor1(int color) {
		return ColorHelper.PackedColor.func_233006_a_(255, 185, 55, 35);
	}

	@ModifyArg(method = {
			"func_238629_a_" }, at = @At(value = "INVOKE", ordinal = 3, target = "Lnet/minecraft/client/gui/ResourceLoadProgressGui;func_238467_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIII)V", remap = false), index = 5)
	private int barBorderColor2(int color) {
		return ColorHelper.PackedColor.func_233006_a_(255, 185, 55, 35);
	}

	@ModifyArg(method = {
			"func_238629_a_" }, at = @At(value = "INVOKE", ordinal = 4, target = "Lnet/minecraft/client/gui/ResourceLoadProgressGui;func_238467_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIII)V", remap = false), index = 5)
	private int barBorderColor3(int color) {
		return ColorHelper.PackedColor.func_233006_a_(255, 185, 55, 35);
	}

	@ModifyArg(method = {
			"func_238629_a_" }, at = @At(value = "INVOKE", ordinal = 5, target = "Lnet/minecraft/client/gui/ResourceLoadProgressGui;func_238467_a_(Lcom/mojang/blaze3d/matrix/MatrixStack;IIIII)V", remap = false), index = 5)
	private int barColor(int color) {
		return ColorHelper.PackedColor.func_233006_a_(255, 251, 238, 30);
	}

	*/



 }

