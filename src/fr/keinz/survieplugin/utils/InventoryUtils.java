package fr.keinz.survieplugin.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
	private static HashMap<Player, ArrayList<ItemStack>> list2 = new HashMap();
	  
	  public static void saveInventory(Player player) {
	    ArrayList<ItemStack> list = new ArrayList<ItemStack>();
	    ItemStack[] content = player.getInventory().getContents();
	    for (int i = 0; i < content.length; i++) {
	      ItemStack is = content[i];
	      if (is != null) {
	        list.add(is);
	      }
	    } 
	    list2.put(player, list);
	    player.getInventory().clear();
	    //GiveKit.addkit(player);
	  }

	  
	  public static void loadInventory(Player player) {
		player.getInventory().clear();
	    ItemStack[] content = player.getInventory().getContents();
	    for (int i = 0; i < ((ArrayList)list2.get(player)).size(); i++) {
	      content[i] = (ItemStack)((ArrayList)list2.get(player)).get(i);
	    }

	    
	    player.getInventory().setContents(content);
	    list2.remove(player);
	  }
}
