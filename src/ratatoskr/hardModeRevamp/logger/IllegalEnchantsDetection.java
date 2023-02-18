package ratatoskr.hardModeRevamp.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;

public class IllegalEnchantsDetection implements Listener {
	
	@EventHandler
	public void onItemClickEvent(InventoryClickEvent event) {
		if(event.getInventory().getType() == InventoryType.CREATIVE) {
			return;
		}
		if(event.getCurrentItem().getType() != Material.AIR) {
			ItemMeta meta = event.getCurrentItem().getItemMeta();
			if(!meta.hasEnchants() && event.getCurrentItem().getType() != Material.ENCHANTED_BOOK) {
				return;
			}
			Integer remove = event.getCurrentItem().removeEnchantment(Enchantment.MENDING);
			if(remove > 0) {
				Logging.logEnchant(event.getWhoClicked().getName() + " has clicked on " + event.getCurrentItem().getType().toString() + " with illegal enchant: MENDING at " + new SimpleDateFormat("yy.MM.dd HH.mm.ss").format(new Date()));
			}
			remove = event.getCurrentItem().removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
			if(remove > 0) {
				Logging.logEnchant(event.getWhoClicked().getName() + " has clicked on " + event.getCurrentItem().getType().toString() + " with illegal enchant: FORTUNE at " + new SimpleDateFormat("yy.MM.dd HH.mm.ss").format(new Date()));
			}
		}
	}
}
