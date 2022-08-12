package me.tlopster.cwtroll.trolls;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KartoxaTroll {
    public int execute(Player p) {
        HashMap<Integer, ItemStack> res = p.getInventory().addItem(new ItemStack(Material.POTATO_ITEM, 2304));
        int extra = 0;
        for(Map.Entry<Integer, ItemStack> item : res.entrySet())
            extra += item.getValue().getAmount();
        return extra;
    }
}

