package com.example;

import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class WideBannerRenderer extends BannerBlockEntityRenderer {

    public WideBannerRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(
            BannerBlockEntity bannerBlockEntity,
            float f,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            int j,
            Vec3d vec3d
    ) {
        matrixStack.push();

        // Parámetros de escala
        float scaleX = 1.199F; // Ancho
        float scaleY = 1.1F; // Alto
        float scaleZ = 1.199F; // Profundidad

        boolean isWall = bannerBlockEntity.getCachedState().getBlock() instanceof WallBannerBlock;

        if (isWall) {
            Direction facing = bannerBlockEntity.getCachedState().get(WallBannerBlock.FACING);

            // Mover al centro
            matrixStack.translate(0.5, 0.0, 0.5);

            // Ajuste fino según orientación para evitar desplazamiento lateral
            switch (facing) {
                case NORTH -> matrixStack.translate(0.0, 0.0, 0.0625);
                case SOUTH -> matrixStack.translate(0.0, 0.0, -0.0625);
                case WEST  -> matrixStack.translate(0.0625, 0.0, 0.0);
                case EAST  -> matrixStack.translate(-0.0625, 0.0, 0.0);
            }

            // Compensar para que el extra de altura se extienda hacia abajo
            matrixStack.translate(0.0, -(scaleY - 1.0F), 0.0);

            // Escalar
            matrixStack.scale(scaleX, scaleY, scaleZ);

            // Revertir ajuste fino
            switch (facing) {
                case NORTH -> matrixStack.translate(0.0, 0.0, -0.0625);
                case SOUTH -> matrixStack.translate(0.0, 0.0, 0.0625);
                case WEST  -> matrixStack.translate(-0.0625, 0.0, 0.0);
                case EAST  -> matrixStack.translate(0.0625, 0.0, 0.0);
            }

            // Volver al origen
            matrixStack.translate(-0.5, 0.0, -0.5);

        } else {
            // Banner de pie
            matrixStack.translate(0.5, 0.0, 0.5);

            // Compensar altura extra para que crezca hacia abajo
            matrixStack.translate(0.0, -(scaleY - 1.0F), 0.0);

            // Escalar
            matrixStack.scale(scaleX, scaleY, scaleZ);

            // Volver al origen
            matrixStack.translate(-0.5, 0.0, -0.5);
        }

        // Render original
        super.render(bannerBlockEntity, f, matrixStack, vertexConsumerProvider, i, j, vec3d);

        matrixStack.pop();
    }


}


