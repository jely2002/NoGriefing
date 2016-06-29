package jely2002.bukkit.NoGriefing;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public HashMap<OfflinePlayer, String> logger = new HashMap<OfflinePlayer, String>();
	public List<String> loggedblocks = (List<String>) this.getConfig().getStringList("blocks-to-be-logged");
	Listener listener = new EventListener(this);



	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(listener, this);
		PluginManager pm = getServer().getPluginManager();
		Permission BreakBlocks = new Permission("ng.breakblocks");
		Permission DropItems = new Permission("ng.dropitems");
		Permission PlaceBlocks = new Permission("ng.placeblocks");
		Permission ChangeState = new Permission("ng.setflags");
		Permission SosMode = new Permission("ng.sosmode");
		Permission Reload = new Permission("ng.reload");
		Permission Blocklog = new Permission("ng.viewblocklog");
		Permission DelBlocklog = new Permission("ng.delblocklog");
		pm.addPermission(BreakBlocks);
		pm.addPermission(DropItems);
		pm.addPermission(PlaceBlocks);
		pm.addPermission(ChangeState);
		pm.addPermission(SosMode); 
		pm.addPermission(Reload);
		pm.addPermission(Blocklog);
		pm.addPermission(DelBlocklog);
		this.saveDefaultConfig();
		getLogger().info("--------------+ NoGriefing +---------------");
		getLogger().info("       NoGriefing is succesfully enabled!  ");
		getLogger().info("        Thank you for using NoGriefing!    ");
		getLogger().info("       To see the commands do /ng help     ");
		getLogger().info("         By: Jely2002 - Open source!       ");
		getLogger().info("--------------+ NoGriefing +---------------");
//		configversion(); Future add.


		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				List<String> blocklist = getConfig().getStringList("blocklog");
				for (OfflinePlayer p : logger.keySet()) {
					blocklist.add(p.getName() + ": " + logger.get(p).toString());
				}
				getConfig().set("blocklog", blocklist);
				saveConfig();
				logger.clear();

			}
		}, 10*20, getConfig().getInt("blocklog-save-interval")*20);			
	}

	@Override
	public void onDisable() {
		List<String> blocklist = getConfig().getStringList("blocklog");
		for (OfflinePlayer p : logger.keySet()) {
			blocklist.add(p.getName() + ": " + logger.get(p).toString());
		}
		getConfig().set("blocklog", blocklist);
		saveConfig();
		getLogger().info("---------+ Thanks for using NoGriefing - Succesfully disabled +---------");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("ng") && sender instanceof Player) {

			int length = args.length;
			Player p = (Player) sender;

			if (length == 0) {
				return false;			
			}

			if (length == 1) {

				if (args[0].equalsIgnoreCase("reload")) {
					if (p.hasPermission("Reload")) {
						this.reloadConfig();
						p.sendMessage(ChatColor.GREEN +"Config succesfully reloaded!");
						return true;
					} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
				}

				if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "NoGriefing" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng help");
					p.sendMessage(ChatColor.GRAY + "Opens this help menu. ");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng sos");
					p.sendMessage(ChatColor.GRAY + "Activates emergency mode.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blockbreak <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable blockbreak.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng itemdrops <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable itemdrops.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blockplace <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable anti-TNT.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng antitnt <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable blockplace.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blocklogg");
					p.sendMessage(ChatColor.GRAY + "Shows the blocklog.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng version");
					p.sendMessage(ChatColor.GRAY + "Shows the version of the plugin.");
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "By: Jely2002" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					return true;
				}

				if (args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.GREEN + "NoGriefing is on version: " + ChatColor.YELLOW + getDescription().getVersion());
					return true;
				}

				if (args[0].equalsIgnoreCase("sos")) {
					if (p.hasPermission("SosMode")) {
						this.getConfig().set("blockplace", false); 
						this.getConfig().set("blockbreak", false); 
						this.getConfig().set("itemdrops", false);
						this.getConfig().set("antitnt", true);
						this.saveConfig();
						p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Emergency mode activated. All privileges taken.");
						Bukkit.broadcastMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Emergency mode activated. Nobody can place, break or drop items.");
						return true;
					} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
				}

				if (args[0].equalsIgnoreCase("blocklog")) {
				if (p.hasPermission("Blocklog")) {
					if (getConfig().getBoolean("enable-blocklog")) {
						for(int i = 0; i < getConfig().getStringList("blocklog").size() ;  i++)	 {
								p.sendMessage(ChatColor.GOLD + getConfig().getStringList("blocklog").get(i));
							p.sendMessage(ChatColor.GREEN + "Blocklog can be emptied using: /ng delblocklog");
						}
					} else p.sendMessage(ChatColor.RED + "To use this feature, set enable-blocklog in the config to true.");
					return true;
				} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
				}

				if (args[0].equalsIgnoreCase("delblocklog")) {
				 if (p.hasPermission("DelBlocklog")) {	
					if (getConfig().getBoolean("enable-blocklog")) {
						loggedblocks.clear();
						logger.clear();
						getConfig().set("blocklog", null);
						saveConfig();
						p.sendMessage(ChatColor.GREEN + "Blocklog cleared!");
					} else p.sendMessage(ChatColor.RED + "To use this feature, set enable-blocklog in the config to true.");
					return true;
				}  else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
				}
			}

			if (length == 2) {
				if (p.hasPermission("ChangeState")) {	
					if (args[0].equalsIgnoreCase("blockbreak") && args[1].equalsIgnoreCase("true") ) {
						this.getConfig().set("blockbreak", true); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Blockbreak set to true.");
						return true;
					}

					if (args[0].equalsIgnoreCase("blockbreak") && args[1].equalsIgnoreCase("false")) {
						this.getConfig().set("blockbreak", false); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Blockbreak set to false.");
						return true;	
					}

					if (args[0].equalsIgnoreCase("itemdrops") && args[1].equalsIgnoreCase("true")) {
						this.getConfig().set("itemdrops", true); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Itemdrops set to true.");
						return true;
					}

					if (args[0].equalsIgnoreCase("itemdrops") && args[1].equalsIgnoreCase("false")) {
						this.getConfig().set("itemdrops", false); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Itemdrops set to false.");
						return true;	
					}

					if (args[0].equalsIgnoreCase("blockplace") && args[1].equalsIgnoreCase("true")) {
						this.getConfig().set("blockplace", true); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Blockplace set to true.");
						return true;
					}

					if (args[0].equalsIgnoreCase("blockplace") && args[1].equalsIgnoreCase("false")) {
						this.getConfig().set("blockplace", false); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Blockplace set to false.");
						return true;	
					}

					if (args[0].equalsIgnoreCase("antitnt") && args[1].equalsIgnoreCase("true")) {
						this.getConfig().set("antitnt", true); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Anti TNT set to true.");
						return true;
					}

					if (args[0].equalsIgnoreCase("antitnt") && args[1].equalsIgnoreCase("false")) {
						this.getConfig().set("antitnt", false); 
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN + "Anti TNT set to false.");
						return true;	
					}
				} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
			}


		}
		return false;

	}
	
//	public void configversion() {
//		if (getConfig().getString("config-version") ==  getDescription().getVersion()) {
//			return;
//		} else {
//			getConfig().options().copyDefaults(true);
//			getConfig().set("config-version", getDescription().getVersion());
//		    getLogger().warning("A new version of the plugin was found! Resetting the config.");			
//		}
//	}

}


// Test (Hope this shows up on GitHub! XOXO)