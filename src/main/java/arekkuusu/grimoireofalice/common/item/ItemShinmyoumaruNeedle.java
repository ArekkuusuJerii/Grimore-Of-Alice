/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import arekkuusu.grimoireofalice.api.sound.GrimoireSoundEvents;
import arekkuusu.grimoireofalice.client.fx.ParticleFX;
import arekkuusu.grimoireofalice.common.GrimoireOfAlice;
import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@Optional.Interface(iface = "net.katsstuff.danmakucore.item.IOwnedBy", modid = "danmakucore")
public class ItemShinmyoumaruNeedle extends ItemModSword implements IOwnedBy {

	public ItemShinmyoumaruNeedle(ToolMaterial material) {
		super(material, LibItemName.SHINMYOUMARU_NEEDLE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.needle_header.name"));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			if(isSelected && player.getCooldownTracker().hasCooldown(this) && !wasShifting(stack) && player.ticksExisted % 2 == 0) {
				EnumHand hand = player.getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				player.swingArm(hand);

				Vec3d vec = player.getLookVec();
				float distance = 4F + itemRand.nextInt(3);
				double dx = player.posX + vec.x * distance;
				double dy = player.posY + 2.5 + vec.y * distance;
				double dz = player.posZ + vec.z * distance;
				GrimoireOfAlice.proxy.sparkleFX(ParticleFX.NEEDLE_SWING, null, dx, dy, dz, itemRand.nextFloat(), 0F, 0F);
				world.playSound(player, player.getPosition(), GrimoireSoundEvents.NEEDLE_SWEEP, SoundCategory.PLAYERS, 1F, 1F);

				if(!world.isRemote) {
					List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
							player.getEntityBoundingBox().offset(vec.x * 2, 0, vec.z * 2).grow(4D), entity -> !player.equals(entity));
					list.forEach(entity -> entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 6));
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entityLiving, int timeLeft) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityLiving);
			int timeUsed = getMaxItemUseDuration(stack) - timeLeft;
			if(timeUsed > 50) {
				timeUsed = 50;
			}
			setShifting(stack, player.isSneaking());
			player.getCooldownTracker().setCooldown(this, timeUsed);
		}
		stack.damageItem(1, entityLiving);
	}

	private static void setShifting(ItemStack itemStack, boolean does) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if(nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		nbt.setBoolean("NeedleMode", does);
	}

	private static boolean wasShifting(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt != null && nbt.getBoolean("NeedleMode");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 500;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Optional.Method(modid = "danmakucore")
	@Override
	public net.katsstuff.danmakucore.entity.living.TouhouCharacter character(ItemStack stack) {
		return net.katsstuff.danmakucore.entity.living.TouhouCharacter.SHINMYOUMARU_SUKUNA;
	}
}
