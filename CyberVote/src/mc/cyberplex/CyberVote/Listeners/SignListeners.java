package mc.cyberplex.CyberVote.Listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import mc.cyberplex.CyberVote.Main;
import mc.cyberplex.CyberVote.TopPlayer;
import net.md_5.bungee.api.ChatColor;

public class SignListeners implements Listener{

	Main main = Main.getMain();
	TopPlayer topPlayer = new TopPlayer();

	@EventHandler
	public void onSignCreate(SignChangeEvent event) {

		Player player = event.getPlayer();

		if(player.hasPermission("cybervote.sign")) {

			if(event.getLine(0).equalsIgnoreCase("[vote]")) {

				if(event.getLine(1).equalsIgnoreCase("daily")) {

					int index = Integer.parseInt(event.getLine(2));

					Block block = event.getBlock();

					--index;
					
					main.getConfig().set("Daily.sign." + index + ".world", block.getWorld().getName());
					main.getConfig().set("Daily.sign." + index + ".x", block.getX());
					main.getConfig().set("Daily.sign." + index + ".y", block.getY());
					main.getConfig().set("Daily.sign." + index + ".z", block.getZ());
					main.saveConfig();
					
					
					
					String username = topPlayer.getTopDaily(index+1);
					int voteCount = 0;
					if(username != "") {
						voteCount = main.getConfig().getInt("Daily.players." + username);
					}
					
					event.setLine(0, ChatColor.GREEN + "[Daily Vote]");
					event.setLine(1, ChatColor.GRAY + "Rank: " + (index+1));
					event.setLine(2, ChatColor.BLUE + username);
					event.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
					
					player.sendMessage(ChatColor.GREEN + "Vote sign created");
					
					updateSigns();

				} else if(event.getLine(1).equalsIgnoreCase("weekly")) {

					int index = Integer.parseInt(event.getLine(2));

					Block block = event.getBlock();
					
					--index;

					main.getConfig().set("Weekly.sign." + index + ".world", block.getWorld().getName());
					main.getConfig().set("Weekly.sign." + index + ".x", block.getX());
					main.getConfig().set("Weekly.sign." + index + ".y", block.getY());
					main.getConfig().set("Weekly.sign." + index + ".z", block.getZ());
					main.saveConfig();
					
					String username = topPlayer.getTopWeekly(index+1);
					int voteCount = 0;
					if(username != "") {
						voteCount = main.getConfig().getInt("Weekly.players." + username);
					}
					
					event.setLine(0, ChatColor.GREEN + "[Weekly Vote]");
					event.setLine(1, ChatColor.GRAY + "Rank: " + (index+1));
					event.setLine(2, ChatColor.BLUE + username);
					event.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
					
					player.sendMessage(ChatColor.GREEN + "Vote sign created");
					
					updateSigns();

				} else if(event.getLine(1).equalsIgnoreCase("monthly")) {

					int index = Integer.parseInt(event.getLine(2));

					Block block = event.getBlock();
					
					--index;

					main.getConfig().set("Monthly.sign." + index + ".world", block.getWorld().getName());
					main.getConfig().set("Monthly.sign." + index + ".x", block.getX());
					main.getConfig().set("Monthly.sign." + index + ".y", block.getY());
					main.getConfig().set("Monthly.sign." + index + ".z", block.getZ());
					main.saveConfig();
					
					String username = topPlayer.getTopMonthly(index+1);
					int voteCount = 0;
					if(username != "") {
						voteCount = main.getConfig().getInt("Monthly.players." + username);
					}
					
					event.setLine(0, ChatColor.GREEN + "[Monthly Vote]");
					event.setLine(1, ChatColor.GRAY + "Rank: " + (index+1));
					event.setLine(2, ChatColor.BLUE + username);
					event.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
					
					player.sendMessage(ChatColor.GREEN + "Vote sign created");
					
					updateSigns();

				}

			}

		}

	}

	public void updateSigns() {

		Set<String> dailySigns = null;
		Set<String> weeklySigns = null;
		Set<String> monthlySigns = null;
		
		Location signLocation;
		Block block;
		BlockState state;
		Sign sign;
		
		if(main.getConfig().getConfigurationSection("Daily.sign") != null) {			
			dailySigns = main.getConfig().getConfigurationSection("Daily.sign").getKeys(false);	
			
			for(int i = 0; i < dailySigns.size(); i++) {
				
				float x = main.getConfig().getInt("Daily.sign." + i + ".x"),
						y = main.getConfig().getInt("Daily.sign." + i + ".y"),
						z = main.getConfig().getInt("Daily.sign." + i + ".z");
				String world = main.getConfig().getString("Daily.sign." + i + ".world");			
				signLocation = new Location(Bukkit.getServer().getWorld(world), x, y, z);
				block = signLocation.getWorld().getBlockAt(signLocation);
				state = block.getState();
				sign = (Sign) state;
				
				String username = topPlayer.getTopDaily(i+1);
				int voteCount = 0;
				if(username != "") {
					voteCount = main.getConfig().getInt("Daily.players." + username);
				}
				
				sign.setLine(0, ChatColor.GREEN + "[Daily Vote]");
				sign.setLine(1, ChatColor.GRAY + "Rank: " + (i+1));
				sign.setLine(2, ChatColor.BLUE + username);
				sign.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
				sign.update();
				
			}
			
		}
		
		if(main.getConfig().getConfigurationSection("Weekly.sign") != null) {			
			weeklySigns = main.getConfig().getConfigurationSection("Weekly.sign").getKeys(false);
			
			for(int i = 0; i < weeklySigns.size(); i++) {
				
				float x = main.getConfig().getInt("Weekly.sign." + i + ".x"),
						y = main.getConfig().getInt("Weekly.sign." + i + ".y"),
						z = main.getConfig().getInt("Weekly.sign." + i + ".z");
				String world = main.getConfig().getString("Weekly.sign." + i + ".world");			
				signLocation = new Location(Bukkit.getServer().getWorld(world), x, y, z);
				block = signLocation.getWorld().getBlockAt(signLocation);
				state = block.getState();
				sign = (Sign) state;
				
				String username = topPlayer.getTopWeekly(i+1);
				int voteCount = 0;
				if(username != "") {
					voteCount = main.getConfig().getInt("Weekly.players." + username);
				}
				
				sign.setLine(0, ChatColor.GREEN + "[Weekly Vote]");
				sign.setLine(1, ChatColor.GRAY + "Rank: " + (i+1));
				sign.setLine(2, ChatColor.BLUE + username);
				sign.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
				sign.update();
				
			}
			
		}
		
		if(main.getConfig().getConfigurationSection("Monthly.sign") != null) {			
			monthlySigns = main.getConfig().getConfigurationSection("Monthly.sign").getKeys(false);	
			
			for(int i = 0; i < monthlySigns.size(); i++) {
				
				float x = main.getConfig().getInt("Monthly.sign." + i + ".x"),
						y = main.getConfig().getInt("Monthly.sign." + i + ".y"),
						z = main.getConfig().getInt("Monthly.sign." + i + ".z");
				String world = main.getConfig().getString("Monthly.sign." + i + ".world");			
				signLocation = new Location(Bukkit.getServer().getWorld(world), x, y, z);
				block = signLocation.getWorld().getBlockAt(signLocation);
				state = block.getState();
				sign = (Sign) state;
				
				String username = topPlayer.getTopMonthly(i+1);
				int voteCount = 0;
				if(username != "") {
					voteCount = main.getConfig().getInt("Monthly.players." + username);
				}
				
				sign.setLine(0, ChatColor.GREEN + "[Monthly Vote]");
				sign.setLine(1, ChatColor.GRAY + "Rank: " + (i+1));
				sign.setLine(2, ChatColor.BLUE + username);
				sign.setLine(3, ChatColor.GRAY + "Votes: " + voteCount);
				sign.update();
				
			}
			
		}		
		
	}

}
