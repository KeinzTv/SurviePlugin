package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.keinz.surviePlugin.rank.Rank;
import fr.keinz.surviePlugin.utils.PlayerManager;

public class Admin implements CommandExecutor, Listener {
	private final Rank rank;
	
	public Admin(Rank rank) {
		this.rank = rank;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (rank.hasPowerInf(player, 75)) {
				player.sendMessage("§f[SERVEUR] : §cVous n'avez pas la permission d'effectuer cette commande");
				return false;
			}
			
			if(label.equalsIgnoreCase("admin")) {
	            if(PlayerManager.isInModerationMod(player)){
	                PlayerManager.getFromPlayer(player).destroy();
	            } else {
	                new PlayerManager(player).init();
	            }
			}
			
			if(label.equalsIgnoreCase("a")){ 
				if(PlayerManager.isInModerationMod(player)){
	                PlayerManager.getFromPlayer(player).destroy();
	            } else {
	                new PlayerManager(player).init();
	            }
	        }
			
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				player.sendMessage("§f[SERVEUR] : §c" + target.getName() + " §aest à présent en mode administrateur.");
				
				if(PlayerManager.isInModerationMod(target)){
	                PlayerManager.getFromPlayer(target).destroy();
	            } else {
	                new PlayerManager(target).init();
	            }
			}
			
			if (args.length >= 2) {
				player.sendMessage("§f[SERVEUR] : §c/admin or /a");
				return false;
			}
		}
		return false;
	}

}
