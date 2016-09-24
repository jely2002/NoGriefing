import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jely2002.bukkit.NoGriefing.Updater.UpdateResult;
import jely2002.bukkit.NoGriefing.Updater.UpdateType;
import jely2002.bukkit.NoGriefing.Updater;

public class Main extends JavaPlugin {

	public boolean uptodate;
	private Menu menu;
	public HashMap<OfflinePlayer, String> logger = new HashMap<OfflinePlayer, String>();
	public List<String> loggedblocks = (List<String>) this.getConfig().getStringList("blocks-to-be-logged");
	Listener listener = new EventListener(this);

	@Override
	public void onEnable() {
		menu = new Menu(this);
		Bukkit.getPluginManager().registerEvents(listener, this);
		Bukkit.getPluginManager().registerEvents(menu, this);
		PluginManager pm = getServer().getPluginManager();
		Permission BreakBlocks = new Permission("ng.breakblocks");
		Permission DropItems = new Permission("ng.dropitems");
		Permission PlaceBlocks = new Permission("ng.placeblocks");
		Permission ChangeState = new Permission("ng.setflags");
		Permission adddisabled = new Permission("ng.adddisabledworlds");
		Permission Hunger = new Permission("ng.hunger");
		Permission FallDamage = new Permission("ng.falldamage");
		Permission Fire = new Permission("ng.fire");
		Permission Drowning = new Permission("ng.drowning");
		Permission Reload = new Permission("ng.reload");
		Permission Blocklog = new Permission("ng.viewblocklog");
		Permission DelBlocklog = new Permission("ng.delblocklog");
		Permission use = new Permission("ng.openinventory");
		Permission PvP = new Permission("ng.pvp");
		Permission ItemPickup = new Permission("ng.itempickup");
		Permission toolUse = new Permission("ng.use");
		pm.addPermission(BreakBlocks);
		pm.addPermission(use);
		pm.addPermission(toolUse);
		pm.addPermission(DropItems);
		pm.addPermission(ItemPickup);
		pm.addPermission(PlaceBlocks);
		pm.addPermission(ChangeState);
		pm.addPermission(adddisabled); 
		pm.addPermission(Reload);
		pm.addPermission(Blocklog);
		pm.addPermission(DelBlocklog);
		pm.addPermission(PvP);
		pm.addPermission(Hunger);
		pm.addPermission(FallDamage);
		pm.addPermission(Fire);
		pm.addPermission(Drowning);
		FileConfiguration config = this.getConfig();
		config.options().copyDefaults(true);
		this.saveConfig();
		getLogger().info("--------------+ NoGriefing +---------------");
		getLogger().info("       NoGriefing is succesfully enabled!  ");
		getLogger().info("        Thank you for using NoGriefing!    ");
		getLogger().info("       To see the commands do /ng help     ");
		getLogger().info("         By: Jely2002 - Open source!       ");
		getLogger().info("--------------+ NoGriefing +---------------");

		//Start metrics
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			getLogger().warning("Could not connect to MCStats.com");
		}

		//Start updater
		if (getConfig().getBoolean("update-check")) {
			Updater updatechecker = new Updater(this, 100722, getFile(), UpdateType.NO_DOWNLOAD, true);
			if (getConfig().getBoolean("update-check"))
			if (updatechecker.getResult().equals(UpdateResult.UPDATE_AVAILABLE)) {
				getLogger().info("There is an update available!");
				getLogger().info("Download it now at the plugin page:");
				getLogger().info("http://dev.bukkit.org/bukkit-plugins/antigrief-nogriefers/");
				uptodate = false;
			} else {
				getLogger().info("You are running the latest version of NoGriefing!");
				uptodate = true;
			}
		}

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
		getLogger().info("---+ Thanks for using NoGriefing - Succesfully disabled +---");
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
						this.saveConfig();
						p.sendMessage(ChatColor.GREEN +"Config succesfully reloaded!");
					} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
					return true;
				}

				if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "NoGriefing" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng help");
					p.sendMessage(ChatColor.GRAY + "Opens this help menu. ");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng settings");
					p.sendMessage(ChatColor.GRAY + "Opens a GUI where you can enable or disable all of the anti-grief features.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blocklog");
					p.sendMessage(ChatColor.GRAY + "Shows the blocklog.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng adddisabled");
					p.sendMessage(ChatColor.GRAY + "Adds the world your in to the disabled worlds list.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng version");
					p.sendMessage(ChatColor.GRAY + "Shows the version of the plugin.");
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "By: Jely2002" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					return true;
				}

				if (args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.GREEN + "NoGriefing is on version: " + ChatColor.YELLOW + getDescription().getVersion());
					return true;
				}

				if (args[0].equalsIgnoreCase("blocklog")) {
					if (p.hasPermission("Blocklog")) {
						if (getConfig().getBoolean("enable-blocklog")) {
							for(int i = 0; i < getConfig().getStringList("blocklog").size() ;  i++)	 {
								p.sendMessage(ChatColor.GOLD + getConfig().getStringList("blocklog").get(i));
								p.sendMessage(ChatColor.GREEN + "Blocklog can be emptied using: /ng delblocklog");
							}
						} else p.sendMessage(ChatColor.RED + "To use this feature, set enable-blocklog in the config to true.");
					} else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
					return true;
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
					}  else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
					return true;
				}

				if (args[0].equalsIgnoreCase("settings")) {
					if (p.hasPermission("ChangeState")) {
						p.openInventory(Menu.settingsgui);
					}  else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
					return true;
				}
				
				if (args[0].equalsIgnoreCase("adddisabled")) {
					if (p.hasPermission("adddisabled")) {
						List<String> disabledworlds = (List<String>) this.getConfig().getStringList("disabled-worlds");
						disabledworlds.add(p.getWorld().getName());
						getConfig().set("disabled-worlds", disabledworlds);
						p.sendMessage(ChatColor.GREEN + "Added the world" + p.getWorld().getName() + "to the disabled worlds!");
					}  else p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("no-permission-message")));
					return true;
				}
			}
		}
		return false;

	}
