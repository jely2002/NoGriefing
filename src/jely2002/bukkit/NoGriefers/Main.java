package jely2002.bukkit.NoGriefers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		PluginManager pm = getServer().getPluginManager();
		Permission BreakBlocks = new Permission("ng.breakblocks");
		Permission UseSigns = new Permission("ng.usesigns");
		Permission DropItems = new Permission("ng.dropitems");
		Permission PlaceBlocks = new Permission("ng.placeblocks");
		pm.addPermission(BreakBlocks);
		pm.addPermission(UseSigns);
		pm.addPermission(DropItems);
		pm.addPermission(PlaceBlocks);
		this.saveDefaultConfig();
	}

	@Override
	public void onDisable() {

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
					this.reloadConfig();
					p.sendMessage(ChatColor.GREEN +"Config succesfully reloaded!");
					return true;
				}

				if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "NoGriefers" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng help");
					p.sendMessage(ChatColor.GRAY + "Opens this help menu. ");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng sos");
					p.sendMessage(ChatColor.GRAY + "Activates emergency mode.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blockbreak <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable blockbreak.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng itemdrops <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable itemdrops.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng blockplace <true/false>");
					p.sendMessage(ChatColor.GRAY + "Enable or disable blockplace.");
					p.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "/ng version");
					p.sendMessage(ChatColor.GRAY + "Shows the version of the plugin.");
					p.sendMessage(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + " " + ChatColor.GREEN.toString() + ChatColor.BOLD + "By: Jely2002" + " " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "==========" + "");
					return true;
				}

				if (args[0].equalsIgnoreCase("version")) {
					p.sendMessage(ChatColor.GREEN + "NoGriefers is on version: " + ChatColor.YELLOW + getDescription().getVersion());
					return true;
				}
				
				if (args[0].equalsIgnoreCase("sos")) {
					this.getConfig().set("blockplace", false); 
					this.getConfig().set("blockbreak", false); 
					this.getConfig().set("itemdrops", false);
					this.saveConfig();
					p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Emergency mode activated. All privileges taken.");
					Bukkit.broadcastMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Emergency mode activated. Nobody can place, break or drop items.");
					return true;
				}
			}

			//	if (length == 2) {
			//		return false;
			///}

			if (length == 2) {
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
			}
			

		}
		return false;

	}
	





	@EventHandler
	public void blockbreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (getConfig().getBoolean("blockbreak") == false){
			if (p.hasPermission("BreakBlocks")) {
				return;
			} else {
				e.setCancelled(true);	
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message-blockbreak")));
			}
		} else return;
	}


	@EventHandler
	public void blockplace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
				if (getConfig().getBoolean("blockplace") == false  && !p.hasPermission("PlaceBlocks")) {
					e.setCancelled(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message-blockplace")));
				} else return;

			}

	@EventHandler
	public void dropitems(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (getConfig().getBoolean("itemdrops") == false && !p.hasPermission("ItemDrops")) {
				e.setCancelled(true);
			    p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("deny-message-itemdrops")));
       } else return;
	}
}


// Test (Hope this shows up on GitHub! XOXO)
