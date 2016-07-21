/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item;

import java.util.List;

import arekkuusu.grimoireOfAlice.entity.EntityFireBalloon;
import arekkuusu.grimoireOfAlice.tmp.CleanupDone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@CleanupDone
public class ItemEnchantedBook extends ItemGOABase {

	ItemEnchantedBook() {
		super(EnumRarity.epic, true);
		setMaxStackSize(1);
		setMaxDamage(50);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if(stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String s = nbttagcompound.getString("title");

			if(!StringUtils.isNullOrEmpty(s)) { return s; }
		}

		return super.getItemStackDisplayName(stack);
	}

	@SuppressWarnings("unchecked")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
		list.add(EnumChatFormatting.LIGHT_PURPLE + "Are you worthy of this magic?");
		if(stack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = stack.getTagCompound();
			String s = nbttagcompound.getString("author");

			if(!StringUtils.isNullOrEmpty(s)) {
				list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocalFormatted("book.byAuthor", s));
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(player.capabilities.isCreativeMode || player.inventory.hasItem(Items.fire_charge)) {
				world.playSoundEffect(player.posX + 0.5D, player.posY + 0.5D, player.posZ + 0.5D, "mob.ghast.scream", 1.0F,
						itemRand.nextFloat() * 0.4F + 0.8F);
				Vec3 look = player.getLookVec();
				Vec3 position = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
				Vec3 fireBallPos = Vec3.createVectorHelper(position.xCoord + look.xCoord * 5, position.yCoord + look.yCoord * 5, position.zCoord + look.zCoord * 5);
				EntityFireBalloon fireball2 = new EntityFireBalloon(world, player, fireBallPos, look);

				world.spawnEntityInWorld(fireball2);
				player.inventory.consumeInventoryItem(Items.fire_charge);
				stack.damageItem(1, player);
			}
			else if(player.experienceLevel > 30 && player.experienceLevel < 60) {
				if(player.capabilities.isCreativeMode || player.inventory.hasItem(Items.coal)) {
					player.inventory.consumeInventoryItem(Items.coal);
					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 25, 0));
					player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 25, 0));
					player.setItemInUse(stack, getMaxItemUseDuration(stack));
				}
			}
			else if(player.experienceLevel > 60 && player.experienceLevel < 80) {
				if(player.experienceLevel < 70) {
					player.setFire(120);
				}
				else {
					player.addExperienceLevel(-1);
					player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, 4));
				}
				player.setItemInUse(stack, getMaxItemUseDuration(stack));
			}
		}
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_,
			float p_77648_10_) {
		if(player.experienceLevel <= 30) {
			if(side == 0) {
				--y;
			}

			if(side == 1) {
				++y;
			}

			if(side == 2) {
				--z;
			}

			if(side == 3) {
				++z;
			}

			if(side == 4) {
				--x;
			}

			if(side == 5) {
				++x;
			}

			if(!player.canPlayerEdit(x, y, z, side, stack)) {
				return false;
			}
			else {
				boolean success = false;
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j <= 1; j++) {
						if(world.isAirBlock(x + i, y, z + j)) {
							world.setBlock(x + i, y, z + j, Blocks.fire);
							success = true;
						}
					}
				}

				if(success) {
					world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "mob.blaze.death", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
				}
				return success;
			}
		}
		else if(player.experienceLevel > 30 && player.experienceLevel < 40) {
			world.spawnEntityInWorld(new EntityLightningBolt(world, x + 0.5, y, z + 0.5));
			stack.damageItem(1, player);
			return true;
		}
		else {
			return false;
		}
	}
}