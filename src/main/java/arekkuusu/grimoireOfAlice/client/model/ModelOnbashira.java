/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimore Of Alice is Open Source and distributed under the
 * Grimore Of Alice license: https://github.com/ArekkuusuJerii/Grimore-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOnbashira extends ModelBase {

	private ModelRenderer SideA;
	private ModelRenderer SideB;
	private ModelRenderer SideC;
	private ModelRenderer SideD;
	private ModelRenderer SideE;
	private ModelRenderer SideF;
	private ModelRenderer SideG;
	private ModelRenderer SideH;
	private ModelRenderer Top;
	private ModelRenderer Top0;
	private ModelRenderer Top1;
	private ModelRenderer Top2;
	private ModelRenderer Top3;
	private ModelRenderer Top4;
	private ModelRenderer Top5;
	private ModelRenderer Top6;
	private ModelRenderer Top7;
	private ModelRenderer Bottom7;
	private ModelRenderer Bottom6;
	private ModelRenderer Bottom5;
	private ModelRenderer Bottom4;
	private ModelRenderer Bottom3;
	private ModelRenderer Bottom2;
	private ModelRenderer Bottom1;
	private ModelRenderer Bottom0;
	private ModelRenderer Bottom;
	private ModelRenderer Side0;
	private ModelRenderer Side1;
	private ModelRenderer Side2;
	private ModelRenderer Side3;
	private ModelRenderer Side4;
	private ModelRenderer Side5;
	private ModelRenderer Side6;
	private ModelRenderer Side7;
	private ModelRenderer Paper0;
	private ModelRenderer Paper1;
	private ModelRenderer Paper2;
	private ModelRenderer Paper3;

	public ModelOnbashira() {
		textureWidth = 64;
		textureHeight = 64;

		SideA = new ModelRenderer(this, 0, 0);
		SideA.addBox(-3.5F, -32F, 7F, 7, 56, 1);
		SideA.setRotationPoint(0F, 0F, 0F);
		SideA.setTextureSize(64, 64);
		SideA.mirror = true;
		setRotation(SideA, 0F, 0F, 0F);
		SideB = new ModelRenderer(this, 0, 0);
		SideB.addBox(-3.5F, -32F, -8F, 7, 56, 1);
		SideB.setRotationPoint(0F, 0F, 0F);
		SideB.setTextureSize(64, 64);
		SideB.mirror = true;
		setRotation(SideB, 0F, 0F, 0F);
		SideC = new ModelRenderer(this, 17, 0);
		SideC.addBox(-8F, -32F, -3.5F, 1, 56, 7);
		SideC.setRotationPoint(0F, 0F, 0F);
		SideC.setTextureSize(64, 64);
		SideC.mirror = true;
		setRotation(SideC, 0F, 0F, 0F);
		SideD = new ModelRenderer(this, 17, 0);
		SideD.addBox(7F, -32F, -3.5F, 1, 56, 7);
		SideD.setRotationPoint(0F, 0F, 0F);
		SideD.setTextureSize(64, 64);
		SideD.mirror = true;
		setRotation(SideD, 0F, 0F, 0F);
		SideE = new ModelRenderer(this, 17, 0);
		SideE.addBox(7F, -32F, -3.5F, 1, 56, 7);
		SideE.setRotationPoint(0F, 0F, 0F);
		SideE.setTextureSize(64, 64);
		SideE.mirror = true;
		setRotation(SideE, 0F, 0.7853982F, 0F);
		SideF = new ModelRenderer(this, 17, 0);
		SideF.addBox(-8F, -32F, -3.5F, 1, 56, 7);
		SideF.setRotationPoint(0F, 0F, 0F);
		SideF.setTextureSize(64, 64);
		SideF.mirror = true;
		setRotation(SideF, 0F, 0.7853982F, 0F);
		SideG = new ModelRenderer(this, 17, 0);
		SideG.addBox(-8F, -32F, -3.5F, 1, 56, 7);
		SideG.setRotationPoint(0F, 0F, 0F);
		SideG.setTextureSize(64, 64);
		SideG.mirror = true;
		setRotation(SideG, 0F, 2.356194F, 0F);
		SideH = new ModelRenderer(this, 17, 0);
		SideH.addBox(-8F, -32F, -3.5F, 1, 56, 7);
		SideH.setRotationPoint(0F, 0F, 0F);
		SideH.setTextureSize(64, 64);
		SideH.mirror = true;
		setRotation(SideH, 0F, -0.7853982F, 0F);
		Top = new ModelRenderer(this, 31, 0);
		Top.addBox(-5.5F, -32F, -5.5F, 11, 1, 11);
		Top.setRotationPoint(0F, 0F, 0F);
		Top.setTextureSize(64, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Top0 = new ModelRenderer(this, 44, 12);
		Top0.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top0.setRotationPoint(0F, 0F, 0F);
		Top0.setTextureSize(64, 64);
		Top0.mirror = true;
		setRotation(Top0, 0F, 0F, 0F);
		Top1 = new ModelRenderer(this, 44, 12);
		Top1.addBox(5F, -32F, -3.5F, 3, 1, 7);
		Top1.setRotationPoint(0F, 0F, 0F);
		Top1.setTextureSize(64, 64);
		Top1.mirror = true;
		setRotation(Top1, 0F, 0F, 0F);
		Top2 = new ModelRenderer(this, 44, 12);
		Top2.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top2.setRotationPoint(0F, 0F, 0F);
		Top2.setTextureSize(64, 64);
		Top2.mirror = true;
		setRotation(Top2, 0F, 1.570796F, 0F);
		Top3 = new ModelRenderer(this, 44, 12);
		Top3.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top3.setRotationPoint(0F, 0F, 0F);
		Top3.setTextureSize(64, 64);
		Top3.mirror = true;
		setRotation(Top3, 0F, -1.570796F, 0F);
		Top4 = new ModelRenderer(this, 44, 12);
		Top4.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top4.setRotationPoint(0F, 0F, 0F);
		Top4.setTextureSize(64, 64);
		Top4.mirror = true;
		setRotation(Top4, 0F, -0.7853982F, 0F);
		Top5 = new ModelRenderer(this, 44, 12);
		Top5.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top5.setRotationPoint(0F, 0F, 0F);
		Top5.setTextureSize(64, 64);
		Top5.mirror = true;
		setRotation(Top5, 0F, -2.356194F, 0F);
		Top6 = new ModelRenderer(this, 44, 12);
		Top6.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top6.setRotationPoint(0F, 0F, 0F);
		Top6.setTextureSize(64, 64);
		Top6.mirror = true;
		setRotation(Top6, 0F, 0.7853982F, 0F);
		Top7 = new ModelRenderer(this, 44, 12);
		Top7.addBox(-8F, -32F, -3.5F, 3, 1, 7);
		Top7.setRotationPoint(0F, 0F, 0F);
		Top7.setTextureSize(64, 64);
		Top7.mirror = true;
		setRotation(Top7, 0F, 2.356194F, 0F);
		Bottom7 = new ModelRenderer(this, 44, 12);
		Bottom7.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom7.setRotationPoint(0F, 0F, 0F);
		Bottom7.setTextureSize(64, 64);
		Bottom7.mirror = true;
		setRotation(Bottom7, 0F, 2.356194F, 0F);
		Bottom6 = new ModelRenderer(this, 44, 12);
		Bottom6.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom6.setRotationPoint(0F, 0F, 0F);
		Bottom6.setTextureSize(64, 64);
		Bottom6.mirror = true;
		setRotation(Bottom6, 0F, 0.7853982F, 0F);
		Bottom5 = new ModelRenderer(this, 44, 12);
		Bottom5.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom5.setRotationPoint(0F, 0F, 0F);
		Bottom5.setTextureSize(64, 64);
		Bottom5.mirror = true;
		setRotation(Bottom5, 0F, -2.356194F, 0F);
		Bottom4 = new ModelRenderer(this, 44, 12);
		Bottom4.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom4.setRotationPoint(0F, 0F, 0F);
		Bottom4.setTextureSize(64, 64);
		Bottom4.mirror = true;
		setRotation(Bottom4, 0F, -0.7853982F, 0F);
		Bottom3 = new ModelRenderer(this, 44, 12);
		Bottom3.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom3.setRotationPoint(0F, 0F, 0F);
		Bottom3.setTextureSize(64, 64);
		Bottom3.mirror = true;
		setRotation(Bottom3, 0F, -1.570796F, 0F);
		Bottom2 = new ModelRenderer(this, 44, 12);
		Bottom2.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom2.setRotationPoint(0F, 0F, 0F);
		Bottom2.setTextureSize(64, 64);
		Bottom2.mirror = true;
		setRotation(Bottom2, 0F, 1.570796F, 0F);
		Bottom1 = new ModelRenderer(this, 44, 12);
		Bottom1.addBox(5F, 24F, -3.5F, 3, 0, 7);
		Bottom1.setRotationPoint(0F, 0F, 0F);
		Bottom1.setTextureSize(64, 64);
		Bottom1.mirror = true;
		setRotation(Bottom1, 0F, 0F, 0F);
		Bottom0 = new ModelRenderer(this, 44, 12);
		Bottom0.addBox(-8F, 24F, -3.5F, 3, 0, 7);
		Bottom0.setRotationPoint(0F, 0F, 0F);
		Bottom0.setTextureSize(64, 64);
		Bottom0.mirror = true;
		setRotation(Bottom0, 0F, 0F, 0F);
		Bottom = new ModelRenderer(this, 31, 0);
		Bottom.addBox(-5.5F, 23F, -5.5F, 11, 1, 11);
		Bottom.setRotationPoint(0F, 0F, 0F);
		Bottom.setTextureSize(64, 64);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Side0 = new ModelRenderer(this, 48, 20);
		Side0.addBox(-3.5F, -25F, -9F, 7, 2, 1);
		Side0.setRotationPoint(0F, 0F, 0F);
		Side0.setTextureSize(64, 64);
		Side0.mirror = true;
		setRotation(Side0, 0F, -0.7853982F, 0F);
		Side1 = new ModelRenderer(this, 48, 20);
		Side1.addBox(-3.5F, -25F, -9F, 7, 2, 1);
		Side1.setRotationPoint(0F, 0F, 0F);
		Side1.setTextureSize(64, 64);
		Side1.mirror = true;
		setRotation(Side1, 0F, 0F, 0F);
		Side2 = new ModelRenderer(this, 48, 20);
		Side2.addBox(-3.5F, -25F, -9F, 7, 2, 1);
		Side2.setRotationPoint(0F, 0F, 0F);
		Side2.setTextureSize(64, 64);
		Side2.mirror = true;
		setRotation(Side2, 0F, 0.7853982F, 0F);
		Side3 = new ModelRenderer(this, 33, 20);
		Side3.addBox(-9F, -25F, -3.5F, 1, 2, 7);
		Side3.setRotationPoint(0F, 0F, 0F);
		Side3.setTextureSize(64, 64);
		Side3.mirror = true;
		setRotation(Side3, 0F, 0F, 0F);
		Side4 = new ModelRenderer(this, 48, 20);
		Side4.addBox(-3.5F, -25F, 8F, 7, 2, 1);
		Side4.setRotationPoint(0F, 0F, 0F);
		Side4.setTextureSize(64, 64);
		Side4.mirror = true;
		setRotation(Side4, 0F, -0.7853982F, 0F);
		Side5 = new ModelRenderer(this, 48, 20);
		Side5.addBox(-3.5F, -25F, 8F, 7, 2, 1);
		Side5.setRotationPoint(0F, 0F, 0F);
		Side5.setTextureSize(64, 64);
		Side5.mirror = true;
		setRotation(Side5, 0F, 0F, 0F);
		Side6 = new ModelRenderer(this, 48, 20);
		Side6.addBox(-3.5F, -25F, 8F, 7, 2, 1);
		Side6.setRotationPoint(0F, 0F, 0F);
		Side6.setTextureSize(64, 64);
		Side6.mirror = true;
		setRotation(Side6, 0F, 0.7853982F, 0F);
		Side7 = new ModelRenderer(this, 33, 20);
		Side7.addBox(8F, -25F, -3.5F, 1, 2, 7);
		Side7.setRotationPoint(0F, 0F, 0F);
		Side7.setTextureSize(64, 64);
		Side7.mirror = true;
		setRotation(Side7, 0F, 0F, 0F);
		Paper0 = new ModelRenderer(this, 33, 30);
		Paper0.addBox(-9F, -25F, -1F, 0, 7, 2);
		Paper0.setRotationPoint(0F, 0F, 0F);
		Paper0.setTextureSize(64, 64);
		Paper0.mirror = true;
		setRotation(Paper0, 0F, 0F, 0F);
		Paper1 = new ModelRenderer(this, 38, 30);
		Paper1.addBox(-1F, -25F, 9F, 2, 7, 0);
		Paper1.setRotationPoint(0F, 0F, 0F);
		Paper1.setTextureSize(64, 64);
		Paper1.mirror = true;
		setRotation(Paper1, 0F, 0F, 0F);
		Paper2 = new ModelRenderer(this, 33, 30);
		Paper2.addBox(9F, -25F, -1F, 0, 7, 2);
		Paper2.setRotationPoint(0F, 0F, 0F);
		Paper2.setTextureSize(64, 64);
		Paper2.mirror = true;
		setRotation(Paper2, 0F, 0F, 0F);
		Paper3 = new ModelRenderer(this, 38, 30);
		Paper3.addBox(-1F, -25F, -9F, 2, 7, 0);
		Paper3.setRotationPoint(0F, 0F, 0F);
		Paper3.setTextureSize(64, 64);
		Paper3.mirror = true;
		setRotation(Paper3, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		renderModel(f5);
	}

	public void renderModel(float f5) {
		SideA.render(f5);
		SideB.render(f5);
		SideC.render(f5);
		SideD.render(f5);
		SideE.render(f5);
		SideF.render(f5);
		SideG.render(f5);
		SideH.render(f5);
		Top.render(f5);
		Top0.render(f5);
		Top1.render(f5);
		Top2.render(f5);
		Top3.render(f5);
		Top4.render(f5);
		Top5.render(f5);
		Top6.render(f5);
		Top7.render(f5);
		Bottom7.render(f5);
		Bottom6.render(f5);
		Bottom5.render(f5);
		Bottom4.render(f5);
		Bottom3.render(f5);
		Bottom2.render(f5);
		Bottom1.render(f5);
		Bottom0.render(f5);
		Bottom.render(f5);
		Side0.render(f5);
		Side1.render(f5);
		Side2.render(f5);
		Side3.render(f5);
		Side4.render(f5);
		Side5.render(f5);
		Side6.render(f5);
		Side7.render(f5);
		Paper0.render(f5);
		Paper1.render(f5);
		Paper2.render(f5);
		Paper3.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
