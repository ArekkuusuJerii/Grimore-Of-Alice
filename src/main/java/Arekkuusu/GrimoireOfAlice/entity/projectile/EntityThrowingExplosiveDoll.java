/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.entity.projectile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrowingExplosiveDoll extends EntityThrowable{
	
	public EntityThrowingExplosiveDoll(World world){
	
		super(world);

	}
	
	public EntityThrowingExplosiveDoll(World world, EntityLivingBase entity) {
		
		super(world, entity);
		
		}

	private int explosionRadius= 2;
	
	public EntityThrowingExplosiveDoll(World world, double x, double y, double z) {
			
		super(world, x, y, z);
		
		}

	protected float getGravityVelocity(){
			
			return 0.07F;
			
		}
		@SideOnly(Side.CLIENT)
		@Override
	protected void onImpact(MovingObjectPosition mop) {
		
	for (int l = 0; l < 4; ++l) {
			
		this.worldObj.spawnParticle("splash", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		
		}

	if (!worldObj.isRemote) {
		
	    boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
		
		this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, explosionRadius, flag);
		setDead();
		
		}
	}
}