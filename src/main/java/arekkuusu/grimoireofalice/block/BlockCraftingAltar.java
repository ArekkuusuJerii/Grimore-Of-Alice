/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.block;

import java.util.List;
import java.util.Random;

import arekkuusu.grimoireofalice.block.tile.TileCraftingAltar;
import arekkuusu.grimoireofalice.lib.LibBlockName;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockCraftingAltar extends BlockMod implements ITileEntityProvider {

	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

	public BlockCraftingAltar() {
		super(LibBlockName.CRAFTINGALTAR, Material.ROCK);
		setHardness(2.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("axe", 1);
		setResistance(2000.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.GOLD + "Crafting Altar");
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileCraftingAltar tile = (TileCraftingAltar) worldIn.getTileEntity(pos);
		boolean ok = false;
		if (tile != null) {
			ok = playerIn.isSneaking() ? tile.removeItem(playerIn) : tile.doCrafting();
		}
		return ok;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				if (i > -2 && i < 2 && j == -1) {
					j = 2;
				}

				if (rand.nextInt(16) == 0) {
					for (int k = 0; k <= 1; ++k) {

						if (!worldIn.isAirBlock(pos.add(i / 2, 0, j / 2))) {
							break;
						}

						worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, pos.getX() + 0.5D, pos.getY() + 2.0D, pos.getZ() + 0.5D,
								(i + rand.nextFloat()) - 0.5D, (k - rand.nextFloat() - 1.0F), (j + rand.nextFloat()) - 0.5D);
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		return facing.getHorizontalIndex();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, PROPERTYFACING);
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing);
	}

	@SuppressWarnings("deprecation") //Internal
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == ModBlocks.COMPACT_STONE;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCraftingAltar();
	}
}
