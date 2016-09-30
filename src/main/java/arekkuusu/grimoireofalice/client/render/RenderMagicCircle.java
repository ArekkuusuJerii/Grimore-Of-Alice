/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.model.ModelFlatTexture;
import arekkuusu.grimoireofalice.entity.EntityMagicCircle;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMagicCircle extends Render<EntityMagicCircle> {

	private ResourceLocation CIRCLE_TEXTURE[] = {
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_0.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_1.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_2.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_3.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_4.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_5.png"),
			new ResourceLocation(LibMod.MODID, "textures/models/entities/MagicCircle_6.png")};
	private static final ModelBase MODEL = new ModelFlatTexture();

	public RenderMagicCircle(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
    public void doRender(EntityMagicCircle circle, double x, double y, double z, float partialTicks, float destroyStage) {
		GlStateManager.pushMatrix();
		bindEntityTexture(circle);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		float size = circle.getCircleSize() * 6.0F;
		GlStateManager.scale(size, size, size);
		GlStateManager.rotate(circle.getAnimationCount() * 5F, 0.0F, 1.0F, 0.0F);
		MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityMagicCircle circle) {
		int numTexture = circle.getTexture();
		return CIRCLE_TEXTURE[numTexture];
	}
}