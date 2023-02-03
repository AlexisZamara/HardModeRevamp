package ratatoskr.hardModeRevamp.villagers;

import java.util.List;

import org.bukkit.entity.Villager;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.persistence.PersistentDataType;

import ratatoskr.hardModeRevamp.utils.RConstants;

public class TradesManager {
	
	public static void loadTrades(Villager villager) {
		Merchant merchant = (Merchant) villager;
		List<MerchantRecipe> trades = TradesRecipes.setTrades();
		
		villager.setVillagerLevel(5);
		merchant.setRecipes(trades);
		
		villager.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.MERCHANT_LOADED_STRING);
	}
}
