/**
 * This class was created by <ArekkuusuJerii>. It's distributed as
 * part of the Grimoire Of Alice Mod. Get the Source Code in github:
 * https://github.com/ArekkuusuJerii/Grimore-Of-Alice
 *
 * Grimoire Of Alice is Open Source and distributed under the
 * Grimoire Of Alice license: https://github.com/ArekkuusuJerii/Grimoire-Of-Alice/blob/master/LICENSE.md
 */
package arekkuusu.grimoireofalice.item.crafting;

import arekkuusu.grimoireofalice.block.ModBlocks;
import arekkuusu.grimoireofalice.handler.ConfigHandler;
import arekkuusu.grimoireofalice.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VanillaCrafting {

    public static void booksAndStrings() {

        //Items
        shaped().grid("ISG", "SAS", "GSI")
                .where('S').mapsTo(Items.STRING)
                .where('G').mapsTo("dustGlowstone")
                .where('I').mapsTo(Items.GUNPOWDER)
                .where('A').mapsTo(Items.FIREWORK_CHARGE)
                .outputs(new ItemStack(ModItems.VOLATILE_STRING, 16)).build();

        shaped().grid("STS", "TAT", "STS")
                .where('S').mapsTo(Items.PAPER)
                .where('A').mapsTo(Items.COAL)
                .where('T').mapsTo("stickWood")
                .outputs(new ItemStack(ModItems.SOLDIFIED_PAPER, 4)).build();

        //Blocks
        shaped().grid("SSS", "SSS", "SSS")
                .where('S').mapsTo(Items.SUGAR)
                .outputs(ModBlocks.SUGAR_BLOCK).build();

        shaped().grid("ACA", "CSC", "ACA")
                .where('S').mapsTo("logWood")
                .where('A').mapsTo(ModItems.VOLATILE_STRING)
                .where('C').mapsTo(Items.COAL)
                .outputs(new ItemStack(ModBlocks.ROPE_BLOCK, 8)).build();

        shaped().grid("AAA", "ASA", "AAA")
                .where('A').mapsTo(Items.PAPER)
                .where('S').mapsTo(ModItems.SOLDIFIED_PAPER)
                .outputs(new ItemStack(ModBlocks.PAPER_BLOCK, 8)).build();

        shaped().grid("AAA", "ASA", "AAA")
                .where('A').mapsTo("stone")
                .where('S').mapsTo(ModItems.VOLATILE_STRING)
                .outputs(ModBlocks.COMPACT_STONE).build();

        shaped().grid("ANA", "ASA", "ANA")
                .where('S').mapsTo(ModBlocks.COMPACT_STONE)
                .where('N').mapsTo("logWood")
                .where('A').mapsTo(ModItems.SHIMENAWA_ROPE)
                .outputs(ModBlocks.COMPACT_STONE).build();

		//for (int i = 0; i < 16; ++i) {
			shapeless()
					.add(new ItemStack(ModItems.SHROOM_SLICE, 1, 0))
					.add(ModItems.BUDAH_BOUL)
					.outputs(new ItemStack(Items.DYE.setContainerItem(ModItems.BUDAH_BOUL), 1, 0));
		//}

        GameRegistry.addSmelting(Blocks.QUARTZ_BLOCK, new ItemStack(ModItems.GLORIOUS_NIPPON_STEEL), 0);
        GameRegistry.addSmelting(ModItems.IMPURE_ROCK, new ItemStack(ModItems.HIHIIROKANE), 0);
    }

    public static void masks() { //TODO: Move Mask Recipes to Altar
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
                .outputs(ModItems.KOKOROS_MASKS).build();

        shaped().grid("SSS", "GAG", "SSS")
                .where('S').mapsTo(ModItems.SOLDIFIED_PAPER)
                .where('G').mapsTo(ModItems.VOLATILE_STRING)
                .where('A').mapsTo(Items.CLAY_BALL)
                .outputs(ModItems.MASK).build();

        if(ConfigHandler.maskRecipes){
            mask().where('R').mapsTo(Items.SUGAR).outputs(ModItems.FOX_MASK).build();
            mask().where('R').mapsTo(Items.BLAZE_POWDER).outputs(ModItems.FUKU_NO_KAMI_MASK).build();
            mask().where('R').mapsTo(Items.POISONOUS_POTATO).outputs(ModItems.HANNYA_MASK).build();
            mask().where('R').mapsTo(Items.GHAST_TEAR).outputs(ModItems.HYOTTOKO_MASK).build();
            mask().where('R').mapsTo(Items.ROTTEN_FLESH).outputs(ModItems.KOOMOTE_MASK).build();
            mask().where('R').mapsTo(Items.FERMENTED_SPIDER_EYE).outputs(ModItems.MASK_OF_HOPE).build();
            mask().where('R').mapsTo(Items.MUSHROOM_STEW).outputs(ModItems.MONKEY_MASK).build();
            mask().where('R').mapsTo("slimeball").outputs(ModItems.RAIDEN_MASK).build();
            mask().where('R').mapsTo(Items.NETHER_STAR).outputs(ModItems.UBA_MASK).build();
        }
    }

    private static ShapedRecipe shaped() {
        return new ShapedRecipe();
    }

    private static ShapelessRecipe shapeless() {
        return new ShapelessRecipe();
    }

    private static ShapedRecipe mask() {
        return new ShapedRecipe().grid("IAR", "SGS", " S ")
                .where('S').mapsTo(ModItems.VOLATILE_STRING)
                .where('G').mapsTo(ModItems.MASK)
                .where('A').mapsTo(Items.NETHER_STAR)
                .where('I').mapsTo(new ItemStack(Items.POTIONITEM, 1, 16));
    }
}
