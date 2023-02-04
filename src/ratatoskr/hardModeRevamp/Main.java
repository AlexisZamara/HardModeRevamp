package ratatoskr.hardModeRevamp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import ratatoskr.hardModeRevamp.beds.PlayerRespawn;
import ratatoskr.hardModeRevamp.beds.TimeSkip;
import ratatoskr.hardModeRevamp.items.*;
import ratatoskr.hardModeRevamp.listeners.*;
import ratatoskr.hardModeRevamp.logger.Commands;
import ratatoskr.hardModeRevamp.logger.GriefLogging;
import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.mobs.CreatureSpawn;
import ratatoskr.hardModeRevamp.mobs.EntityChangeBlock;
import ratatoskr.hardModeRevamp.mobs.EntityDamageByEntity;
import ratatoskr.hardModeRevamp.mobs.EntityExplode;
import ratatoskr.hardModeRevamp.piglins.PiglinHandler;
import ratatoskr.hardModeRevamp.stamina.ConsumeFood;
import ratatoskr.hardModeRevamp.stamina.Stamina;
import ratatoskr.hardModeRevamp.villagers.PlayerInteractVillagerEvent;

public class Main extends JavaPlugin {
	public File griefLogFile;
	public File logsFile;
	
	public static Plugin getPlugin() {
		return JavaPlugin.getPlugin(Main.class);
	}
	
	@Override
	public void onEnable() {
		if(!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}
		this.saveDefaultConfig();
		createFile("grieflog.txt");
		createFile("rlogs.txt");
		
		if(this.getConfig().getBoolean("godapples.enabled")) {
			Recipes recipes = new Recipes(this);
			recipes.godAppleRestored();
		}
		
		this.getCommand("rlogs").setExecutor(new Commands());
		
		// TODO, replace all instances of "this.plugin = plugin" with "Plugin plugin = Main.getPlugin()";
		// TODO: break up the listeners based on what each module is supposed to do instead of shoving everything into one EventHandler
		getServer().getPluginManager().registerEvents(new BlockBroken(), this);
		getServer().getPluginManager().registerEvents(new BlockPlaced(), this);
		getServer().getPluginManager().registerEvents(new CreatureSpawn(), this);
		getServer().getPluginManager().registerEvents(new EntityChangeBlock(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new EntityExplode(), this);
		getServer().getPluginManager().registerEvents(new EntityToggleGlide(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		
		getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
		getServer().getPluginManager().registerEvents(new TimeSkip(), this);
		
		getServer().getPluginManager().registerEvents(new ItemEventHandler(), this);
		
		getServer().getPluginManager().registerEvents(new Stamina(), this);
		getServer().getPluginManager().registerEvents(new ConsumeFood(), this);
		
		getServer().getPluginManager().registerEvents(new PlayerInteractVillagerEvent(), this);
		getServer().getPluginManager().registerEvents(new PiglinHandler(), this);
		
		getServer().getPluginManager().registerEvents(new GriefLogging(), this);
		
		Logging.logError("Plugin startup completed", 0);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void createFile(String filename) {
		griefLogFile = new File(this.getDataFolder(), filename);
		if(!griefLogFile.exists()) {
			this.saveResource(filename, false);
		}
	}
	
	public static void log(String message, String filename) {
		try {
			File logFile = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), filename); 
					
			if(!logFile.exists()) {
				logFile.createNewFile();
			}
			
			FileWriter fw = new FileWriter(logFile, true);
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(message);
			pw.flush();
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
