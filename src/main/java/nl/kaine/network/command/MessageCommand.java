package nl.kaine.network.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import nl.kaine.network.Network;

import java.util.ArrayList;
import java.util.List;

public class MessageCommand extends Command implements TabExecutor {

    private Network network;
    public MessageCommand(Network network) {
        super("message");
        this.network = network;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length >= 2) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if (target != null) {

                    StringBuilder builder = new StringBuilder();
                    // Loop door argument die achter elke string een SPATIE plaatst.
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[1]).append(" ");
                    }

                    player.sendMessage(ChatColor.GRAY + "Jij -> " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " : " + ChatColor.GRAY + builder);
                    target.sendMessage(player.getName() + ChatColor.GRAY + " -> jou" + " : " + ChatColor.GRAY + builder);

                    network.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                } else {
                    player.sendMessage(ChatColor.RED + "Deze speler is niet online!");
                }

            } else {
                player.sendMessage(ChatColor.RED + "Verkeerd gebruik! Gebruik: /message <speler> <bericht>.");
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                results.add(player.getName());
            }
        }

        return results;
    }
}