package ratatoskr.hardModeRevamp.villagers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

public class TradesRecipes {
	
	public static List<MerchantRecipe> setTrades() {
		List<MerchantRecipe> recipes = new ArrayList<>();
		
		MerchantRecipe diamondToEmerald = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		diamondToEmerald.addIngredient(new ItemStack(Material.DIAMOND, 1));
		recipes.add(diamondToEmerald);
		
		MerchantRecipe ironToEmerald = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		ironToEmerald.addIngredient(new ItemStack(Material.IRON_BLOCK, 3));
		recipes.add(ironToEmerald);
		
		MerchantRecipe redstoneToEmerald = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		redstoneToEmerald.addIngredient(new ItemStack(Material.REDSTONE_BLOCK, 11));
		recipes.add(redstoneToEmerald);
		
		MerchantRecipe coalToEmerald = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		coalToEmerald.addIngredient(new ItemStack(Material.COAL_BLOCK, 33));
		recipes.add(coalToEmerald);
		
		
		MerchantRecipe emeraldToBell = new MerchantRecipe(new ItemStack(Material.BELL, 4), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToBell.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToBell);
		
		MerchantRecipe emeraldToExperience = new MerchantRecipe(new ItemStack(Material.EXPERIENCE_BOTTLE, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToExperience.addIngredient(new ItemStack(Material.EMERALD, 1));
		emeraldToExperience.addIngredient(new ItemStack(Material.GLASS_BOTTLE, 1));
		recipes.add(emeraldToExperience);
		
		MerchantRecipe emeraldToItemframe = new MerchantRecipe(new ItemStack(Material.ITEM_FRAME, 16), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToItemframe.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToItemframe);
		
		MerchantRecipe emeraldToLapisLazuli = new MerchantRecipe(new ItemStack(Material.LAPIS_LAZULI, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToLapisLazuli.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToLapisLazuli);
		
		MerchantRecipe emeraldToNametag = new MerchantRecipe(new ItemStack(Material.NAME_TAG, 3), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToNametag.addIngredient(new ItemStack(Material.EMERALD, 1));
		emeraldToNametag.addIngredient(new ItemStack(Material.LEATHER, 3));
		recipes.add(emeraldToNametag);
		
		MerchantRecipe emeraldToPainting = new MerchantRecipe(new ItemStack(Material.PAINTING, 16), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToPainting.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToPainting);
		
		MerchantRecipe emeraldToScute = new MerchantRecipe(new ItemStack(Material.SCUTE, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToScute.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToScute);
		
		MerchantRecipe emeraldToStew = new MerchantRecipe(new ItemStack(Material.SUSPICIOUS_STEW, 1), 0, Integer.MAX_VALUE, false, 0, 1.0f);
		emeraldToStew.addIngredient(new ItemStack(Material.EMERALD, 1));
		recipes.add(emeraldToStew);
		
		
		return recipes;
	}
}