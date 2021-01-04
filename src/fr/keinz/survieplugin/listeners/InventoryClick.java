package fr.keinz.survieplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
 
public class InventoryClick implements Listener {
 
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;
 
        Player player = (Player) e.getWhoClicked();
 
        switch(e.getCurrentItem().getType()){
 
            case ELYTRA:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�bFly")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToStaff(e.getCurrentItem().getItemMeta().getDisplayName());
                    player.sendMessage("�aVous avez bien signal� ce joueur !");
                }
                break;
 
            case ACACIA_SIGN:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�cInsulte")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToStaff(e.getCurrentItem().getItemMeta().getDisplayName());
                    player.sendMessage("�aVous avez bien signal� ce joueur !");
                }
                break;
                
            case NETHERITE_INGOT:
            	if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�aDuplication")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToStaff(e.getCurrentItem().getItemMeta().getDisplayName());
                    player.sendMessage("�aVous avez bien signal� ce joueur !");
                }
                break;
 
            default: break;
        }
    }
 
    private void sendToStaff(String raison) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (!player.hasPermission("moderation.mod")) {
				player.sendMessage("�f[SERVEUR] : �3Le joueur ... � �t� �bsignal� pour " + raison);
			}
		}
	}
}
