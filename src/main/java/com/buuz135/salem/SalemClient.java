package com.buuz135.salem;

import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;

@EventBusSubscriber(Dist.CLIENT)
public class SalemClient {
	public static final ContextKey<Boolean> ENLARGED = new ContextKey<>(Identifier.fromNamespaceAndPath("salem", "enlarged"));

	@SubscribeEvent
	public static void registerCustomRenderData(RegisterRenderStateModifiersEvent event) {
		event.registerEntityModifier(CatRenderer.class, (cat, renderState) -> {
			var attribute = cat.getAttribute(Attributes.SCALE);
			if (attribute != null && attribute.hasModifier(SalemContent.Effect.ENLARGE_ATTRIBUTE)){
				renderState.setRenderData(ENLARGED, true);
			}
		});
	}

}
