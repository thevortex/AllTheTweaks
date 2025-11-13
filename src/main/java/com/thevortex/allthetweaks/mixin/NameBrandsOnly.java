package com.thevortex.allthetweaks.mixin;

import com.google.common.collect.ImmutableList;
import com.thevortex.allthetweaks.AllTheTweaks;
import com.thevortex.allthetweaks.config.Configuration;

import com.thevortex.allthetweaks.proxy.BCCProxy;
import net.minecraft.DetectedVersion;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.i18n.FMLTranslations;
import net.neoforged.neoforge.internal.BrandingControl;
import net.neoforged.neoforge.internal.versions.neoforge.NeoForgeVersion;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Optional;

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
    private static void computeBranding()
    {
        if (brandings == null)
        {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            if(AllTheTweaks.BCC){
                brd.add(AllTheTweaks.DISPLAY + " v" + BCCProxy.getVersion());
            }
            if(AllTheTweaks.mfContainer.isPresent() && AllTheTweaks.brandingModernFix){brd.add("ModernFix " + AllTheTweaks.mfContainer.get().getModInfo().getVersion().toString());}
            int tModCount = AllTheTweaks.ModsLoaded;
            brd.add(FMLTranslations.parseMessage(tModCount + " Mods Loaded", tModCount));
            brd.add("NeoForge " + NeoForgeVersion.getVersion());
            brd.add("Minecraft " + DetectedVersion.BUILT_IN.getName());
             brandings = brd.build();
            brandingsNoMC = brandings.subList(1, brandings.size());

        }
    }


}
