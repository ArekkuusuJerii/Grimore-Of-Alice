package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import arekkuusu.grimoireofalice.lib.LibMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemKappaHat extends ItemModArmor {

	ItemKappaHat(ArmorMaterial materialIn, int dmg) {
		super(materialIn, dmg, LibItemName.KAPPAHAT, EntityEquipmentSlot.HEAD);
		setMaxDamage(200);
		setNoRepair();
		setMaxStackSize(1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "A kappa hat");
		list.add(TextFormatting.DARK_AQUA + "\"Do not drop the water\"");
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(worldIn.isRaining()){
			if(stack.isItemDamaged()){
				stack.setItemDamage(stack.getItemDamage() - 1);
			}
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		if (player.isInWater()) {
			player.motionX *= 1.2;
			player.motionY *= 1.2;
			player.motionZ *= 1.2;
		} else if(!player.isWet()){
			player.motionX *= 0.4;
			player.motionZ *= 0.4;
		}
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return LibMod.MODID + ":textures/models/armor/firerobe.png";
	}
}