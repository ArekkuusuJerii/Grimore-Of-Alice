/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item;

import java.util.List;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMikoStick extends ItemModSword {

	ItemMikoStick(ToolMaterial material) {
		super(material, LibItemName.MIKOSTICK);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Property of Toyosatomimi no Miko");
		list.add(TextFormatting.ITALIC + "Better than a bucket of water");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.experienceLevel < 30) {
			playerIn.setFire(120);
		}
		else {
			playerIn.addExperienceLevel(-1);
			Potion potion = Potion.REGISTRY.getObject(new ResourceLocation("")); //TODO: id 10
			if(potion != null) {
				playerIn.addPotionEffect(new PotionEffect(potion, 60, 4));
			}
		}
		//.setItemInUse(itemStackIn, getMaxItemUseDuration(itemStackIn));
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase user) {
		target.extinguish();
		return true;
	}
}
