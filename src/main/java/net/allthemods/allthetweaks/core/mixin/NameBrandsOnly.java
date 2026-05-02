package net.allthemods.allthetweaks.core.mixin;

import net.allthemods.allthetweaks.AllTheTweaks;
import net.allthemods.allthetweaks.client.discord.PackMode;
import net.allthemods.allthetweaks.proxy.BCCProxy;
import net.minecraft.DetectedVersion;
import net.neoforged.fml.ModList;
import net.neoforged.fml.i18n.FMLTranslations;
import net.neoforged.neoforge.common.NeoForgeVersion;
import net.neoforged.neoforge.internal.BrandingControl;

import net.minecraft.SharedConstants;

import net.allthemods.allthetweaks.ATTConfig;

import com.google.common.collect.ImmutableList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(value = BrandingControl.class, remap = false, priority = 1)

public class NameBrandsOnly {
    @Shadow(remap = false)
    private static List<String> brandings;
    @Shadow(remap = false)
    private static List<String> brandingsNoMC;

    /**
     * @author thevortex
     */
    @Overwrite(remap = false)
    private static void computeBranding() {
        AllTheTweaks.DISPLAY = PackMode.ATM11.name().replace("ATM", "All The Mods ");

        if (brandings == null) {
            ImmutableList.Builder<String> brd = ImmutableList.builder();


            if (AllTheTweaks.mfContainer.isPresent() && AllTheTweaks.brandingModernFix) {
                brd.add("ModernFix " + AllTheTweaks.mfContainer.get().getModInfo().getVersion().toString());
            }
            int tModCount = AllTheTweaks.ModsLoaded;
            brd.add(FMLTranslations.parseMessage(tModCount + " Mods Loaded", tModCount));
            brd.add("NeoForge " + NeoForgeVersion.getVersion());
            brd.add("Minecraft 26.1.2");
            if (AllTheTweaks.BCC) {
                brd.add(AllTheTweaks.DISPLAY + " v" + BCCProxy.getVersion());
            }
            brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());

        }
    }
}

