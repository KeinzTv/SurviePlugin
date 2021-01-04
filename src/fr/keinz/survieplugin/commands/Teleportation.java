package fr.keinz.survieplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Teleportation implements CommandExecutor {

	private HashMap<Player, ArrayList<Player>> teleportationRequestMap = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof  Player){
			final Player player = (Player)sender;
			if(label.equalsIgnoreCase("tpa")) return tpa(player, args);
			if(label.equalsIgnoreCase("tpaccept") || label.equalsIgnoreCase("tpyes")) return tpaccept(player, args);
			if(label.equalsIgnoreCase("tpdeny") || label.equalsIgnoreCase("tpno")) return tpdeny(player, args);
		}
		return false;
	}

	private boolean tpa(Player player, String[] args){
		if(args.length != 1){
			player.sendMessage("§cVeuillez saisir le pseudo d'un joueur !");
			return false;
		}
		if (player.isFlying()) {
			player.setFlying(false);
		}

		final Player target = Bukkit.getPlayer(args[0]);
		if(target != null){
		    if(target == player) player.sendMessage("§cT'as cru j'allais laisser un bug sur mon plugin.");
			target.sendMessage("§2" + player.getDisplayName() + " §aveut se téléporter à votre position.\n" +
					"Pour accepter la demande : §2/tpaccept §aou §2/tpyes.\n" +
					"§aPour refuser la demande : §2/tpdeny §aou §2/tpno.");

			if(this.teleportationRequestMap.containsKey(target)) this.teleportationRequestMap.get(target).add(player);
			else {
				ArrayList<Player> array = new ArrayList<>();
				array.add(player);
				this.teleportationRequestMap.put(target, array);
			}
		} else {
			player.sendMessage("§cCe joueur n'existe pas ou n'est pas connecté !");
		}

		return false;
	}

	private boolean tpaccept(Player player, String[] args){
		if(this.teleportationRequestMap.containsKey(player)){
			if(this.teleportationRequestMap.get(player).size() >= 1){
				if(args.length == 1){
					for (Player requester : this.teleportationRequestMap.get(player)) {
						if(requester == Bukkit.getPlayer(args[0])){
							player.sendMessage("§f[SERVEUR] : §2" + requester.getDisplayName() + " §aest en cours de téléportation.");
							requester.teleport(requester.getLocation());
							requester.sendMessage("§f[SERVEUR] : §2" + player.getDisplayName() + " §aest en cours de téléportation.");
							return true;
						}
					}
					player.sendMessage("§cImpossible de trouver une demande de téléportation de ce joueur.");
					return false;
				}
				else {
					final Player requester = this.teleportationRequestMap.get(player).get(0);
					player.sendMessage("§f[SERVEUR] : §2" + requester.getDisplayName() + " §aest en cours de téléportation..");
					requester.teleport(requester.getLocation());
					requester.sendMessage("§f[SERVEUR] : §aVous venez d'être téléporté à §a" + player.getDisplayName() + ".");
					return true;
				}
			}
		}
		player.sendMessage("§cVous n'avez pas de demande de téléportation.");
		return false;
	}

	private boolean tpdeny(Player player, String[] args){
		if(this.teleportationRequestMap.containsKey(player)){
			if(this.teleportationRequestMap.get(player).size() >= 1) {
				if(args.length == 1){
					for (Player requester : this.teleportationRequestMap.get(player)) {
						if(requester == Bukkit.getPlayer(args[0])){
							player.sendMessage("§f[SERVEUR] : §aVous avez refusé la demande de téléportation de §2" + requester.getDisplayName() + ".");
							requester.sendMessage("§f[SERVEUR] : §2" + player.getDisplayName() + " §aa refusé votre demande de téléportation.");
							return true;
						}
					}
					player.sendMessage("§cImpossible de trouver une demande de téléportation de ce joueur.");
					return false;
				}
				else {
					final Player requester = this.teleportationRequestMap.get(player).get(0);
					player.sendMessage("§f[SERVEUR] : §aVous avez refusé la demande de téléportation de §2" + requester.getDisplayName() + ".");
					requester.sendMessage("§f[SERVEUR] : §2" + player.getDisplayName() + " §aa refusé votre demande de téléportation.");
					return true;
				}
			}
		}
		player.sendMessage("§cVous n'avez pas de demande de téléportation.");
		return false;
	}
	
}
