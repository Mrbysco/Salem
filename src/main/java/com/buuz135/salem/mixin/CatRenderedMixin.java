package com.buuz135.salem.mixin;

import com.buuz135.salem.Salem;
import com.buuz135.salem.SalemClient;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatRenderer.class)
public class CatRenderedMixin {

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/CatRenderState;)Lnet/minecraft/resources/Identifier;", at = @At("HEAD"), cancellable = true)
    public void getTextureLocation(CatRenderState state, CallbackInfoReturnable<Identifier> cir) {
        if (state.getRenderDataOrDefault(SalemClient.ENLARGED, false)){
            cir.setReturnValue(Identifier.fromNamespaceAndPath(Salem.MODID, "textures/entity/salem.png"));
        }
    }
}
