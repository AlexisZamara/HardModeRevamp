package ratatoskr.hardModeRevamp.enchantments.inventory;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.enchantments.Enchant;
import ratatoskr.hardModeRevamp.logger.Logging;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class EnchantItem implements Listener {
	Plugin plugin = Main.getPlugin();
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent event) {
		if(event.getEnchantBlock().getType() != Material.ENCHANTING_TABLE) {
			return;
		}
		
		Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
		Enchantment baseEnchant = null;
		Integer baseEnchantLevel = 0;
		
		String metaName = "r_" + event.getEnchanter().getName();
		EnchantmentOffer[] offers = null;
		if(event.getEnchantBlock().hasMetadata(metaName)) {
			for(MetadataValue val : event.getEnchantBlock().getMetadata(metaName)) {
				if(val.getOwningPlugin() == plugin) {
					offers = (EnchantmentOffer[]) val.value();
				}
			}
			event.getEnchantBlock().removeMetadata(metaName, plugin);
		}
		
		if(offers == null) {
			Logging.logError("no offers found in metadata, cannot provide reliable enchant", 2);
			// if offers cannot be located, complete an unreliable enchantment instead in order to prevent illegal enchant
			for(Map.Entry<Enchantment, Integer> e : enchants.entrySet()) {
				baseEnchant = e.getKey();
				baseEnchantLevel = e.getValue();
				Logging.logError("onEnchantItem could not locate suitable metadata of enchantment for " + event.getEnchanter().getName() + " enchanting " + event.getItem().getType().toString(), 2);
				break;
			}
		}
		else {
			baseEnchant = offers[event.whichButton()].getEnchantment();
			baseEnchantLevel = offers[event.whichButton()].getEnchantmentLevel();
		}
				
		if(baseEnchant == null) {
			Logging.logError("could not find an enchantment in the list of enchantments to add to the item", 2);
			return;
		}

		Map<Enchantment, Integer> newEnchants = Enchant.enchantAtTable(baseEnchant, baseEnchantLevel, event.getExpLevelCost(), event.getItem().getType());

		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				for(Map.Entry<Enchantment, Integer> ench : event.getItem().getEnchantments().entrySet()) {
					Logging.logError("removing: " + ench.getKey().toString() + " at level " + String.valueOf(ench.getValue()), 0); 
					event.getItem().removeEnchantment(ench.getKey());
				}
				Logging.logError("item still has enchants: " + event.getItem().getEnchantments().keySet().toString(), 1);
				event.getItem().addEnchantments(newEnchants); // this does not work when enchanting books
				// event.getItem().addUnsafeEnchantments(newEnchants); solution to enchant books?
			}
		}, 1L);
		return;
	}
	
	@EventHandler
	public void onPrepareEnchantItem(PrepareItemEnchantEvent event) {
		if(event.getEnchantBlock().getType() != Material.ENCHANTING_TABLE) {
			return;
		}
		String metaName = "r_" + event.getEnchanter().getName();
		event.getEnchantBlock().setMetadata(metaName, new FixedMetadataValue(plugin, event.getOffers()));
	}
}
