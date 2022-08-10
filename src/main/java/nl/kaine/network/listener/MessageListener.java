package nl.kaine.network.listener;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import nl.kaine.network.Network;

public class MessageListener implements Listener {

    private final Network network;
    public MessageListener(Network network) {
        this.network = network;
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent e) {
        network.getRecentMessages().remove(e.getPlayer().getUniqueId());
    }
}