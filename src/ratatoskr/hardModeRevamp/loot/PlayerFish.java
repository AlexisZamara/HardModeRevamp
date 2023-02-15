package ratatoskr.hardModeRevamp.loot;

import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.meta.ItemMeta;

import ratatoskr.hardModeRevamp.logger.Logging;

public class PlayerFish implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
			Item item = (Item) event.getCaught();
			ItemMeta meta = item.getItemStack().getItemMeta();
			Logging.logError("caught " + item.getItemStack().getType(), 0);
			
			if(!meta.hasEnchants()) {
				return;
			}
			boolean curse = false;
			
			for(Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
				if(entry.getKey().getName() == Enchantment.MENDING.getName()) {
					Logging.logError("removing Mending from " + item.getItemStack().getType().toString(), 0);
					meta.removeEnchant(entry.getKey());
					meta.addEnchant(Enchantment.DURABILITY, 3, false);
					continue;
				}
				if(entry.getKey().getName() == Enchantment.LOOT_BONUS_BLOCKS.getName()) {
					Logging.logError("removing Fortune from " + item.getItemStack().getType().toString(), 0);
					meta.removeEnchant(entry.getKey());
					meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
					continue;
				}
				if(entry.getKey().getName() == Enchantment.BINDING_CURSE.getName() || entry.getKey().getName() == Enchantment.VANISHING_CURSE.getName()) {
					curse = true;
				}
			}
			if(Math.random() > 0.875 && !curse) {
				Logging.logError("adding curse of vanishing to " + item.getType().toString(), 0);
				meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);
			}
			item.getItemStack().setItemMeta(meta);
		}
	}
}
