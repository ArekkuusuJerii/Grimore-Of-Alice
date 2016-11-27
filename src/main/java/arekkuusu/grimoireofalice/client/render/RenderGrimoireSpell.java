/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.render;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.client.model.ModelFlat;
import arekkuusu.grimoireofalice.common.entity.EntityGrimoireSpell;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderGrimoireSpell extends Render<EntityGrimoireSpell> {

	private final ModelBook modelBook = new ModelBook();
	private static final ModelBase MODEL = new ModelFlat();

	public RenderGrimoireSpell(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityGrimoireSpell circle, double x, double y, double z, float yaw, float pitch) {

		//TODO: Name variables here
		GlStateManager.pushMatrix();
		bindEntityTexture(circle);
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		float size = 6.0F - circle.getTickCount() * 0.01F;
		GlStateManager.scale(size, size, size);
		GlStateManager.rotate(circle.getTickCount() * 16F, 0.0F, 1.0F, 0.0F);
		MODEL.render(circle, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5F, y + 0.75F, z + 0.5F);
		float f = circle.getTickCount() + 5;
		GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(f * 0.1F) * 0.01F, 0.0F);

		float f1 = circle.getBookRotation() - circle.getBookRotationPrev();

		while(f1 >= Math.PI) {
			f1 -= Math.PI * 2F;
		}
		while(f1 < -Math.PI) {
			f1 += Math.PI * 2F;
		}

		float f2 = circle.getBookRotationPrev() + f1 * 5;
		GlStateManager.rotate(-f2 * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
		bindTexture(ResourceLocations.GRIMOIRE_BOOK);
		float f3 = circle.getPageFlipPrev() + (circle.getPageFlip() - circle.getPageFlipPrev()) * 5 + 0.25F;
		float f4 = circle.getPageFlipPrev() + (circle.getPageFlip() - circle.getPageFlipPrev()) * 5 + 0.75F;
		f3 = (f3 - MathHelper.truncateDoubleToInt(f3)) * 1.6F - 0.3F;
		f4 = (f4 - MathHelper.truncateDoubleToInt(f4)) * 1.6F - 0.3F;

		if(f3 < 0.0F) {
			f3 = 0.0F;
		}

		if(f4 < 0.0F) {
			f4 = 0.0F;
		}

		if(f3 > 1.0F) {
			f3 = 1.0F;
		}

		if(f4 > 1.0F) {
			f4 = 1.0F;
		}

		float f5 = circle.getBookSpreadPrev() + (circle.getBookSpread() - circle.getBookSpreadPrev()) * 5;
		GlStateManager.enableCull();
		modelBook.render(null, f, f3, f4, f5, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGrimoireSpell circle) {
		return ResourceLocations.GRIMOIRE_CIRCLE_TEXTURE;
	}
}
