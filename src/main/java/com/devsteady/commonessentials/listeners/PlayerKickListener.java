package com.devsteady.commonessentials.listeners;

import com.caved_in.commons.Commons;
import com.caved_in.commons.config.Configuration;
import com.caved_in.commons.permission.Perms;
import com.caved_in.commons.player.Players;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.UUID;

public class PlayerKickListener implements Listener {

    private Configuration config;

    public PlayerKickListener() {
        config = Commons.getInstance().getConfiguration();
    }

    @EventHandler
    public void onPlayerKicked(PlayerKickEvent event) {
        if (!config.enableKickMessages()) {
            event.setLeaveMessage(null);
        }

        Player player = event.getPlayer();

        if (Players.hasPermission(player, Perms.KICK_DENY)) {
            event.setCancelled(true);
            return;
        }

        UUID playerId = player.getUniqueId();
        Players.removeData(playerId);
    }
}
