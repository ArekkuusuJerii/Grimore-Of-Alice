/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 * <p>
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.common.core.helper.MiscHelper;
import arekkuusu.grimoireofalice.common.lib.LibName;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class ItemGapFoldingUmbrella extends ItemBase {

	public ItemGapFoldingUmbrella() {
		super(LibName.FOLDING_UMBRELLA);
		setMaxStackSize(1);
		setMaxDamage(10);
		setNoRepair();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;

			Optional<BlockPos> posLookedAt = getBlockPosLookedAt(player);
			BlockPos pos;
			if(posLookedAt.isPresent() && !player.isSneaking()) {
				pos = posLookedAt.get();
			} else {
				Vec3d look = player.getLookVec();
				double range = 40.0D;
				double dx = player.posX + look.x * range;
				double dy = player.posY + 1 + look.y * range;
				double dz = player.posZ + look.z * range;
				pos = new BlockPos(dx, dy, dz);
			}

			if(isSafe(world, pos)) {
				if(!world.isRemote && player instanceof EntityPlayerMP) {
					player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				}
				stack.damageItem(1, player);
			}
			world.playSound(player, pos.getX(), pos.getY(), pos.getZ(), GrimoireSoundEvents.WARP,
					SoundCategory.PLAYERS, 0.2F, itemRand.nextFloat() * 0.4F + 0.8F);
			player.getCooldownTracker().setCooldown(this, 30);
		}
	}

	private static Optional<BlockPos> getBlockPosLookedAt(EntityPlayer player) {
		RayTraceResult rayTraceResult = MiscHelper.rayTraceLook(player, 40.0D);
		if(rayTraceResult != null) {
			return Optional.of(rayTraceResult.getBlockPos().offset(rayTraceResult.sideHit));
		} else {
			return Optional.empty();
		}
	}

	private static boolean isSafe(World world, BlockPos pos) {
		if(pos.getY() < 0) {
			return false;
		}
		IBlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || !state.isSideSolid(world, pos, EnumFacing.UP);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 2000;
	}
}
