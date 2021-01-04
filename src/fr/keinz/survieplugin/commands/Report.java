package fr.keinz.survieplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.keinz.survieplugin.utils.ItemBuilder;

public class Report implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peut executer cette commande !");
            return false;
        }
 
        Player player = (Player) sender;
 
        if(label.equalsIgnoreCase("report")){
            if(args.length != 1){
                player.sendMessage("�cVeuillez saisir le pseudo d'un joueur !");
                return false;
            }
 
            String targetName = args[0];
 
            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("�cCe joueur n'est pas connect� ou n'existe pas !");
                return false;
            }
 
            Player target = Bukkit.getPlayer(targetName);
 
            Inventory inv = Bukkit.createInventory(null, 27, "�aReport: �2" + target.getName());
 
            inv.setItem(10, new ItemBuilder(Material.ELYTRA).setName("�bFly").toItemStack());
            inv.setItem(13, new ItemBuilder(Material.ACACIA_SIGN).setName("�cInsulte").toItemStack());
            inv.setItem(16, new ItemBuilder(Material.NETHERITE_INGOT).setName("�aDuplication").toItemStack());
 
            player.openInventory(inv);
        }
 
        return false;
    }

}
