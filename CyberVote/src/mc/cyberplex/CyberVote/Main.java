package mc.cyberplex.CyberVote;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.vexsoftware.votifier.model.VotifierEvent;

import mc.cyberplex.CyberVote.Listeners.PlayerJoin;
import mc.cyberplex.CyberVote.Listeners.SignListeners;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener{

	private static Economy econ;

	private static Main main;
	public static Main getMain() {
		return main;
	}

	public void onEnable(){

		if(!setupEconomy()) {
			this.getLogger().severe("Disabled due to no vault depencency found");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		main = this;
		main.getConfig().options().copyDefaults(true);
		createConfig();
		main.saveConfig();

		setDate();

		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new SignListeners(), this);

		this.getCommand("vote").setExecutor(new Commands());

	}

	private boolean setupEconomy() {

		if(Bukkit.getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy>	rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if(rsp == null) {
			return false;
		}

		econ = rsp.getProvider();
		return econ != null;

	}

	public static Economy getEconomy() {
		return econ;
	}	

	private void setDate() {

		LocalDate date = LocalDate.now();

		//check to see if the previous date field is empty in the config and set previous and current date to the date
		if(main.getConfig().getString("Daily.date") == null) {

			main.getConfig().set("Daily.date", date.toString());
			main.saveConfig();

		} else {

			String previousDate = main.getConfig().getString("Daily.date");

			//check to see if previous date is different then current date and will set it to a new day if true
			if(!(previousDate.equalsIgnoreCase(date.toString()))) {

				main.getConfig().set("Daily.date", date.toString());
				main.getConfig().set("Daily.players", null);
				main.saveConfig();			

			}

		}

		if(main.getConfig().getString("Weekly.day") == null) {

			main.getConfig().set("Weekly.day", date.getDayOfWeek().toString());
			main.saveConfig();

		} else {

			String previousDay = main.getConfig().getString("Weekly.day");

			if(!(previousDay.equalsIgnoreCase(date.getDayOfWeek().toString()))) {

				if(previousDay.equalsIgnoreCase("saturday") && date.getDayOfWeek().toString().equalsIgnoreCase("sunday")) {

					TopPlayer topPlayer = new TopPlayer();
					String player = topPlayer.getTopWeekly(1);
					
					main.getConfig().set("Weekly.reward", player);
					main.getConfig().set("Weekly.players", null);

				}

				main.getConfig().set("Weekly.day", date.getDayOfWeek().toString());				
				main.saveConfig();

			}

		}

		if(main.getConfig().getString("Monthly.month") == null) {

			main.getConfig().set("Monthly.month", date.getMonth().toString());
			main.saveConfig();

		} else {

			String previousMonth = main.getConfig().getString("Monthly.month");

			if(!(previousMonth.equalsIgnoreCase(date.getMonth().toString()))) {

				TopPlayer topPlayer = new TopPlayer();
				String player = topPlayer.getTopMonthly(1);
				
				main.getConfig().set("Monthly.reward", player);

				main.getConfig().set("Monthly.month", date.getMonth().toString());
				main.getConfig().set("Monthly.players", null);
				main.saveConfig();

			}

		}

	}

	private void createConfig(){

		try {

			//Check to see theirs a folder
			if(!getDataFolder().exists()){
				getDataFolder().mkdirs();
			}

			//Create file 
			File configFile = new File(getDataFolder(), "config.yml");

			//Check if file doesn't exist
			if(!configFile.exists()){

				//Display error message to console
				getLogger().info("Config.yml not found, creating file");

				//create file
				saveDefaultConfig();

			} else {

				//Display message saying file was found
				getLogger().info("Config.yml found, loading file");

			}

		} catch (Exception except) {

			//display error message to console 
			except.printStackTrace();
		}

	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onVotifierEvent(VotifierEvent event){
		String username = event.getVote().getUsername();

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