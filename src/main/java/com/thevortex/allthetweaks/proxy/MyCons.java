package com.thevortex.allthetweaks.proxy;

import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class MyCons {

    private static InputStream iconStream(String prefix, String size) throws IOException {
        var location = String.format("textures/%s/icon_%s.png", prefix, size);
        return AllTheTweaks.proxy.getMinecraft()
                .getResourceManager()
                .getResource(new ResourceLocation("allthetweaks", location))
                .getInputStream();
    }

    public static void setWindowIcon() {
        if(System.getProperties().getProperty("os.name").contains("OS")) { return; }

        String prefix = switch (Configuration.COMMON.mainmode.get()) {
            case 3 -> "icons_magic";
            case 2 -> "icons_sky";
            case 1 -> "icons_slop";
            default -> "icons";
        };

        try (var inputStream16 = iconStream(prefix, "16x16");
             var inputStream32 = iconStream(prefix, "32x32");
             var memoryStack = MemoryStack.stackPush()) {

            IntBuffer intbuffer = memoryStack.mallocInt(1);
            IntBuffer intbuffer1 = memoryStack.mallocInt(1);
            IntBuffer intbuffer2 = memoryStack.mallocInt(1);
            GLFWImage.Buffer buffer = GLFWImage.mallocStack(2, memoryStack);
            ByteBuffer bytebuffer = loadIco(inputStream16, intbuffer, intbuffer1, intbuffer2);
            if (bytebuffer == null) {
                throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
            }

            buffer.position(0);
            buffer.width(intbuffer.get(0));
            buffer.height(intbuffer1.get(0));
            buffer.pixels(bytebuffer);
            ByteBuffer bytebuffer1 = loadIco(inputStream32, intbuffer, intbuffer1, intbuffer2);
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


        } catch (IOException e) {
            AllTheTweaks.LOGGER.error("Couldn't set icon", e);
        }
    }

    @Nullable
    private static ByteBuffer loadIco(InputStream p_198111_1_, IntBuffer p_198111_2_, IntBuffer p_198111_3_, IntBuffer p_198111_4_) throws IOException {

        RenderSystem.assertInInitPhase();
        ByteBuffer bytebuffer = null;

        ByteBuffer bytebuffer1;
        try {
            bytebuffer = TextureUtil.readResource(p_198111_1_);
            bytebuffer.rewind();
            bytebuffer1 = STBImage.stbi_load_from_memory(bytebuffer, p_198111_2_, p_198111_3_, p_198111_4_, 0);
        } finally {
            if (bytebuffer != null) {
                MemoryUtil.memFree(bytebuffer);
            }

        }

        return bytebuffer1;

    }


}
