package fr.keinz.surviePlugin.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.keinz.surviePlugin.SurviePlugin;

public class Teleportation implements CommandExecutor {

	private HashMap<Player, ArrayList<Player>> teleportationRequestMap = SurviePlugin.getInstance().teleportationRequestMap;
	
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
		    else {
				if(this.teleportationRequestMap.get(target) != null){
					if(this.teleportationRequestMap.get(target).contains(player)) {
						player.sendMessage("§cTu as déjà  envoyé une demande de téléportation à ce joueur.");
						return false;
					}
					this.teleportationRequestMap.get(target).add(player);
				}
				else {
					ArrayList<Player> array = new ArrayList<>();
					array.add(player);
					this.teleportationRequestMap.put(target, array);
				}
				target.sendMessage("§2" + player.getDisplayName() + " §aveut se téléporter à votre position.\n" +
						"Pour accepter la demande : §2/tpaccept §aou §2/tpyes.\n" +
						"§aPour refuser la demande : §2/tpdeny §aou §2/tpno.");
				player.sendMessage("§aDemande envoyée.");
				return true;
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
							player.sendMessage("§2" + requester.getDisplayName() + " §aest en cours de téléportation.");
							requester.teleport(requester.getLocation());
							requester.sendMessage("§aVous venez d'être téléporté à §a" + player.getDisplayName() + ".");
							this.teleportationRequestMap.get(player).remove(requester);
							return true;
						}
					}
					player.sendMessage("§cImpossible de trouver une demande de téléportation de ce joueur.");
					return false;
				}
				else {
					final Player requester = this.teleportationRequestMap.get(player).get(0);
					player.sendMessage("§2" + requester.getDisplayName() + " §aest en cours de téléportation..");
					requester.teleport(player.getLocation());
					requester.sendMessage("§aVous venez d'être téléporté à §a" + player.getDisplayName() + ".");
					this.teleportationRequestMap.get(player).remove(requester);
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
							player.sendMessage("§aVous avez refusé la demande de télé©portation de §2" + requester.getDisplayName() + ".");
							requester.sendMessage("§2" + player.getDisplayName() + " §aa refusé votre demande de téléportation.");
							this.teleportationRequestMap.get(player).remove(requester);
							return true;
						}
					}
					player.sendMessage("§cImpossible de trouver une demande de téléportation de ce joueur.");
					return false;
				}
				else {
					final Player requester = this.teleportationRequestMap.get(player).get(0);
					player.sendMessage("§aVous avez refusé la demande de téléportation de §2" + requester.getDisplayName() + ".");
					requester.sendMessage("§2" + player.getDisplayName() + " §aa refusé votre demande de téléportation.");
					this.teleportationRequestMap.get(player).remove(requester);
					return true;
				}
			}
		}
		player.sendMessage("§cVous n'avez pas de demande de téléportation.");
		return false;
	}
	
}
