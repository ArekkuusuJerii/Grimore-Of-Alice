/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.item.masks;

import java.util.List;

import arekkuusu.grimoireOfAlice.item.GOAItem;
import arekkuusu.grimoireOfAlice.lib.LibMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

public class ItemFoxMask extends ItemArmor {

	public ItemFoxMask(ArmorMaterial material, int p_i45325_2_, int p_i45325_3_) {
		super(material, p_i45325_2_, p_i45325_3_);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {

		list.add(EnumChatFormatting.DARK_AQUA + "Tsukumogami of Determination");
		list.add(EnumChatFormatting.GOLD + " -Speed");

		if(player.experienceLevel >= 40) {
			list.add(EnumChatFormatting.GOLD + " -Resistance");

		}
		list.add(EnumChatFormatting.DARK_PURPLE + " *Vulnerable to Magic");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {

		if(player.experienceLevel <= 40) {
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 0));
		}
		else {
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 0, 4));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 0, 0));

		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {

		if(stack.getItem() == GOAItem.itemFoxMask) {
			return LibMod.MODID + ":textures/models/armor/FoxMask.png";

		}
		else {
			return null;
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack, int pass) {
		return true;
	}
}
