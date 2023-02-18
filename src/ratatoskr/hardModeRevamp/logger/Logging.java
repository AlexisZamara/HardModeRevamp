package ratatoskr.hardModeRevamp.logger;

import java.util.Set;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import ratatoskr.hardModeRevamp.Main;

public class Logging {
	private static Plugin plugin = Main.getPlugin();

	public static void logError(String message, Integer level) {
		if(!plugin.getConfig().getBoolean("logs.enabled")) {
			return;
		}
		
		if(level < plugin.getConfig().getInt("logs.level")) {
			return;
		}
		
		String levelString = "";
		switch(level) {
		case 0:
			levelString = ChatColor.WHITE + "INFO";
			break;
		case 1:
			levelString = ChatColor.YELLOW + "WARNING";
			break;
		case 2:
			levelString = ChatColor.RED + "ERROR";
			break;
		}
		
		if(plugin.getConfig().getBoolean("logs.op")) {
			Set<OfflinePlayer> ops = plugin.getServer().getOperators();
			for(OfflinePlayer op : ops) {
				if(op.isOnline()) {
					op.getPlayer().sendMessage(levelString + ": " + message);
				}
			}
		}
		Main.log(level + ": " + message, "rlogs.txt");
	}
	
	public static void logEnchant(String message) {
		if(!plugin.getConfig().getBoolean("logs.enabled")) {
			return;
		}
		
		if(plugin.getConfig().getBoolean("logs.op")) {
			Set<OfflinePlayer> ops = plugin.getServer().getOperators();
			for(OfflinePlayer op: ops) {
				if(op.isOnline()) {
					op.getPlayer().sendMessage(ChatColor.RED + "ILLEGAL ENCHANT: " + message);
				}
			}
		}
		Main.log(message, "enchantlogs.txt");
	}
}
