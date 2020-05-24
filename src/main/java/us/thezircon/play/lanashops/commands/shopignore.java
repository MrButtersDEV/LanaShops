package us.thezircon.play.lanashops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.Messages;

public class shopignore implements CommandExecutor {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player && sender.hasPermission("lanashops.shopignore")) {
            Player player = (Player) sender;
            if (plugin.shopignore_list.contains(player)) {
                plugin.shopignore_list.remove(player);
                player.sendMessage(Messages.ShopIgnoreEnable());
            } else if (!plugin.shopignore_list.contains(player)) {
                plugin.shopignore_list.add(player);
                player.sendMessage(Messages.ShopIgnoreDisable());
            }
        } else {
            sender.sendMessage("Must be a player to use /shopignore");
        }

        return false;
    }
}
