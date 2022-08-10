package nl.kaine.network;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public final class Network extends Plugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("NETWORK | LOADED");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new PingCommand());
        ProxyServer.getInstance().getPluginManager().registerListener(this, this);

//        ProxyServer.getInstance().getScheduler().schedule(this, () -> {
//            System.out.println("NETWORK | SCHEDULAR RAN");
//        }, 10, 5, TimeUnit.SECONDS);

    }

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        e.getPlayer().sendMessage("Welkom");
    }


}
