package arekkuusu.grimoireofalice.item;

import arekkuusu.grimoireofalice.GrimoireOfAlice;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMod extends Item {

	public ItemMod(String id) {
		super();
		setRegistryName(id);
		setUnlocalizedName(id);
		GameRegistry.register(this);
		setCreativeTab(GrimoireOfAlice.CREATIVE_TAB);
	}
}
