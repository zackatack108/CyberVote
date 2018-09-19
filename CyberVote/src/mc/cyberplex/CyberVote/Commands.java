package mc.cyberplex.CyberVote;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.cyberplex.CyberVote.Listeners.SignListeners;
import net.milkbowl.vault.economy.Economy;

public class Commands implements CommandExecutor {

	Main main = Main.getMain();
	TopPlayer topPlayer = new TopPlayer();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

		if(cmd.getName().equalsIgnoreCase("vote")) {

			Player player = (Player) sender;

			if(args.length >= 1) {

				String subCmd = args[0].toLowerCase();

				switch(subCmd) {

				case "help":

					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
					player.sendMessage(ChatColor.DARK_AQUA +  "Cyber Vote help");
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
					player.sendMessage(ChatColor.YELLOW + "/vote help: " + ChatColor.GRAY + "Display commands for cyber vote");
					player.sendMessage(ChatColor.YELLOW + "/vote top daily: " + ChatColor.GRAY + "Display the top voters for that day");
					player.sendMessage(ChatColor.YELLOW + "/vote top weekly: " + ChatColor.GRAY + "Display the top voters for that week");
					player.sendMessage(ChatColor.YELLOW + "/vote top monthly: " + ChatColor.GRAY + "Display the top voters for that month");
					player.sendMessage(ChatColor.YELLOW + "/vote link add [link]: " + ChatColor.GRAY + "Add a link to vote for the server");
					player.sendMessage(ChatColor.YELLOW + "/vote link remove [number]: " + ChatColor.GRAY + "Remove a link");
					player.sendMessage(ChatColor.YELLOW + "/vote reload: " + ChatColor.GRAY + "Reload config for plugin");
					player.sendMessage(ChatColor.YELLOW + "/vote: " + ChatColor.GRAY + "Display voting links");
					player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

					break;

				case "top":

					if(args.length == 2) {

						String topType = args[1].toLowerCase();

						if(topType.equalsIgnoreCase("daily")) {

							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Top Voters Today");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "1. " + topPlayer.getTopDaily(1));
							player.sendMessage(ChatColor.YELLOW + "2. " + topPlayer.getTopDaily(2));
							player.sendMessage(ChatColor.YELLOW + "3. " + topPlayer.getTopDaily(3));
							player.sendMessage(ChatColor.YELLOW + "4. " + topPlayer.getTopDaily(4));
							player.sendMessage(ChatColor.YELLOW + "5. " + topPlayer.getTopDaily(5));
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						} else if(topType.equalsIgnoreCase("weekly")) {

							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Top Weekly Voters");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "1. " + topPlayer.getTopWeekly(1));
							player.sendMessage(ChatColor.YELLOW + "2. " + topPlayer.getTopWeekly(2));
							player.sendMessage(ChatColor.YELLOW + "3. " + topPlayer.getTopWeekly(3));
							player.sendMessage(ChatColor.YELLOW + "4. " + topPlayer.getTopWeekly(4));
							player.sendMessage(ChatColor.YELLOW + "5. " + topPlayer.getTopWeekly(5));
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						} else if(topType.equalsIgnoreCase("monthly")) {

							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.DARK_AQUA +  "Top Monthly Voters");
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
							player.sendMessage(ChatColor.YELLOW + "1. " + topPlayer.getTopMonthly(1));
							player.sendMessage(ChatColor.YELLOW + "2. " + topPlayer.getTopMonthly(2));
							player.sendMessage(ChatColor.YELLOW + "3. " + topPlayer.getTopMonthly(3));
							player.sendMessage(ChatColor.YELLOW + "4. " + topPlayer.getTopMonthly(4));
							player.sendMessage(ChatColor.YELLOW + "5. " + topPlayer.getTopMonthly(5));
							player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

						} else {
							
							player.sendMessage(ChatColor.RED + "Sorry, invalid command");
							player.sendMessage(ChatColor.RED + "Please do /vote help");
							
						}

					} else {
						
						player.sendMessage(ChatColor.RED + "Sorry, invalid command");
						player.sendMessage(ChatColor.RED + "Please do /vote help");
						
					}

					break;

				case "link":

					if(args.length == 3) {

						if(player.hasPermission("cybervote.link")) {

							if(args[1].toLowerCase().equalsIgnoreCase("add")) {

								String voteLink = args[2];

								List<String> linkList = main.getConfig().getStringList("Vote links");
								linkList.add(voteLink);

								main.getConfig().set("Vote links", linkList);
								main.saveConfig();

								player.sendMessage(ChatColor.GREEN + "Link added");

							} else if(args[1].toLowerCase().equalsIgnoreCase("remove")) {

								int index = Integer.parseInt(args[2]);

								List<String> linkList = main.getConfig().getStringList("Vote links");
								linkList.remove(index-1);

								main.getConfig().set("Vote links", linkList);
								main.saveConfig();

								player.sendMessage(ChatColor.RED + "Link removed");

							}

						} else {
							
							player.sendMessage(ChatColor.RED + "Sorry, you don't have permission to use this command");
							
						}

					}

					break;

				case "reload":

					if(player.hasPermission("cybervote.reload")) {
						
						main.reloadConfig();
						main.saveConfig();
						
						player.sendMessage(ChatColor.GREEN + "Cyber Vote config reloaded");
						
					} else {
						
						player.sendMessage(ChatColor.RED + "Sorry, you don't have permission to use this command");
						
					}

					break;
					
				/*default:
					testVoteCmd(args[0]);
					break;*/
					
				}

			} else {

				List<String> linkList = main.getConfig().getStringList("Vote links");

				player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
				player.sendMessage(ChatColor.DARK_AQUA +  "Vote Links");
				player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");

				for(int i = 0; i < linkList.size(); i++) {					
					player.sendMessage(ChatColor.WHITE + Integer.toString(i+1) + ": " + ChatColor.YELLOW + linkList.get(i));					
				}

				player.sendMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");			

			}

		}

		return false;
	}
	
	@SuppressWarnings("deprecation")
	public void testVoteCmd(String username) {

		if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(username))) {

			Economy economy = Main.getEconomy();					

			Bukkit.getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.GOLD + username + ChatColor.BLUE + " has voted for the server.");
			economy.depositPlayer(username, 1000);
			Bukkit.getPlayer(username).sendMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.BLUE + "You've been awarded $1000, thanks for voting.");

		} else {

			Bukkit.getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.YELLOW + "Cyber Vote" + ChatColor.WHITE + "] " + ChatColor.GOLD + username + ChatColor.BLUE + " has voted for the server.");

			List<String> offlinePlayers = main.getConfig().getStringList("Players");					
			offlinePlayers.add(username);

			main.getConfig().set("Players", offlinePlayers);					
			main.saveConfig();

		}

		if(main.getConfig().contains("Daily.players." + username)) {

			int voteNum = main.getConfig().getInt("Daily.players." + username);
			main.getConfig().set("Daily.players." + username, ++voteNum);
			main.saveConfig();

		} else {

			main.getConfig().set("Daily.players." + username, 1);
			main.saveConfig();

		}

		if(main.getConfig().contains("Weekly.players." + username)) {

			int voteNum = main.getConfig().getInt("Weekly.players." + username);
			main.getConfig().set("Weekly.players." + username, ++voteNum);
			main.saveConfig();

		} else {

			main.getConfig().set("Weekly.players." + username, 1);
			main.saveConfig();

		}

		if(main.getConfig().contains("Monthly.players." + username)) {

			int voteNum = main.getConfig().getInt("Monthly.players." + username);
			main.getConfig().set("Monthly.players." + username, ++voteNum);
			main.saveConfig();

		} else {

			main.getConfig().set("Monthly.players." + username, 1);
			main.saveConfig();

		}
		
		SignListeners signs = new SignListeners();
		signs.updateSigns();

	}

}
