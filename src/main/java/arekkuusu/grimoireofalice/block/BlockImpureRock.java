package arekkuusu.grimoireofalice.block;

import arekkuusu.grimoireofalice.item.ModItems;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockImpureRock extends BlockMod {

	public BlockImpureRock() {
		super(LibBlockName.IMPURE_STONE, Material.ROCK);
		setHardness(5F);
		setHarvestLevel("pickaxe", 3);
	}

	@Nullable
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModItems.IMPURE_ROCK;
	}

	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return 3 + fortune;
	}
}
