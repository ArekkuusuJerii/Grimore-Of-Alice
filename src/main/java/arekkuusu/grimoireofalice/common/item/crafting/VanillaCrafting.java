/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.common.item.crafting;

import arekkuusu.grimoireofalice.common.block.ModBlocks;
import arekkuusu.grimoireofalice.common.core.handler.ConfigHandler;
import arekkuusu.grimoireofalice.common.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaCrafting {

	public static void booksAndStrings() {
		//Items
		shaped().grid("SSS", "SAS", "SSS")
				.where('S').mapsTo("paper")
				.where('A').mapsTo(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.SOLIDIFIED_PAPER, 1)).build();

		//Blocks
		shaped().grid("SSS", "SSS", "SSS")
				.where('S').mapsTo(Items.SUGAR)
				.outputs(ModBlocks.SUGAR_BLOCK).build();

		shaped().grid("ASA", "IWI", "ASA")
				.where('W').mapsTo("logWood")
				.where('I').mapsTo("nuggetIron")
				.where('S').mapsTo(Items.STICK)
				.where('A').mapsTo(ModItems.PASTE)
				.outputs(new ItemStack(ModBlocks.ROPE, 8)).build();

		shaped().grid(" S ", " A ", "AAA")
				.where('A').mapsTo("paper")
				.where('S').mapsTo(ModItems.SOLIDIFIED_PAPER)
				.outputs(new ItemStack(ModBlocks.PAPER, 8)).build();

		shaped().grid("AAA", "ASA", "AAA")
				.where('A').mapsTo("stone")
				.where('S').mapsTo(new ItemStack(Blocks.DIRT, 1, 1))
				.outputs(ModBlocks.COMPACT_STONE).build();

		shaped().grid("RLR", "PGP")
				.where('G').mapsTo(ModBlocks.COMPACT_STONE)
				.where('L').mapsTo("logWood")
				.where('R').mapsTo(ModBlocks.ROPE)
				.where('P').mapsTo(ModBlocks.PAPER)
				.outputs(ModBlocks.PILLAR_ALTAR).build();

		shaped().grid("FFF", "FIF", "FFF")
				.where('F').mapsTo(Items.FEATHER)
				.where('I').mapsTo("dyeBlack")
				.outputs(new ItemStack(ModItems.BLACK_FEATHER, 2)).build();

		shaped().grid("PBP", "WCW", "WDW")
				.where('P').mapsTo("paper")
				.where('B').mapsTo(Items.BOOK)
				.where('W').mapsTo("plankWood")
				.where('D').mapsTo(ModBlocks.COMPACT_STONE)
				.where('C').mapsTo(Blocks.CRAFTING_TABLE)
				.outputs(ModBlocks.CRAFTING_ALTAR).build();

		shaped().grid("N N", "PLP", "N N")
				.where('N').mapsTo("nuggetIron")
				.where('P').mapsTo(Items.PAPER)
				.where('L').mapsTo(Items.LEATHER)
				.outputs(ModItems.HARDENED_LEATHER).build();

		shaped().grid("NNN", "NNN", "NNN")
				.where('N').mapsTo(ModItems.IRON_NUGGET)
				.outputs(Items.IRON_INGOT).build();

		shapeless()
				.add(ModBlocks.SUGAR_BLOCK)
				.outputs(new ItemStack(Items.SUGAR, 9)).build();

		shapeless()
				.add(Items.IRON_INGOT)
				.outputs(new ItemStack(ModItems.IRON_NUGGET, 9)).build();

		shapeless()
				.add(ModItems.SOLIDIFIED_PAPER)
				.add(Items.STRING)
				.add(Items.WATER_BUCKET)
				.add(Items.CLAY_BALL)
				.outputs(new ItemStack(ModItems.PASTE, 2)).build();

		shapeless()
				.add(Items.STICK)
				.add(Items.BOWL)
				.outputs(ModItems.MORTAR_AND_PESTLE).build();

		if(ConfigHandler.grimoireOfAlice.crafting.mask) {
			shapeless()
					.add(ModItems.PASTE)
					.add(ModItems.SOLIDIFIED_PAPER)
					.add(ModItems.SOLIDIFIED_PAPER)
					.outputs(ModItems.MASK).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hihiirokane) {
			shapeless()
					.add(ModItems.IMPURE_ROCK)
					.add(Blocks.COAL_BLOCK)
					.outputs(ModItems.HIHIIROKANE).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hihiirokaneBlock) {
			shaped().grid("HHH", "HHH", "HHH")
					.where('H').mapsTo(ModItems.HIHIIROKANE)
					.outputs(ModBlocks.HIHIIROKANE_BLOCK).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.shimenawaRope) {
			shaped().grid("RRR", "PPP")
					.where('R').mapsTo(ModBlocks.ROPE)
					.where('P').mapsTo(ModBlocks.PAPER)
					.outputs(ModItems.SHIMENAWA_ROPE).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.popsicleStick) {
			shaped().grid("  S", "SS ", "SS ")
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.POPSICLE_STICK).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.waterMelonBlade) {
			shaped().grid("  M", " M ", "MS ")
					.where('M').mapsTo(Items.MELON)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.WATERMELON_BLADE).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.hakurouken) {
			shaped().grid(" I ", "S  ")
					.where('S').mapsTo(Items.STICK)
					.where('I').mapsTo(Items.IRON_INGOT)
					.outputs(ModItems.HAKUROUKEN).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.tenshiHat) {
			shaped().grid("F F", "CHC")
					.where('F').mapsTo(ModItems.HEAVENLY_PEACH)
					.where('H').mapsTo(Items.LEATHER_HELMET)
					.where('C').mapsTo(new ItemStack(Blocks.CARPET, 1, 15))
					.outputs(ModItems.TENSHI_HAT).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.talisman) {
			shaped().grid("G  ", " P ", "  G")
					.where('G').mapsTo(Items.GLOWSTONE_DUST)
					.where('P').mapsTo(ModItems.SOLIDIFIED_PAPER)
					.outputs(ModItems.SPIRITUAL_STRIKE_TALISMAN).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.waterMelonSword) {
			shapeless()
					.add(ModItems.POPSICLE_STICK)
					.add(Blocks.MELON_BLOCK)
					.add(Blocks.ICE)
					.outputs(ModItems.WATERMELON_SWORD).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.cattailPlant) {
			shaped().grid("AE ", " AS", " SA")
					.where('A').mapsTo(Blocks.VINE)
					.where('E').mapsTo(Items.SLIME_BALL)
					.where('S').mapsTo("stickWood")
					.outputs(ModItems.CATTAIL_PLANT).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.ghastlySendOffLantern) {
			shaped().grid("EAE", "ASA", "EAE")
					.where('A').mapsTo(Blocks.GLASS_PANE)
					.where('E').mapsTo(ModItems.SOLIDIFIED_PAPER)
					.where('S').mapsTo(Blocks.TORCH)
					.outputs(new ItemStack(ModItems.SEND_OFF_LANTERN, 4)).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.patchyBook) {
			shapeless()
					.add(Items.WRITABLE_BOOK)
					.add("stickWood")
					.add(Items.FEATHER)
					.outputs(ModItems.PATCHY_BOOK).build();
		}

		if(ConfigHandler.grimoireOfAlice.food.grilledLamprey) {
			shapeless()
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add(Items.COOKED_FISH)
					.add("stickWood")
					.outputs(ModItems.GRILLED_LAMPREY).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.syringe) {
			shapeless()
					.add(Items.GLASS_BOTTLE)
					.add(new ItemStack(ModItems.SHROOM_POWDER, 1, OreDictionary.WILDCARD_VALUE))
					.outputs(ModItems.SYRINGE).build();
		}

		if(ConfigHandler.grimoireOfAlice.crafting.simpleUfo) {
			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassGreen")
					.outputs(ModItems.UFO_GREEN).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassRed")
					.outputs(ModItems.UFO_RED).build();

			shaped().grid("GGG", "GIG", "B B")
					.where('I').mapsTo(Blocks.SLIME_BLOCK)
					.where('B').mapsTo(ModItems.PASTE)
					.where('G').mapsTo("blockGlassBlue")
					.outputs(ModItems.UFO_BLUE).build();
		}

		if(ConfigHandler.grimoireOfAlice.food.shroomPowder) {
			for(int i = 0; i < 16; i++)
				shapeless()
						.add(new ItemStack(ModBlocks.SHROOM, 1, i))
						.add(new ItemStack(ModItems.MORTAR_AND_PESTLE, 1, OreDictionary.WILDCARD_VALUE))
						.outputs(new ItemStack(ModItems.SHROOM_POWDER, 1, 15 - i)).build();
		}

		shapeless()
				.add("treeLeaves")
				.outputs(ModItems.LEAF).build();

		GameRegistry.addSmelting(Items.BLAZE_ROD, new ItemStack(ModItems.TAMAHAGANE_STEEL), 1);

		GameRegistry.addSmelting(ModBlocks.IMPURE_STONE, new ItemStack(ModItems.IMPURE_ROCK), 1);
	}

	public static void masks() {
		if(ConfigHandler.grimoireOfAlice.crafting.kokoroMask) {
			shapeless()
					.add(ModItems.FOX_MASK)
					.add(ModItems.FUKU_NO_KAMI_MASK)
					.add(ModItems.HANNYA_MASK)
					.add(ModItems.HYOTTOKO_MASK)
					.add(ModItems.KOOMOTE_MASK)
					.add(ModItems.MASK_OF_HOPE)
					.add(ModItems.MONKEY_MASK)
					.add(ModItems.RAIDEN_MASK)
					.add(ModItems.UBA_MASK)
					.outputs(ModItems.KOKORO_MASKS).build();

			mask(Items.SUGAR, ModItems.FOX_MASK);
			mask(Items.BLAZE_POWDER, ModItems.FUKU_NO_KAMI_MASK);
			mask(Items.POISONOUS_POTATO, ModItems.HANNYA_MASK);
			mask(Items.GHAST_TEAR, ModItems.HYOTTOKO_MASK);
			mask(Items.ROTTEN_FLESH, ModItems.KOOMOTE_MASK);
			mask(Items.FERMENTED_SPIDER_EYE, ModItems.MASK_OF_HOPE);
			mask(Items.MUSHROOM_STEW, ModItems.MONKEY_MASK);
			rawMask().where('R').mapsTo("slimeball").outputs(ModItems.RAIDEN_MASK).build();
			mask(Items.NETHER_STAR, ModItems.UBA_MASK);
		}
	}

	private static ShapedRecipe shaped() {
		return new ShapedRecipe();
	}

	private static ShapelessRecipe shapeless() {
		return new ShapelessRecipe();
	}

	private static ShapedRecipe rawMask() {
		return new ShapedRecipe().grid("IAR", "SGS", " S ")
				.where('S').mapsTo(ModItems.PASTE)
				.where('G').mapsTo(ModItems.MASK)
				.where('A').mapsTo(ModItems.NETHER_SHARD)
				.where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
	}

	private static void mask(Item item, Item output) {
		rawMask().where('R').mapsTo(item).outputs(item).build();
	}
}
