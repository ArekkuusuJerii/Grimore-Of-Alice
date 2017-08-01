/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.client.fx;

import arekkuusu.grimoireofalice.client.ResourceLocations;
import arekkuusu.grimoireofalice.common.core.helper.MathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ShinmyoumaruSpark extends Particle {

	public ShinmyoumaruSpark(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);

		if(MathUtil.fuzzyEqual(xSpeed, 0D) && MathUtil.fuzzyEqual(zSpeed, 0D)) {
			motionX *= 0.10000000149011612D;
			motionZ *= 0.10000000149011612D;
		}

		particleScale = 0.4F * rand.nextFloat();
		particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));

		TextureAtlasSprite atlasSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ResourceLocations.SHINMYOUMARU_SPARKLE.toString());

		setParticleTexture(atlasSprite);
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public boolean shouldDisableDepth() {
		return true;
	}

	@Override
	public int getBrightnessForRender(float p_189214_1_) {
		return 244;
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		if(particleAge++ >= particleMaxAge) {
			setExpired();
		}

		motionY = 0;

		move(motionX, motionY, motionZ);

		if(onGround) {
			motionX *= 0.699999988079071D;
			motionZ *= 0.699999988079071D;
		}
	}
}
