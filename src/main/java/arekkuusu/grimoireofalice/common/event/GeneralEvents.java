package arekkuusu.grimoireofalice.common.event;

import arekkuusu.grimoireofalice.api.items.GoheiMode;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ItemMochiHammer;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.katsstuff.teamnightclipse.danmakucore.helper.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class GeneralEvents {

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if(player.getEntityData().hasKey("MalletResized")) {
			float modifier = player.getEntityData().getFloat("MalletResized");
			boolean isHalf = MathUtil.fuzzyEqual(modifier, 0.5F);

			AxisAlignedBB axisAlignedBB = player.getEntityBoundingBox(); //Get Bounding Box
			double minX = axisAlignedBB.minX;
			double minY = axisAlignedBB.minY;
			double minZ = axisAlignedBB.minZ;
			axisAlignedBB = new AxisAlignedBB(minX, minY, minZ
					, isHalf ? minX + 0.5 : minX + modifier * 0.8
					, isHalf ? minY + 0.9 : minY + (modifier - 0.1) * 2
					, isHalf ? minZ + 0.5 : minZ + modifier * 0.8); //Expand bounding Box
			player.setEntityBoundingBox(axisAlignedBB); //Set Bounding Box
		}
	}

	@SubscribeEvent
	public void livingDeathEvent(LivingDeathEvent event) {
		Entity attacker = event.getSource().getTrueSource();
		if(attacker instanceof EntityPlayer && !attacker.world.isRemote) {
			EntityPlayer player = (EntityPlayer) attacker;
			ItemStack heldItem = player.getHeldItemMainhand();
			if(!heldItem.isEmpty() || heldItem.getItem() != ModItems.MOCHI_HAMMER) {
				heldItem = player.getHeldItemOffhand();
			}
			if(!heldItem.isEmpty() && heldItem.getItem() == ModItems.MOCHI_HAMMER) {
				ItemMochiHammer.LUNATIC.set(ItemMochiHammer.LUNATIC.get(heldItem) + 1, heldItem);
			}
		}
	}

	@SubscribeEvent
	public void livingAttacked(LivingAttackEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(!stack.isEmpty() && stack.getItem() == ModItems.KANAKO_SHIMENAWA) {
				if((event.getSource().isProjectile() && !event.getSource().isMagicDamage()) || event.getSource().isExplosion()) {
					event.setCanceled(true);
				}
				return;
			}
			if(isUsingItem(player, ModItems.NIMBLE_FABRIC)) {
				event.setCanceled(true);
				return;
			}
			if(event.getSource() == DamageSource.FALL && isGoheiMode(player, GoheiMode.PASSIVE)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void livingHurtEvent(LivingHurtEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(isUsingItem(player, ModItems.NIMBLE_FABRIC) || event.getSource() == DamageSource.FALL && isGoheiMode(player, GoheiMode.PASSIVE)
					|| player.getCooldownTracker().hasCooldown(ModItems.SEND_OFF_LANTERN)) {
				event.setCanceled(true);
				return;
			}

			if(player.inventory.hasItemStack(new ItemStack(ModItems.SUBSTITUTE_JIZO))) {

				@SuppressWarnings("ConstantConditions") //Liar
				IItemHandler capability = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

				for(int i = 0; i < capability.getSlots(); i++) {
					ItemStack stack = capability.getStackInSlot(i);
					if(!stack.isEmpty() && stack.getItem() == ModItems.SUBSTITUTE_JIZO) {
						capability.extractItem(i, 1, false);
						player.playSound(SoundEvents.BLOCK_GRASS_BREAK, 0.5F, player.world.rand.nextFloat() * 0.1F + 0.9F);
						event.setCanceled(true);
						return;
					}
				}
			}

			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(!stack.isEmpty() && stack.getItem() == ModItems.KANAKO_SHIMENAWA && (
					(event.getSource().isProjectile() && !event.getSource().isMagicDamage()) || event.getSource().isExplosion())) {
				event.setCanceled(true);
			}
		}
		else {
			int rarity = ConfigHandler.grimoireOfAlice.features.dragonScaleRarity;

			if(event.getEntityLiving() instanceof EntityDragon && event.getSource().isProjectile()
					&& rarity > 0 && event.getEntityLiving().world.rand.nextInt(rarity) == 0) {
				event.getEntityLiving().dropItem(ModItems.DRAGON_SCALE, 1);
			}
		}
	}

	@SubscribeEvent
	public void onLivingDropsEvent(LivingDropsEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(entity instanceof EntityDragon && ConfigHandler.grimoireOfAlice.features.swordOfKusanagi) {
			event.getDrops().add(createDrop(ModItems.SWORD_OF_KUSANAGI, entity));
		}
		else if(entity instanceof EntityChicken && event.getEntity().getEntityWorld().rand.nextInt(200) == 1) {
			event.getDrops().add(createDrop(ModItems.SWALLOW_EGG, entity));
		}
	}

	private EntityItem createDrop(Item item, Entity entity) {
		World world = entity.getEntityWorld();
		BlockPos pos = entity.getPosition();

		return new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(item));
	}

	private static boolean isUsingItem(EntityLivingBase base, Item item) {
		ItemStack stack = base.getHeldItemMainhand();
		if(stack.isEmpty()) {
			stack = base.getHeldItemOffhand();
		}
		return !stack.isEmpty() && stack.getItem() == item && base.isHandActive();
	}

	private static boolean isGoheiMode(EntityPlayer player, GoheiMode mode) {
		ItemStack stack = new ItemStack(ModItems.HAKUREI_GOHEI);
		return hasItemStack(player.inventory.mainInventory, stack) && getValidMode(player.inventory.mainInventory, stack, mode);
	}

	private static boolean hasItemStack(NonNullList<ItemStack> stacks, ItemStack stack) {
		for(ItemStack itemStack : stacks) {
			if(!itemStack.isEmpty() && itemStack.isItemEqualIgnoreDurability(stack)) {
				return true;
			}
		}

		return false;
	}

	private static boolean getValidMode(NonNullList<ItemStack> stacks, ItemStack stack, GoheiMode mode) {
		for(ItemStack itemStack : stacks) {
			if(!itemStack.isEmpty() && stack.getItem() == itemStack.getItem() && getGoheiMode(itemStack) == mode) {
				return true;
			}
		}

		return false;
	}

	private static GoheiMode getGoheiMode(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		return nbt == null ? GoheiMode.OFFENSIVE : GoheiMode.fromType(nbt.getByte("GoheiMode"));
	}
}
