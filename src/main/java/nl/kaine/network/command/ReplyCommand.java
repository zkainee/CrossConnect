package nl.kaine.network.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import nl.kaine.network.Network;

public class ReplyCommand extends Command {

    private Network network;
    public ReplyCommand(Network network) {
        super("reply");
        this.network = network;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length >= 1) {
                if (network.getRecentMessages().containsKey(player.getUniqueId())) {
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(network.getRecentMessages().get(player.getUniqueId()));
                    if (target != null) {
                        StringBuilder builder = new StringBuilder();
                        // Loop door argument die achter elke string een SPATIE plaatst.
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[1]).append(" ");
                        }

                        player.sendMessage(ChatColor.GRAY + "Jij -> " + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " : " + ChatColor.GRAY + builder);
                        target.sendMessage(player.getName() + ChatColor.GRAY + " -> jou" + " : " + ChatColor.GRAY + builder);
                    } else {
                        player.sendMessage(ChatColor.RED + "Deze speler offline gegaan sinds dat je laatste bericht.");
                        network.getRecentMessages().remove(player.getUniqueId());
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Je hebt niemand om op te reageren.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Verkeerd gebruik! Gebruik: /message <speler> <bericht>.");
            }
        }
    }
}
