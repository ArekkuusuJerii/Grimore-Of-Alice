package arekkuusu.grimoireofalice.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityNeedle extends EntityArrow {

	public EntityNeedle(World world) {
		super(world);
	}

	public EntityNeedle(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	public EntityNeedle(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		worldObj.playSound(null, new BlockPos(posX + 0.5D, posY + 0.5D, posZ + 0.5D),
				SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 0.4F + 0.8F);
		if(ticksInAir >= 15 && !worldObj.isRemote){
			setDead();
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return null; //FIXME: This could cause NPE
	}
}
