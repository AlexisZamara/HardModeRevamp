package ratatoskr.hardModeRevamp.logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;

public class Commands implements CommandExecutor {
	private Plugin plugin = Main.getPlugin();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!plugin.getConfig().getBoolean("logs.enabled")) {
			sender.sendMessage("logs are not enabled");
			return false;
		}
		
		if(!(sender instanceof Player)) {
			return false;
		}
		if(!sender.isOp()) {
			return false;
		}
		
		if(args.length < 1) {
			Boolean state = !plugin.getConfig().getBoolean("logs.op");
			plugin.getConfig().set("logs.op", state);
			sender.sendMessage("op logs set to " + state.toString());
			return true;
		}
		if(args[0].equals("true") || args[0].equals("false")) {
			plugin.getConfig().set("logs.op", args[0]);
			sender.sendMessage("op logs set to " + args[0].toString());
			return true;
		}
		
		sender.sendMessage("unknown error processing command");
		return false;
	}
}
