package us.thezircon.play.lanashops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.Messages;

public class reload implements CommandExecutor {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("lanashops.reload")) {
            plugin.reloadConfig();
            sender.sendMessage(Messages.Reloading());
        }
        return false;
    }
}
