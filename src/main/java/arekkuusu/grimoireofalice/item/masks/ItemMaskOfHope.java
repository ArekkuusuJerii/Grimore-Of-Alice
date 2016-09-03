/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.masks;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaskOfHope extends ItemModMask {

	public ItemMaskOfHope(ArmorMaterial material, int dmg) {
		super(material, dmg, EntityEquipmentSlot.HEAD, LibItemName.MASKOFHOPE);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_AQUA + "Tsukumogami of Jizo Statue");
		list.add(TextFormatting.DARK_GRAY + "Return to Kokoro if found... or not...");

		if(player.experienceLevel <= 120) {
			list.add(TextFormatting.LIGHT_PURPLE + " -Wither");
		}
		else {
			list.add(TextFormatting.LIGHT_PURPLE + " -Poison.");
		}

		list.add(TextFormatting.DARK_PURPLE + " *Vulnerable to Wither");
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
		if(player.experienceLevel <= 120) {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 20
			if(potion1 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 666, 4));
			}
		}
		else {
			Potion potion1 = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 19
			if(potion1 != null) {
				player.addPotionEffect(new PotionEffect(potion1, 666, 4));
			}
		}
	}
}