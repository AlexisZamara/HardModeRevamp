package ratatoskr.hardModeRevamp.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.utils.Random;

public class BlockBroken implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	private Material[] crops = {Material.WHEAT,Material.BEETROOTS,Material.POTATOES,Material.CARROTS,Material.NETHER_WART,Material.COCOA};
	private Material[] cropBlock = {Material.PUMPKIN,Material.MELON};
	private Material[] seeds = {Material.WHEAT_SEEDS,Material.BEETROOT_SEEDS,Material.POTATO,Material.CARROT,Material.NETHER_WART,Material.COCOA_BEANS};
	
	private Material[] hoes = {Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE};
	private Material[] axes = {Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE};
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBlockBroken(BlockBreakEvent event) {
		if(plugin.getConfig().getBoolean("farmingrework.enabled")) {
			if(Arrays.asList(crops).contains(event.getBlock().getType()) && Arrays.asList(hoes).contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
				Ageable age = (Ageable) event.getBlock().getBlockData();
				if(age.getAge() == age.getMaximumAge()) {
					for(ItemStack item : event.getBlock().getDrops()) {
						if(item.getAmount() > 1 && Arrays.asList(seeds).contains(item.getType())) {
							item.setAmount(item.getAmount() - 1);
						}
						event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), item);
					}
					
					age.setAge(0);
					event.getBlock().setBlockData(age);
					event.getPlayer().getInventory().getItemInMainHand().setDurability(((short) (event.getPlayer().getInventory().getItemInMainHand().getDurability() + 1)));
					
					ExperienceOrb expOrb = event.getPlayer().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
					List<Integer> values = plugin.getConfig().getIntegerList("farmingrework.farmxp");
					expOrb.setExperience(Random.RandomInt(values.get(0), values.get(1)));
					
					event.setCancelled(true);
				}
				return;
			}
				
			if(!event.getBlock().hasMetadata("ppb") && Arrays.asList(cropBlock).contains(event.getBlock().getType()) && Arrays.asList(axes).contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
				List<Integer> values = plugin.getConfig().getIntegerList("farmingrework.farmxp");
				event.setExpToDrop(Random.RandomInt(values.get(0), values.get(1)));
				return;
			}
		}
	}
}
