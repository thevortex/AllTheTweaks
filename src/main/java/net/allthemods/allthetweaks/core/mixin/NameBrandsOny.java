package net.allthemods.allthetweaks.core.mixin;

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
public class NameBrandsOny {
    
    @Shadow(remap = false)
    private static List<String> brandings;
    
    @Shadow(remap = false)
    private static List<String> brandingsNoMC;
    
    /**
     * @author thevortex
     */
    @Overwrite(remap = false)
    private static void computeBranding() {
        if (NameBrandsOny.brandings == null) {
            ImmutableList.Builder<String> brd = ImmutableList.builder();
            brd.add(ATTConfig.PACK_MODE.get().getWindowTitle());
            brd.add("Minecraft " + SharedConstants.getCurrentVersion().name());
            int modCount = ModList.get().size();
            brd.add(FMLTranslations.parseMessage("fml.menu.branding", BrandingControl.BRANDING_NAME + ' ' + NeoForgeVersion.getVersion(), modCount));
            NameBrandsOny.brandings = brd.build();
            NameBrandsOny.brandingsNoMC = NameBrandsOny.brandings.subList(1, NameBrandsOny.brandings.size());
        }
    }
}
