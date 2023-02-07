package ratatoskr.hardModeRevamp.enchantments.inventory;

import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import ratatoskr.hardModeRevamp.enchantments.Enchant;
import ratatoskr.hardModeRevamp.logger.Logging;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;

public class EnchantItem implements Listener {
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent event) {
		if(event.getEnchantBlock().getType() != Material.ENCHANTING_TABLE) {
			return;
		}
		
		Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
		boolean reroll = false;
		Enchantment baseEnchant = null;
		Integer baseEnchantLevel = 0;
		
		// assumption: the first enchant in the Map is the enchant displayed on the enchanting table
		for(Map.Entry<Enchantment, Integer> e : enchants.entrySet()) {
			baseEnchant = e.getKey();
			baseEnchantLevel = e.getValue();
			break;
		}
		
		if(baseEnchant == null) {
			Logging.logError("could not find an enchantment in the list of enchantments to add to the item", 2);
			return;
		}
		
		if(reroll) {
			event.setCancelled(true);
			Map<Enchantment, Integer> newEnchants = Enchant.enchantAtTable(baseEnchant, baseEnchantLevel, event.getExpLevelCost(), event.getItem().getType());
			Bukkit.getPluginManager().callEvent(new EnchantItemEvent(event.getEnchanter(), event.getView(), event.getEnchantBlock(), event.getItem(), event.getExpLevelCost(), newEnchants, event.whichButton()));
		}
		return;
	}
}
