package fr.keinz.survieplugin.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import fr.keinz.survieplugin.utils.PlayerManager;

public class ModItemsInteract implements Listener {
 
    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if(!PlayerManager.isInModerationMod(player)) return;
        if(!(e.getRightClicked() instanceof  Player)) return;
        Player target = (Player) e.getRightClicked();
 
        e.setCancelled(true);
 
        switch(player.getInventory().getItemInHand().getType()){
            /**
             * Voir l'inventaire
             */
            case CHEST:
                Inventory inv = Bukkit.createInventory(null, 5 * 9, target.getName() + " > Inventaire");
 
                for(int i = 0; i < 36; i++){
                    if(target.getInventory().getItem(i) != null){
                        inv.setItem(i, target.getInventory().getItem(i));
                    }
                }
 
                inv.setItem(36, target.getInventory().getHelmet());
                inv.setItem(37, target.getInventory().getChestplate());
                inv.setItem(38, target.getInventory().getLeggings());
                inv.setItem(39, target.getInventory().getBoots());
 
                player.openInventory(inv);
                break;
 
            /**
             * Voir les signalements
             */
            case BOOK:
                /**
                 *   TODO
                 */
                break;
            /*
             * Clear l'inventaire du joueur
             */
            case BEDROCK:
	                target.getInventory().clear();
	                target.getInventory().setHelmet(null);
	                target.getInventory().setChestplate(null);
	                target.getInventory().setLeggings(null);
	                target.getInventory().setBoots(null);
            	break;
 
            /**
             * Tueur de joueur
             */
            case BLAZE_ROD:
                target.damage(target.getHealth());
                break;
 
            default: break;
        }
    }
 
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(!PlayerManager.isInModerationMod(player)) return;
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) return;
 
        switch(player.getInventory().getItemInHand().getType()){
            /**
             * T�l�portation al�atoire
             */
            case COMPASS:
                List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                list.remove(player);
 
                if(list.size() == 0){
                    player.sendMessage("�cIl n'y a aucun joueur sur lequel vous t�l�porter.");
                    return;
                }
 
                Player target = list.get(new Random().nextInt(list.size()));
                player.teleport(target.getLocation());
                player.sendMessage("�aVous avez �t� t�l�port� � �e" + target.getName());
                break;
 
            /**
             * Vanish
             */
            case GUNPOWDER:
                PlayerManager mod = PlayerManager.getFromPlayer(player);
                mod.setVanished(!mod.isVanished());
                player.sendMessage(mod.isVanished() ? "�avous �tes � pr�sent invisible !" : "�bVous �tes � pr�sent visible !");
                break;
 
            default: break;
        }
    }
 
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        for(Player players : Bukkit.getOnlinePlayers()){
            if(PlayerManager.isInModerationMod(players)){
                PlayerManager pm = PlayerManager.getFromPlayer(players);
                if(pm.isVanished()){
                    player.hidePlayer(players);
                }
            }
        }
    }
}