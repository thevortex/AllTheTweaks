package com.thevortex.allthetweaks.proxy;

import com.mojang.blaze3d.platform.MacosUtil;
import com.mojang.blaze3d.platform.NativeImage;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.IoSupplier;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class MyCons {

    private static String getPrefix() {
        return switch (Configuration.COMMON.mainmode.get()) {
            case 5 -> "icons_grav";
            case 3 -> "icons_magic";
            case 2 -> "icons_sky";
            case 1 -> "icons_slop";
            default -> "icons";
        };
    }

    private static List<IoSupplier<InputStream>> getStandardIcons() {
        var list = new ArrayList<IoSupplier<InputStream>>();
        String prefix = getPrefix();
        short[] sizes = {16, 32, 48, 128, 256};
        for (short size : sizes) {
            var location = String.format("textures/%s/icon_%sx%s.png", prefix, size, size);
            var resource = Minecraft.getInstance().getResourceManager().getResource(ResourceLocation.fromNamespaceAndPath("allthetweaks", location));
            resource.ifPresent(value -> list.add(value::open));
        }
        return list;
    }

    @Nullable
    private static IoSupplier<InputStream> getMacIcon() {
        String prefix = getPrefix();
        var location = String.format("textures/%s/mac_icon.icns", prefix);
        var resourceOpt = Minecraft.getInstance().getResourceManager().getResource(ResourceLocation.fromNamespaceAndPath("allthetweaks", location));
        if (resourceOpt.isPresent()) {
            return () -> resourceOpt.get().open();
        }
        return null;
    }

    public static void setWindowIcon() throws IOException {
        int i = GLFW.glfwGetPlatform();
        switch (i) {
            case GLFW.GLFW_PLATFORM_WIN32, GLFW.GLFW_PLATFORM_X11:
                List<IoSupplier<InputStream>> list = getStandardIcons();
                List<ByteBuffer> list1 = new ArrayList<>(list.size());

                try (MemoryStack memorystack = MemoryStack.stackPush()) {
                    GLFWImage.Buffer buffer = GLFWImage.malloc(list.size(), memorystack);

                    for (int j = 0; j < list.size(); j++) {
                        try (NativeImage nativeimage = NativeImage.read(list.get(j).get())) {
                            ByteBuffer bytebuffer = MemoryUtil.memAlloc(nativeimage.getWidth() * nativeimage.getHeight() * 4);
                            list1.add(bytebuffer);
                            bytebuffer.asIntBuffer().put(nativeimage.getPixelsRGBA());
                            buffer.position(j);
                            buffer.width(nativeimage.getWidth());
                            buffer.height(nativeimage.getHeight());
                            buffer.pixels(bytebuffer);
                        }
                    }

                    GLFW.glfwSetWindowIcon(Minecraft.getInstance().getWindow().getWindow(), buffer.position(0));
                } finally {
                    list1.forEach(MemoryUtil::memFree);
                }
                break;
            case GLFW.GLFW_PLATFORM_COCOA:
                // use to convert png->icns: https://miconv.com/png-to-icns/
                // use the original png, not some already scaled down
                var icon = getMacIcon();
                if (icon != null) {
                    MacosUtil.loadIcon(icon);
                }
                break;
            case GLFW.GLFW_PLATFORM_WAYLAND, GLFW.GLFW_PLATFORM_NULL:
                break;
            default:
                AllTheTweaks.LOGGER.error("Couldn't set icon for platform {}", i);
        }
    }
}
