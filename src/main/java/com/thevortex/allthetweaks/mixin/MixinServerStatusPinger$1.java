package com.thevortex.allthetweaks.mixin;

import com.aizistral.nochatreports.common.NCRCore;
import com.aizistral.nochatreports.common.config.NCRConfig;
import com.aizistral.nochatreports.common.core.ServerDataExtension;
import com.aizistral.nochatreports.common.platform.extensions.ServerPingerExtension;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerStatusPinger;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.status.ClientboundStatusResponsePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetSocketAddress;

@Pseudo
@Mixin(targets = "net/minecraft/client/multiplayer/ServerStatusPinger$1")
public abstract class MixinServerStatusPinger$1 implements ServerPingerExtension {

    static {
        if (NCRConfig.getCommon().enableDebugLog()) {
            NCRCore.LOGGER.info("Common mixin into ServerStatusPinger$1 succeeded.");
        }
    }

    @Unique
    private ServerDataExtension nochatreports$serverData;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void captureServerData(ServerStatusPinger serverStatusPinger, Connection connection, ServerData serverData, Runnable runnable, Runnable runnable2, InetSocketAddress inetSocketAddress, ServerAddress serverAddress, CallbackInfo ci) {
        nochatreports$serverData = (ServerDataExtension) serverData;
    }

    @Inject(method = "handleStatusResponse(Lnet/minecraft/network/protocol/status/ClientboundStatusResponsePacket;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/status/ServerStatus;"
                    + "description()Lnet/minecraft/network/chat/Component;"))
    private void getNoChatReports(ClientboundStatusResponsePacket packet, CallbackInfo info) {
        boolean preventsReports = ((ServerDataExtension) (Object) packet.status()).preventsChatReports();
        nochatreports$serverData.setPreventsChatReports(preventsReports);

        if (NCRConfig.getCommon().enableDebugLog()) {
            NCRCore.LOGGER.info("Received status response packet from server, preventsChatReports: {}",
                    preventsReports);
        }
    }

}
