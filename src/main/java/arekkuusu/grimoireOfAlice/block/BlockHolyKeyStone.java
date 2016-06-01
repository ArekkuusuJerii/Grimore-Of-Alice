/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireOfAlice.block;

import java.util.Random;

import arekkuusu.grimoireOfAlice.client.tile.TileEntityHolyKeyStone;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockHolyKeyStone extends BlockGOABase implements ITileEntityProvider {

	BlockHolyKeyStone(Material material) {
		super(material);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 1);
		setResistance(15.0F);
		setCreativeTab(CreativeTabs.tabDecorations);
		setLightLevel(0.5F);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		return new TileEntityHolyKeyStone();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1 - f, z + 1);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int e) {
		world.createExplosion(null, x + 0.5, y, z + 0.5, 2.0F, false);
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return null;
	}

	@Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLivingBase) {
			boolean isRain = world.isRaining();
			if(!isRain) {
				EntityLivingBase livingEntity = (EntityLivingBase)entity;
				livingEntity.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 50, 2));
				livingEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 50, 2));
				livingEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 50, 2));
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(world, x, y, z, p_149749_5_, p_149749_6_);
		world.removeTileEntity(x, y, z);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? Blocks.stone.getBlockTextureFromSide(side) : side == 0 ? Blocks.stone.getBlockTextureFromSide(side) : blockIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon) {
		blockIcon = icon.registerIcon(getTextureName() + "_side");
	}
}