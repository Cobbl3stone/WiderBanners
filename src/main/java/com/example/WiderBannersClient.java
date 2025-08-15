package com.example;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.block.entity.BlockEntityType;

public class WiderBannersClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.register(BlockEntityType.BANNER, ctx -> new WideBannerRenderer(ctx));
    }
}
