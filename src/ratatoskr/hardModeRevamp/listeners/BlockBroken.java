package ratatoskr.hardModeRevamp.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
//import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.utils.Random;

public class BlockBroken implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	public Material[] cropsBlock = {Material.WHEAT,Material.BEETROOTS,Material.POTATOES,Material.CARROTS,Material.NETHER_WART,Material.COCOA,Material.PUMPKIN,Material.MELON};
	public Material[] seeds = {Material.WHEAT_SEEDS,Material.BEETROOT_SEEDS,Material.POTATO,Material.CARROT,Material.NETHER_WART,Material.COCOA_BEANS};
	
	@EventHandler
	public void onBlockBroken(BlockBreakEvent event) {
		if(plugin.getConfig().getBoolean("farmingrework.enabled")) {
			if(!event.getBlock().hasMetadata("ppb")) {
				if(Arrays.asList(cropsBlock).contains(event.getBlock().getType()) && event.getBlock().isPreferredTool(event.getPlayer().getInventory().getItemInMainHand())) {
					if(event.getBlock().getType() == Material.PUMPKIN || event.getBlock().getType() == Material.MELON) {
//						ExperienceOrb expOrb = event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
						List<Integer> values = plugin.getConfig().getIntegerList("farmingrework.farmxp");
						event.setExpToDrop(Random.RandomInt(values.get(0), values.get(1)));
//						expOrb.setExperience(Random.RandomInt(values.get(0), values.get(1)));
						return;
					}
					
					Ageable age = (Ageable) event.getBlock().getBlockData();
					if(age.getAge() == age.getMaximumAge()) {
						for(ItemStack item : event.getBlock().getDrops()) {
							if(item.getAmount() > 1 && Arrays.asList(seeds).contains(item.getType())) {
								item.setAmount(item.getAmount() - 1);
							}
							event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
						}
//						ExperienceOrb expOrb = event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
						List<Integer> values = plugin.getConfig().getIntegerList("farmingrework.farmxp");
						event.setExpToDrop(Random.RandomInt(values.get(0), values.get(1)));
//						expOrb.setExperience(Random.RandomInt(values.get(0), values.get(1)));
					}
				}
			}
		}
	}
}
