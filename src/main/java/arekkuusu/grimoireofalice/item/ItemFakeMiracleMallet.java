package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.lib.LibItemName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFakeMiracleMallet extends ItemMod {

	ItemFakeMiracleMallet() {
		super(LibItemName.FAKEMIRACLEMALLET);
		setMaxStackSize(1);
		setNoRepair();
		addPropertyOverride(new ResourceLocation("swinging"), (stack, world, entity) ->
				entity != null && entity.isSwingInProgress ? 1F : 0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.UNCOMMON;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.DARK_PURPLE + "Swipe the space in front of the player, dealing damage");
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		if(entityLiving instanceof  EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityLiving;
			Vec3d vec = player.getLookVec();
			List<Entity> list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, entityLiving.getEntityBoundingBox().offset(vec.xCoord * 2, 0, vec.zCoord * 2).expandXyz(3));
			if (!list.isEmpty()) {
				list.stream().filter(entity -> entity instanceof EntityLivingBase).forEach(entity -> {
					entity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), 10F);
				});
			}
		}
		entityLiving.worldObj.playSound(null, new BlockPos(entityLiving.posX + 0.5D, entityLiving.posY + 0.5D, entityLiving.posZ + 0.5D),
				SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
		return super.onEntitySwing(entityLiving, stack);
	}
}
