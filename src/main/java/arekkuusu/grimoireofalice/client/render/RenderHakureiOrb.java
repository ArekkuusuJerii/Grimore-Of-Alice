package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityHakureiOrb;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHakureiOrb extends Render<EntityHakureiOrb> {

	private static final ModelBase MODEL = new ModelFlat();

	public RenderHakureiOrb(RenderManager render) {
		super(render);
	}

	@Override
	public void doRender(EntityHakureiOrb entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		float size = entity.getSize();
		GlStateManager.scale(size, size, size);
		float angle = 90;
		GlStateManager.rotate(180F - renderManager.playerViewY + angle, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180F - renderManager.playerViewX, 0.0F, 0.0F, 1.0F);
		GlStateManager.rotate(entity.getTicksAlive() * 8, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(angle * 5F, 0.0F, 0.0F, 1.0F);
		MODEL.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHakureiOrb entityHakureiOrb) {
		return ResourceLocations.ORB_TEXTURE;
	}
}
