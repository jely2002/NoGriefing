package jely2002.bukkit.NoGriefing;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Menu implements Listener {
	
	
	
	Main plugin;
	public Menu(Main instance) {
		plugin = instance;
	}

	public static Inventory settingsgui = Bukkit.createInventory(null, 45, ChatColor.RED.toString() + ChatColor.BOLD + "Anti-Grief settings");
	static {
		createDisplay(Material.GRASS, settingsgui, 10, "Block-place", "Click to enable or disable block place!");
		createDisplay(Material.DIAMOND_PICKAXE, settingsgui, 13, "Block-break", "Click to enable or disable block break!");
		createDisplay(Material.CHEST, settingsgui, 16, "Item-drops", "Click to enable or disable item drops!");
		createDisplay(Material.TNT, settingsgui, 31, "Anti-TNT", "Click to enable or disable Anti-TNT!");
	}

	public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(ChatColor.DARK_GRAY + lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);

		inv.setItem(Slot, item); 

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked(); 
		ItemStack clicked = e.getCurrentItem(); 
		Inventory inventory = e.getInventory(); 
		if (inventory.getName().equals(settingsgui.getName())) { 
			if (clicked.getItemMeta().getDisplayName().contains("Block-place")) { 
                if (plugin.getConfig().getBoolean("blockplace")) {
        			e.setCancelled(true); 
                	plugin.getConfig().set("blockplace", false);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Block-place has been set to false!");
                } else {
        			e.setCancelled(true); 
                	plugin.getConfig().set("blockplace", true);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Block-place has been set to true!");
                }
         			}
			if (clicked.getItemMeta().getDisplayName().contains("Block-break")) {
                if (plugin.getConfig().getBoolean("blockbreak")) {
        			e.setCancelled(true); 
                	plugin.getConfig().set("blockbreak", false);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Block-break has been set to false!");
                } else {
        			e.setCancelled(true); 
                	plugin.getConfig().set("blockbreak", true);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Block-break has been set to true!");
                }
         			}
			if (clicked.getItemMeta().getDisplayName().contains("Item-drops")) {
                if (plugin.getConfig().getBoolean("itemdrops")) {
        			e.setCancelled(true); 
                	plugin.getConfig().set("itemdrops", false);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Item-drops has been set to false!");
                } else {
        			e.setCancelled(true); 
                	plugin.getConfig().set("itemdrops", true);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Item-drops has been set to true!");
                }
         			}
			if (clicked.getItemMeta().getDisplayName().contains("Anti-TNT")) {
                if (plugin.getConfig().getBoolean("antitnt")) {
        			e.setCancelled(true); 
                	plugin.getConfig().set("antitnt", false);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Anti-TNT has been set to false!");
                } else {
        			e.setCancelled(true); 
                	plugin.getConfig().set("antitnt", true);
                	plugin.saveConfig();
                	p.sendMessage(ChatColor.GREEN + "Anti-TNT has been set to true!");
                }
         			}
		}
	}
}