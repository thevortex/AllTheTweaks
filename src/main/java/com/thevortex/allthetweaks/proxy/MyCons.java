package com.thevortex.allthetweaks.proxy;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import org.lwjgl.glfw.GLFW;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class MyCons {

	public static void setWindowIcon() {


		InputStream inputstream = null;
		  InputStream inputstream1 = null;
	   RenderSystem.assertInInitPhase();
	   try {
			 if(Configuration.COMMON.mainmode.get() == 3) {
				 inputstream = AllTheTweaks.proxy.getMinecraft().getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("allthetweaks", "icons_magic/icon_16x16.png"));
				 inputstream1 = AllTheTweaks.proxy.getMinecraft().getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("allthetweaks", "icons_magic/icon_32x32.png"));

			 }
	   	if(Configuration.COMMON.mainmode.get() == 2) {
				inputstream = AllTheTweaks.proxy.getMinecraft().getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("allthetweaks", "icons_sky/icon_16x16.png"));
				inputstream1 = AllTheTweaks.proxy.getMinecraft().getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("allthetweaks", "icons_sky/icon_32x32.png"));

			}
	   	if(Configuration.COMMON.mainmode.get() <= 1) {

				inputstream = Minecraft.getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES,new ResourceLocation("allthetweaks:textures/icons/icon_16x16.png"));
				inputstream1 = Minecraft.getInstance().getClientPackSource().getVanillaPack().getResource(PackType.CLIENT_RESOURCES, new ResourceLocation("allthetweaks:textures/icons/icon_32x32.png"));
			}

	   
	   } catch(IOException io) {
	  	 
	   }
	   try (MemoryStack memorystack = MemoryStack.stackPush()) {

	     if (inputstream == null) {
	      throw new FileNotFoundException("icons/icon_16x16.png");
	     }

	     if (inputstream1 == null) {
	      throw new FileNotFoundException("icons/icon_32x32.png");
	     }



	     IntBuffer intbuffer = memorystack.mallocInt(1);
	     IntBuffer intbuffer1 = memorystack.mallocInt(1);
	     IntBuffer intbuffer2 = memorystack.mallocInt(1);
	     GLFWImage.Buffer buffer = GLFWImage.mallocStack(2, memorystack);
	     ByteBuffer bytebuffer = loadIco(inputstream, intbuffer, intbuffer1, intbuffer2);
	     if (bytebuffer == null) {
	      throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
	     }

	     buffer.position(0);
	     buffer.width(intbuffer.get(0));
	     buffer.height(intbuffer1.get(0));
	     buffer.pixels(bytebuffer);
	     ByteBuffer bytebuffer1 = loadIco(inputstream1, intbuffer, intbuffer1, intbuffer2);
	     if (bytebuffer1 == null) {
	      throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
	     }

	     buffer.position(1);
	     buffer.width(intbuffer.get(0));
	     buffer.height(intbuffer1.get(0));
	     buffer.pixels(bytebuffer1);
	     buffer.position(0);
	     GLFW.glfwSetWindowIcon(AllTheTweaks.proxy.getMinecraft().getWindow().getWindow(), buffer);
	     STBImage.stbi_image_free(bytebuffer);
	     STBImage.stbi_image_free(bytebuffer1);
	   } catch (IOException ioexception) {
	     AllTheTweaks.LOGGER.error("Couldn't set icon", (Throwable)ioexception);
	   }
	}
		@Nullable
		private static ByteBuffer loadIco(InputStream p_198111_1_, IntBuffer p_198111_2_, IntBuffer p_198111_3_, IntBuffer p_198111_4_) throws IOException {

			RenderSystem.assertInInitPhase();
			ByteBuffer bytebuffer = null;

			ByteBuffer bytebuffer1;
			try {
				bytebuffer = TextureUtil.readResource(p_198111_1_);
				((java.nio.Buffer)bytebuffer).rewind();
				bytebuffer1 = STBImage.stbi_load_from_memory(bytebuffer, p_198111_2_, p_198111_3_, p_198111_4_, 0);
			} finally {
				if (bytebuffer != null) {
					MemoryUtil.memFree(bytebuffer);
				}

			}

			return bytebuffer1;

		}



}
