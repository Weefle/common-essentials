package com.devsteady.commonessentials.listeners;

import com.caved_in.commons.Commons;
import com.caved_in.commons.config.Configuration;
import com.caved_in.commons.player.Players;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    private Configuration config;

    private static Commons commons = Commons.getInstance();

    public PlayerQuitListener() {
        config = commons.getConfiguration();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        UUID playerId = event.getPlayer().getUniqueId();

        //If there's no leave/join messages, then remove the message!
        if (!config.enableJoinMessages()) {
            event.setQuitMessage(null);
        }

        //Remove the cached player instance!
        Players.removeData(playerId);
    }
}
