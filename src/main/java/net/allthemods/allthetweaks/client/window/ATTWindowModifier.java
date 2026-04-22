package net.allthemods.allthetweaks.client.window;

import net.minecraft.client.Minecraft;

import net.allthemods.allthetweaks.ATTConfig;
import net.allthemods.allthetweaks.AllTheTweaks;
import net.allthemods.allthetweaks.client.discord.PackMode;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.Window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ATTWindowModifier {
    
    public static void apply() {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Window window = minecraft.getWindow();
            PackMode mode = ATTConfig.PACK_MODE.get();
            
            try {
                ATTWindowModifier.setIcon(window, mode.getIcon16(), mode.getIcon32());
            } catch (Exception exception) {
                AllTheTweaks.LOGGER.error(
                        "Failed to load window icons for {}",
                        mode.name().toLowerCase(Locale.ROOT),
                        exception
                );
            }
        });
    }
    
    private static void setIcon(Window window, String icon16Path, String icon32Path) throws IOException {
        int platform = GLFW.glfwGetPlatform();
        if (platform != GLFW.GLFW_PLATFORM_WIN32 && platform != GLFW.GLFW_PLATFORM_X11) return;
        
        List<ByteBuffer> allocated = new ArrayList<>();
        
        try (MemoryStack stack = MemoryStack.stackPush()) {
            GLFWImage.Buffer icons = GLFWImage.malloc(2, stack);
            
            ATTWindowModifier.loadIcon(icons, allocated, 0, icon16Path);
            ATTWindowModifier.loadIcon(icons, allocated, 1, icon32Path);
            
            GLFW.glfwSetWindowIcon(window.handle(), icons.position(0));
        } finally {
            for (ByteBuffer buffer : allocated) {
                MemoryUtil.memFree(buffer);
            }
        }
    }
    
    private static void loadIcon(
            GLFWImage.Buffer icons,
            List<ByteBuffer> allocated,
            int index,
            String path
    ) throws IOException {
        try (InputStream stream = ATTWindowModifier.class.getResourceAsStream(path)) {
            if (stream == null) throw new IOException("Missing icon resource: " + path);
            
            try (NativeImage image = NativeImage.read(stream)) {
                ByteBuffer pixels = MemoryUtil.memAlloc(image.getWidth() * image.getHeight() * 4);
                allocated.add(pixels);
                
                pixels.asIntBuffer().put(image.getPixelsABGR());
                
                icons.position(index);
                icons.width(image.getWidth());
                icons.height(image.getHeight());
                icons.pixels(pixels);
            }
        }
    }
}