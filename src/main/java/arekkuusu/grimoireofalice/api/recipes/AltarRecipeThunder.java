package arekkuusu.grimoireofalice.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class AltarRecipeThunder extends AltarRecipe {

	public AltarRecipeThunder(ItemStack result, Object... inputs) {
		super(result, inputs);
	}

	@Override
	public boolean checkRecipe(List<ItemStack> usedItems, World world) {
		return world.isThundering() && super.checkRecipe(usedItems, world);
	}
}
