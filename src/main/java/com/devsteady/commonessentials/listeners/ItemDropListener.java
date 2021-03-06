package com.devsteady.commonessentials.listeners;

import com.caved_in.commons.Commons;
import com.caved_in.commons.config.Configuration;
import com.caved_in.commons.game.gadget.Gadget;
import com.caved_in.commons.game.gadget.Gadgets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDropListener implements Listener {
    private Configuration config;

    public ItemDropListener() {
        config = Commons.getInstance().getConfiguration();
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {

        //todo check if player is in creative and has option for creative drops off, then don't drop items- Just remove them when they're dropped
        ItemStack item = event.getItemDrop().getItemStack();

        //If we're not dealing with gadgets
        if (!Gadgets.isGadget(item)) {
            if (!config.enableItemDrop()) {
                event.setCancelled(true);
                return;
            }
        }

        Gadget gadget = Gadgets.getGadget(item);

        // Not a gadget! We don't need to do anything.
        if (gadget == null) {
            return;
        }

        if (!gadget.properties().isDroppable()) {
            event.setCancelled(true);
            gadget.onDrop(event.getPlayer(), null);
            return;
        }

        gadget.onDrop(event.getPlayer(), event.getItemDrop());
    }
}
