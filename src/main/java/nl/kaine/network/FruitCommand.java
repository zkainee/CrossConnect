package nl.kaine.network;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FruitCommand extends Command implements TabExecutor {


    public FruitCommand() {
        super("fruit");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            results.add("Lorem");
            results.add("Ipsum");
            results.add("Dolor");
            results.add("Sit");
            results.add("Amet");
            // Loop door array + search function w/ Java Streams
            return results.stream().filter(val -> val.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        } else if (args.length == 2) {
            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                results.add(player.getName());
            }
            // Loop door array + search function w/ Java Streams
            return results.stream().filter(val -> val.toLowerCase().startsWith(args[1].toLowerCase())).collect(Collectors.toList());
        }

        return results;
    }
}
