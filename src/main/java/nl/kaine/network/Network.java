package nl.kaine.network;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import nl.kaine.network.command.MessageCommand;
import nl.kaine.network.command.ReplyCommand;
import nl.kaine.network.listener.MessageListener;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class Network extends Plugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    @Override
    public void onEnable() {
        System.out.println("NETWORK | LOADED");

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MessageCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new ReplyCommand(this));

        ProxyServer.getInstance().getPluginManager().registerListener(this, new MessageListener(this));

        recentMessages = new HashMap<>();

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new FruitCommand());
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);

//        ProxyServer.getInstance().getScheduler().schedule(this, () -> {
//            System.out.println("NETWORK | SCHEDULAR RAN");
//        }, 10, 5, TimeUnit.SECONDS);

    }

    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        e.getPlayer().sendMessage("Welkom");
    }


}
