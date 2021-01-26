package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.keinz.surviePlugin.rank.Rank;

public class GameMode implements CommandExecutor {
	private final Rank rank;
	
	public GameMode(Rank rank) {
		this.rank = rank;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (rank.hasPowerInf(player, 55)) {
				player.sendMessage("§f[SERVEUR] : §cVous n'avez pas la permission d'effectuer cette commande");
				return false;
			}
			
			if (args.length <= 0) {
				player.sendMessage("§f[SERVEUR] : §c/gm <gamemode> <player>");
				return false;
			}
			
			if (args.length == 1) {
				if (Integer.parseInt(args[0]) == 0) {
					player.setGameMode(org.bukkit.GameMode.SURVIVAL);
					player.sendMessage("§f[SERVEUR] : §aVous étes maintenant en survie !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 1) {
					player.setGameMode(org.bukkit.GameMode.CREATIVE);
					player.sendMessage("§f[SERVEUR] : §aVous étes maintenant en créatif !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 2) {
					player.setGameMode(org.bukkit.GameMode.ADVENTURE);
					player.sendMessage("§f[SERVEUR] : §aVous étes maintenant en aventure !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 3) {
					player.setGameMode(org.bukkit.GameMode.SPECTATOR);
					player.sendMessage("§f[SERVEUR] : §aVous étes maintenant en spectateur !");
					return false;
				}
			}
			
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (Integer.parseInt(args[0]) == 0) {
					target.setGameMode(org.bukkit.GameMode.SURVIVAL);
					player.sendMessage("§f[SERVEUR] : §c" + target.getName() + " §aest maintenant en survie !");
					target.sendMessage("§f[SERVEUR] : §aVous étes maintenant en survie !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 1) {
					target.setGameMode(org.bukkit.GameMode.CREATIVE);
					player.sendMessage("§f[SERVEUR] : §c" + target.getName() + " §aest maintenant en créatif !");
					target.sendMessage("§f[SERVEUR] : §aVous étes maintenant en créatif !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 2) {
					target.setGameMode(org.bukkit.GameMode.ADVENTURE);
					player.sendMessage("§f[SERVEUR] : §c" + target.getName() + " §aest maintenant en aventure !");
					target.sendMessage("§f[SERVEUR] : §aVous étes maintenant en aventure !");
					return false;
				}
				if (Integer.parseInt(args[0]) == 3) {
					target.setGameMode(org.bukkit.GameMode.SPECTATOR);
					player.sendMessage("§f[SERVEUR] : §c" + target.getName() + " §aest maintenant en spectateur !");
					target.sendMessage("§f[SERVEUR] : §aVous étes maintenant en spectateur !");
					return false;
				}
			}
			
			if (args.length >= 3) {
				player.sendMessage("§f[SERVEUR] : §c/gm <gamemode> <player>");
				return false;
			}
			
		}
		return false;
	}

}
