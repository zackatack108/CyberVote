package mc.cyberplex.CyberVote.Listeners;


import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import mc.cyberplex.CyberVote.Main;
import net.milkbowl.vault.economy.Economy;

public class PlayerJoin implements Listener {

	Main main = Main.getMain();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		if(main != null) {

			List<String> offlinePlayers =  main.getConfig().getStringList("Players");

			if(offlinePlayers.contains(event.getPlayer().getName())) {

				Economy economy = Main.getEconomy();

				do {

					economy.depositPlayer(event.getPlayer().getName(), 1000);
					Bukkit.getPlayer(event.getPlayer().getName()).sendMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.BLUE + "You've been awarded $1000, thanks for voting.");

					offlinePlayers.remove(event.getPlayer().getName());
					main.getConfig().set("Players", offlinePlayers);
					main.saveConfig();

				} while (offlinePlayers.contains(event.getPlayer().getName()));

			}
			
			if(main.getConfig().getString("Weekly.reward") != null) {
				
				if(main.getConfig().getString("Weekly.reward").equalsIgnoreCase(event.getPlayer().getName())) {

					Economy economy = Main.getEconomy();
					
					economy.depositPlayer(event.getPlayer().getName(), 5000);
					Bukkit.getPlayer(event.getPlayer().getName()).sendMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.BLUE + "You've been awarded $5000 for being the top weekly voter. Thanks for voting.");

					main.getConfig().set("Weekly.reward", null);
					main.saveConfig();
					
				}
				
			}
			
			if(main.getConfig().getString("Monthly.reward") != null) {
				
				if(main.getConfig().getString("Monthly.reward").equalsIgnoreCase(event.getPlayer().getName())) {

					Economy economy = Main.getEconomy();
					
					economy.depositPlayer(event.getPlayer().getName(), 125000);
					Bukkit.getPlayer(event.getPlayer().getName()).sendMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.BLUE + "You've been awarded $125000 for being the top monthly voter. Thanks for voting.");

					main.getConfig().set("Monthly.reward", null);
					main.saveConfig();
					
				}
				
			}			

		}

	}

}
