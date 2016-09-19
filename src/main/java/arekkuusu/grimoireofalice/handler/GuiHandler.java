/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.handler;

import arekkuusu.grimoireofalice.client.gui.GuiItemInventory;
import arekkuusu.grimoireofalice.client.gui.GuiScreenBookYoukai;
import arekkuusu.grimoireofalice.client.gui.GuiScreenGuide;
import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.plugin.touhou.SpellCardContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case 2:
				EnumHand hand = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem = player.getHeldItem(hand);
				if(heldItem == null || heldItem.getItem() != ModItems.pouch) return null;
				return new SpellCardContainer(player.inventory, heldItem);
			default:
				return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
			case 0:
				ItemStack heldItem = player.getHeldItemMainhand();
				if(heldItem == null || heldItem.getItem() != ModItems.youkaiBook) heldItem = player.getHeldItemOffhand();

				if(heldItem == null || heldItem.getItem() != ModItems.youkaiBook) return null;
				else return new GuiScreenBookYoukai(player, heldItem, true);
			case 1:
				return new GuiScreenGuide();
			case 2:
				EnumHand hand = EnumHand.MAIN_HAND.ordinal() == x ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
				ItemStack heldItem0 = player.getHeldItem(hand);
				if(heldItem0 == null || heldItem0.getItem() != ModItems.pouch) return null;
				return new GuiItemInventory(player.inventory, heldItem0);
			default:
				return null;
		}
	}
}
