package arekkuusu.grimoireofalice.block;

import java.util.List;

import javax.annotation.Nullable;

import arekkuusu.grimoireofalice.block.tile.TilePillarAltar;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPillarAltar extends BlockMod implements ITileEntityProvider {

	public BlockPillarAltar() {
		super(LibBlockName.PILLAR_ALTAR, Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(2000.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + I18n.format("grimoire.tooltip.pillar_altar_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.pillar_altar_heavy.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.pillar_altar_tier.name"));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TilePillarAltar tile = (TilePillarAltar)worldIn.getTileEntity(pos);
		boolean ok = false;
		if(tile != null) {
			if(playerIn.isSneaking()) {
				ok = tile.removeItem(playerIn);
			}
			else if(heldItem != null) {
				ok = tile.addItem(playerIn, heldItem);
			}
		}
		return ok;
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TilePillarAltar();
	}
}
