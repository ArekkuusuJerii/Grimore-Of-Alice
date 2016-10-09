package arekkuusu.grimoireofalice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelUnzanFist - Arekkuusu
 * Created using Tabula 5.1.0
 */
public class ModelUnzanFist extends ModelBase {

	//TODO: Name these
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer shape2_1;
	private final ModelRenderer shape2_2;
	private final ModelRenderer shape2_3;
	private final ModelRenderer shape2_4;
	private final ModelRenderer shape2_5;
	private final ModelRenderer shape2_6;

	public ModelUnzanFist() {
		this.textureWidth = 128;
		this.textureHeight = 64;
		this.shape2_2 = new ModelRenderer(this, 90, 35);
		this.shape2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_2.addBox(-3.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(-10.0F, -8.0F, -10.0F, 20, 18, 20, 0.0F);
		this.shape2_5 = new ModelRenderer(this, 81, 0);
		this.shape2_5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_5.addBox(-10.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		this.shape2 = new ModelRenderer(this, 90, 35);
		this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2.addBox(4.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		this.shape2_4 = new ModelRenderer(this, 90, 35);
		this.shape2_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_4.addBox(-10.0F, -10.0F, -11.0F, 6, 22, 6, 0.0F);
		this.shape2_6 = new ModelRenderer(this, 0, 40);
		this.shape2_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_6.addBox(-11.0F, 4.0F, -8.0F, 6, 6, 18, 0.0F);
		this.shape2_1 = new ModelRenderer(this, 81, 0);
		this.shape2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_1.addBox(4.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
		this.shape2_3 = new ModelRenderer(this, 81, 0);
		this.shape2_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2_3.addBox(-3.0F, 10.0F, -11.0F, 6, 2, 17, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape2_2.render(f5);
		this.shape1.render(f5);
		this.shape2_5.render(f5);
		this.shape2.render(f5);
		this.shape2_4.render(f5);
		this.shape2_6.render(f5);
		this.shape2_1.render(f5);
		this.shape2_3.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}