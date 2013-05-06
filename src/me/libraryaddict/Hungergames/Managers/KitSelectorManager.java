package me.libraryaddict.Hungergames.Managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.libraryaddict.Hungergames.Types.HungergamesApi;
import me.libraryaddict.Hungergames.Types.KitInventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitSelectorManager {

    Inventory icon;
    ItemStack kitSelector = null;

    public void createInventory(String invName, ArrayList<ItemStack> items) {
        int size = (int) (Math.ceil((double) items.size() / 9)) * 9;
        icon = Bukkit.createInventory(null, size, invName);
        for (int i = 0; i < items.size(); i++)
            icon.setItem(i, items.get(i));
    }

    public ItemStack generateItem(Material type, int dataValue, String name, List<String> lore) {
        return generateItem(type.getId(), dataValue, name, lore);
    }

    public ItemStack generateItem(int type, int dataValue, String name, String[] lore) {
        return generateItem(type, dataValue, name, Arrays.asList(lore));
    }

    public ItemStack generateItem(Material type, int dataValue, String name, String[] lore) {
        return generateItem(type.getId(), dataValue, name, Arrays.asList(lore));
    }

    public ItemStack generateItem(int id, int dataValue, String name, List<String> lore) {
        ItemStack item = new ItemStack(id, 1, (short) dataValue);
        ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(ChatColor.WHITE + name);
        }
        if (lore != null && lore.size() > 0) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKitSelector() {
        if (kitSelector == null) {
            ItemStack item = HungergamesApi.getConfigManager().getKitSelectorIcon();
            kitSelector = generateItem(item.getType(), item.getDurability(), getKitSelectorName(), HungergamesApi
                    .getChatManager().getItemKitSelectorDescription());
        }
        return kitSelector;
    }

    public String getKitSelectorName() {
        return HungergamesApi.getChatManager().getItemKitSelectorName();
    }

    public void openInventory(Player p) {
        KitInventory inv = new KitInventory(p);
        inv.setKits();
        inv.openInventory();
    }

    public Inventory getInventory() {
        return icon;
    }
}
