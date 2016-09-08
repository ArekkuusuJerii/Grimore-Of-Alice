package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityAnimalShot extends EntityThrowable {
	
	private int timeLive = 50;
	private int ticksInAir;

	public EntityAnimalShot(World worldIn) {
        super(worldIn);
    }

    public EntityAnimalShot(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityAnimalShot(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		doEffects();
		++this.ticksInAir;
		if(this.ticksInAir >= timeLive){
			if (worldObj.isRemote) {
	            this.setDead();
	        }
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		for (int j = 0; j < 8; ++j) {
            this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
		if (worldObj.isRemote) {
            this.setDead();
        }
	}
	
	@Override
	public float getGravityVelocity() {
        return 0.02F;
    }
	
	//Rotate Figure or Make Animation (Like in Ten Desires)
	private void doEffects(){
	}

}