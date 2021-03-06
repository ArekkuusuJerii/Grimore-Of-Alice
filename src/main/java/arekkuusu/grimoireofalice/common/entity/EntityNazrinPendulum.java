/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.entity;

import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;

public class EntityNazrinPendulum extends Entity {

	private ItemStack stack;
	private String ore = "";
	private EntityPlayer user;
	private boolean follow;

	public EntityNazrinPendulum(World world) {
		super(world);
	}

	public EntityNazrinPendulum(World world, EntityPlayer player, ItemStack stack, String ore, boolean follow) {
		super(world);
		this.user = player;
		this.stack = stack.copy();
		this.follow = follow;
		this.ore = ore;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote) {
			if(user == null || isEntityInsideOpaqueBlock() || ticksExisted > 10 && user.isSneaking() || user.hurtTime > 0) {
				stopEntity();
				return;
			}
			followPlayer();
			surveyArea();
		}
	}

	public void followPlayer() {
		if(follow) {
			Vec3d look = user.getLookVec();
			float distance = 1.25F;
			double dx = user.posX + look.x * distance;
			double dy = user.posY + user.getEyeHeight() + look.y * distance;
			double dz = user.posZ + look.z * distance;
			setPosition(dx, dy, dz);
		}
	}

	private void surveyArea() {
		int count = 0;
		BlockPos pos = new BlockPos(posX, posY, posZ);
		for(int i = 1; i < 20; i++) {
			Block block = world.getBlockState(pos.down(i)).getBlock();
			ItemStack blockStack = new ItemStack(block);
			if(blockStack.isEmpty()) {
				continue;
			}
			boolean searchForOre = !ore.isEmpty();
			boolean isValuable = Arrays.stream(OreDictionary.getOreIDs(blockStack))
					.mapToObj(OreDictionary::getOreName)
					.anyMatch(s -> searchForOre ? s.equals(ore) : s.startsWith("ore"))
					|| block == Blocks.CHEST;
			if(isValuable) {
				count++;
			}
		}
		for(int i = 0; i < count; i++) {
			if(rand.nextInt(8) == 2 && world instanceof WorldServer) {
				((WorldServer) world).spawnParticle(EnumParticleTypes.CRIT_MAGIC, posX, posY, posZ, 1, 0D, rand.nextDouble(), 0D, 0.1D);
			}
		}
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected void entityInit() {
	}

	private void stopEntity() {
		if(!world.isRemote) {
			if(user != null) {
				if(user.capabilities.isCreativeMode) {
					setDead();
					return;
				}
				ItemHandlerHelper.giveItemToPlayer(user, stack);
			} else {
				dropItem(ModItems.NAZRIN_PENDULUM, 1);
			}
			setDead();
		}
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		AxisAlignedBB alignedBB = super.getEntityBoundingBox();
		return new AxisAlignedBB(alignedBB.minX + 0.1, alignedBB.minY - 0.2, alignedBB.minZ + 0.1, alignedBB.minX + 0.5, alignedBB.minY + 0.2, alignedBB.minZ + 0.5);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}
