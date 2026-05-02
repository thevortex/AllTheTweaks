package net.allthemods.allthetweaks;

import net.allthemods.allthetweaks.proxy.MFProxy;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

import net.allthemods.allthetweaks.api.ATT;
import net.allthemods.allthetweaks.core.ATTRegistry;
import net.allthemods.allthetweaks.core.reflection.PipesUpgradeEnumOverwrite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Mod(ATT.MOD_ID)
@EventBusSubscriber(modid = ATT.MOD_ID)
public class AllTheTweaks {
    public static boolean BCC;
    public static final Logger LOGGER = LoggerFactory.getLogger(AllTheTweaks.class);
    public static int ModsLoaded;
    public static Optional<? extends ModContainer> mfContainer;
    public static String DISPLAY;
    public static boolean brandingModernFix = false;
    public AllTheTweaks(final IEventBus bus, final ModContainer container) {
        ATTRegistry.register(bus);
        ATTConfig.register(container);
        if(ModList.get().isLoaded("bcc")){
            BCC = true;
        } else {
            BCC = false;
        }
        ModsLoaded = ModList.get().size();
        mfContainer = ModList.get().getModContainerById("modernfix");
        if (ModList.get().isLoaded("modernfix")) {
            brandingModernFix = MFProxy.brandingEnabled();
        }

    }
    
    @SubscribeEvent
    public static void onLoadComplete(final FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            if (ModList.get().isLoaded("pipez")) PipesUpgradeEnumOverwrite.overrideEnumEntries();
        });

    }
}
