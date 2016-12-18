/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.block;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import arekkuusu.grimoireofalice.common.lib.LibBlockName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHolyKeyStone extends BlockMod {

	public BlockHolyKeyStone() {
		super(LibBlockName.HOLY_KEY, Material.ROCK);
		setHardness(2.0F);
		setHarvestLevel("pickaxe", 1);
		setSoundType(SoundType.STONE);
		setResistance(15.0F);
		setLightLevel(0.5F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean p_77624_4_) {
		list.add(TextFormatting.WHITE + "" + TextFormatting.ITALIC + I18n.format("grimoire.tooltip.holy_key_stone_header.name"));
		list.add(TextFormatting.ITALIC + I18n.format("grimoire.tooltip.holy_key_stone_use.name"));
	}

	@Override
	public int tickRate(World worldIn) {
		return worldIn.isRaining() ? 40 : 100;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		Optional<EntityPlayer> optPlayer = getPlayerInRange(world, pos);
		if(optPlayer.isPresent()) {
			EntityPlayer player = optPlayer.get();
			addPlayerEffect(player);
			ifNear(world, pos, rand);
			world.scheduleUpdate(pos, this, 10); //Update more frequently if a player is around
		}
		else {
			world.scheduleUpdate(pos, this, tickRate(world));
		}
	}

	private void addPlayerEffect(EntityPlayer player) {
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50, 3));
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 3));
	}

	private void ifNear(World world, BlockPos pos, Random rand) {
		if(world.isRemote) {
			float d0 = 0.0625F;
			for(int l = 0; l < 6; ++l) {
				float d1 = pos.getX() + rand.nextFloat();
				float d2 = pos.getY() + rand.nextFloat();
				float d3 = pos.getZ() + rand.nextFloat();

				if(l == 0 && !(world.getBlockLightOpacity(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == 0)) d2 = pos.getY() + 1 + d0;
				if(l == 1 && !(world.getBlockLightOpacity(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == 0)) d2 = pos.getY() - d0;
				if(l == 2 && !(world.getBlockLightOpacity(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)) == 0)) d3 = pos.getZ() + 1 + d0;
				if(l == 3 && !(world.getBlockLightOpacity(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())) == 0)) d3 = pos.getZ() - d0;
				if(l == 4 && !(world.getBlockLightOpacity(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())) == 0)) d1 = pos.getX() + 1 + d0;
				if(l == 5 && !(world.getBlockLightOpacity(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())) == 0)) d1 = pos.getX() - d0;

				if(d1 < pos.getX() || d1 > pos.getX() + 1 || d2 < 0.0D || d2 > pos.getY() + 1 || d3 < pos.getZ() || d3 > pos.getZ() + 1) {
					world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, d1, d2, d3, 0.0D, 0.0D, 0.0D, 0);
				}
			}
		}
	}

	private Optional<EntityPlayer> getPlayerInRange(World world, BlockPos pos) {
		if(world.isRaining()) return Optional.ofNullable(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 3, false));
		else return Optional.empty();
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.createExplosion(null, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 2.0F, false);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}

	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
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
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 75, 2));
			player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50, 2));
			player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 50, 2));
		}
	}
}