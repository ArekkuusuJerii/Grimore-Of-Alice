package arekkuusu.grimoireofalice.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityUnzanFist extends EntityThrowable {

	private boolean isReturning = false;

	public EntityUnzanFist(World world) {
		super(world);
	}

	public EntityUnzanFist(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityUnzanFist(World world, EntityLivingBase thrower) {
		super(world, thrower);
		setRotation(thrower.rotationYaw, thrower.rotationPitch);
	}

	@Override
	protected void entityInit() {

	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase thrower = getThrower();
		if(thrower == null) {
			if (!worldObj.isRemote) {
				setDead();
			}
		} else if (isReturning) {
			double dx, dy, dz;
			dx = posX - thrower.posX;
			dy = posY - thrower.posY - thrower.getEyeHeight();
			dz = posZ - thrower.posZ;

			double d = Math.sqrt(dx * dx + dy * dy + dz * dz);
			dx /= d;
			dy /= d;
			dz /= d;

			motionX -= 0.5D * dx;
			motionY -= 0.5D * dy;
			motionZ -= 0.5D * dz;
		}

		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float res = 0.99F;
		float grav = getGravityVelocity();
		if (isInWater()) {
			for (int i = 0; i < 4; i++) {
				float f6 = 0.25F;
				worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * f6, posY - motionY * f6, posZ - motionZ * f6, motionX, motionY, motionZ);
			}
			res *= 0.80808080F;
		}
		motionX *= res;
		motionY *= res;
		motionZ *= res;
		motionY -= grav;
		setPosition(posX, posY, posZ);
		if(ticksExisted > 50){
			if(!worldObj.isRemote){
				setDead();
			}
		}
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX + 0.5, posY + 0.5, posZ + 0.5, motionX, motionY, motionZ);
		worldObj.playSound(null, new BlockPos(posX + 0.5D, posY + 0.5D, posZ + 0.5D),
				SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1.0F, rand.nextFloat() * 1.0F + 0.8F);
		if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			onImpactBlock(result);
		}
		else if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			onImpactEntity(result);
		}
	}

	private void onImpactBlock(RayTraceResult result) {
		IBlockState base = worldObj.getBlockState(result.getBlockPos());
		boolean canHitBlock = base.getBlock() != Blocks.TALLGRASS && base.getBlock() != Blocks.DOUBLE_PLANT;
		if(canHitBlock) bounceBack();
	}

	private void onImpactEntity(RayTraceResult result) {
		bounceBack();
		if(result.entityHit != null && result.entityHit != this) {
			if(result.entityHit == this.getThrower()){
				if (!worldObj.isRemote) {
					setDead();
				}
				return;
			}
			applyHitEffects(result.entityHit);
		}
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		if(entityplayer != this.getThrower()){
			bounceBack();
			applyHitEffects(entityplayer);
		} else {
			if(!worldObj.isRemote) setDead();
		}
	}

	private void applyHitEffects(Entity entity) {
		entity.attackEntityFrom(DamageSource.generic,5);
		double speed = 0.5;
		entity.motionX = -Math.sin(Math.toRadians(this.rotationYaw)) * speed;
		entity.motionZ = Math.cos(Math.toRadians(this.rotationYaw)) * speed;
	}

	private void bounceBack() {
		this.isReturning = true;
		motionX *= -0.1D;
		motionY *= -0.1D;
		motionZ *= -0.1D;
		rotationYaw += 180F;
		prevRotationYaw += 180F;
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		int xyz = ticksExisted < 1.5 ? -1 : 3 ;
		return super.getEntityBoundingBox().expandXyz(xyz);
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeAttackedWithItem() {
		return true;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
}