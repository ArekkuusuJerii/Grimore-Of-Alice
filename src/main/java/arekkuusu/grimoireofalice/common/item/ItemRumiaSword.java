/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item;

import java.util.List;

import arekkuusu.grimoireofalice.common.lib.LibItemName;
import net.katsstuff.danmakucore.entity.living.TouhouCharacter;
import net.katsstuff.danmakucore.item.IOwnedBy;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRumiaSword extends ItemBaseSword implements IOwnedBy {

	public ItemRumiaSword(ToolMaterial material) {
		super(material, LibItemName.RUMIA_SWORD);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.rumia_sword_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.rumia_sword_description.name"));
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLiving;
			Vec3d vec = player.getLookVec();
			List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class,
					entityLiving.getEntityBoundingBox().offset(vec.x * 2, 0, vec.z * 2).grow(3D), entity -> !player.equals(entity));
			list.forEach(entity -> entity.setFire(50));
		}
		entityLiving.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, 1F, itemRand.nextFloat() * 0.4F + 0.8F);
		return super.onEntitySwing(entityLiving, stack);
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public TouhouCharacter character(ItemStack stack) {
		return TouhouCharacter.RUMIA;
	}
}
