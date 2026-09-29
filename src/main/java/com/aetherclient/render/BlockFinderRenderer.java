package com.aetherclient.render;

import com.aetherclient.AetherClient;
import com.aetherclient.config.ClientConfig.ModuleConfig;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.client.util.math.MatrixStack;

public final class BlockFinderRenderer {
    public static void draw(WorldRenderContext context, BlockPos pos, ModuleConfig c) {
        MatrixStack matrices = context.matrices();
        var camera = context.camera().getPosition();
        Box box = new Box(pos).expand(0.002).offset(-camera.x, -camera.y, -camera.z);
        VertexConsumerProvider.Immediate buffers = AetherClient.client().getBufferBuilders().getEntityVertexConsumers();
        int color = ((int)(255 * c.outlineOpacity) << 24) | (c.red << 16) | (c.green << 8) | c.blue;

        if ("FULL_BOX".equals(c.mode)) {
            VertexConsumer fill = buffers.getBuffer(RenderLayer.getDebugFilledBox());
            WorldRenderer.drawBox(matrices, fill, box, c.red / 255f, c.green / 255f, c.blue / 255f, c.fillOpacity);
        }
        VertexConsumer lines = buffers.getBuffer(RenderLayer.getDebugLineStrip(Math.max(1.0, c.lineWidth)));
        if ("CORNERS".equals(c.mode)) drawCorners(matrices, lines, box, color);
        else WorldRenderer.drawBox(matrices, lines, box, c.red / 255f, c.green / 255f, c.blue / 255f, c.outlineOpacity);
        buffers.draw();
    }

    private static void drawCorners(MatrixStack matrices, VertexConsumer v, Box b, int color) {
        double s = 0.28;
        double x1=b.minX, x2=b.maxX, y1=b.minY, y2=b.maxY, z1=b.minZ, z2=b.maxZ;
        line(matrices,v,x1,y1,z1,x1+s,y1,z1,color); line(matrices,v,x1,y1,z1,x1,y1+s,z1,color); line(matrices,v,x1,y1,z1,x1,y1,z1+s,color);
        line(matrices,v,x2,y1,z1,x2-s,y1,z1,color); line(matrices,v,x2,y1,z1,x2,y1+s,z1,color); line(matrices,v,x2,y1,z1,x2,y1,z1+s,color);
        line(matrices,v,x1,y2,z1,x1+s,y2,z1,color); line(matrices,v,x1,y2,z1,x1,y2-s,z1,color); line(matrices,v,x1,y2,z1,x1,y2,z1+s,color);
        line(matrices,v,x2,y2,z1,x2-s,y2,z1,color); line(matrices,v,x2,y2,z1,x2,y2-s,z1,color); line(matrices,v,x2,y2,z1,x2,y2,z1+s,color);
        line(matrices,v,x1,y1,z2,x1+s,y1,z2,color); line(matrices,v,x1,y1,z2,x1,y1+s,z2,color); line(matrices,v,x1,y1,z2,x1,y1,z2-s,color);
        line(matrices,v,x2,y1,z2,x2-s,y1,z2,color); line(matrices,v,x2,y1,z2,x2,y1+s,z2,color); line(matrices,v,x2,y1,z2,x2,y1,z2-s,color);
        line(matrices,v,x1,y2,z2,x1+s,y2,z2,color); line(matrices,v,x1,y2,z2,x1,y2-s,z2,color); line(matrices,v,x1,y2,z2,x1,y2,z2-s,color);
        line(matrices,v,x2,y2,z2,x2-s,y2,z2,color); line(matrices,v,x2,y2,z2,x2,y2-s,z2,color); line(matrices,v,x2,y2,z2,x2,y2,z2-s,color);
    }

    private static void line(MatrixStack m, VertexConsumer v, double x1,double y1,double z1,double x2,double y2,double z2,int color) {
        var p = m.peek().getPositionMatrix();
        v.vertex(p, (float)x1,(float)y1,(float)z1).color(color).normal(0,1,0);
        v.vertex(p, (float)x2,(float)y2,(float)z2).color(color).normal(0,1,0);
    }
}
