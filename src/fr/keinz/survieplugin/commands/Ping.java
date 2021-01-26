package fr.keinz.surviePlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            final Player player = (Player) sender;
            if (args.length == 1){
                final Player target = Bukkit.getPlayer(args[0]);
                if (target != null) player.sendMessage("§aLe ping de §2" + target.getDisplayName() + " §aest de §2" + ((CraftPlayer) target).getHandle().ping + " §ams");
                else player.sendMessage("§aLe joueur §2" + target.getDisplayName() + " §an'existe pas ou n'est pas connecté.");
            }
            else {
                player.sendMessage("§aTon ping est de §2" + ((CraftPlayer) player).getHandle().ping + " §ams");
            }
        }
        return false;
    }
}