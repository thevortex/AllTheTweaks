package com.thevortex.allthetweaks.mixin;

import com.google.common.collect.ImmutableList;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;

import net.minecraftforge.common.ForgeI18n;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.internal.BrandingControl;
import net.minecraftforge.versions.forge.ForgeVersion;
import net.minecraftforge.versions.mcp.MCPVersion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(BrandingControl.class)

public class NameBrandsOnly {
    @Shadow(remap = false)
    private static List<String> brandings;
    @Shadow(remap = false)
    private static List<String> brandingsNoMC;

    /**
     * @author thevortex
     */
    @Overwrite(remap = false)
    private static void computeBranding()
    {
        if (brandings == null)
        {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            brd.add(AllTheTweaks.DISPLAY /*+ " " + Configuration.COMMON.majorver.get() + "." + Configuration.COMMON.minorver.get() + "." + Configuration.COMMON.minorrevver.get()*/);
            brd.add("Forge " + ForgeVersion.getVersion());
            brd.add("Minecraft " + MCPVersion.getMCVersion());
            brd.add("MCP " + MCPVersion.getMCPVersion());
            int tModCount = ModList.get().size();
            brd.add(ForgeI18n.parseMessage(tModCount + " Mods Loaded", tModCount));
            brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());
        }
    }

}
