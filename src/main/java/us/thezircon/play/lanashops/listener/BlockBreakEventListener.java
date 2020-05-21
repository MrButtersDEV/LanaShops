package us.thezircon.play.lanashops.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.Messages;
import us.thezircon.play.lanashops.utils.ShopSign;

import java.util.ArrayList;

public class BlockBreakEventListener implements Listener {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if (plugin.containerInUse.containsKey(block.getLocation())) {
            if (plugin.containerInUse.get(block.getLocation())) {
                e.setCancelled(true);
                player.sendMessage(Messages.ShopInUse());
            }
        }

        // Checks if player broke a sign & if its a shop
        if (block.getState() instanceof Sign) {
            ShopSign shopSign = new ShopSign((Sign) block.getState());
            if (shopSign.isShopSign() && !shopSign.getPlayer().equals(player)) {
                if (!player.hasPermission("lanashops.break.others") && !player.isSneaking()) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.BreakShopSign());
                } else if (player.hasPermission("lanashops.break.others") && !player.isSneaking()) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.BreakShopOthers());
                }
            }
        }

        // Checks if a container was broken and if container is a shop
        if (block.getState() instanceof Container) {
            Container c = (Container) block.getState();
            Location loc = c.getLocation();

            ArrayList<Block> nearby = new ArrayList<>();
            nearby.add(loc.getBlock().getRelative(BlockFace.UP));
            nearby.add(loc.getBlock().getRelative(BlockFace.NORTH));
            nearby.add(loc.getBlock().getRelative(BlockFace.SOUTH));
            nearby.add(loc.getBlock().getRelative(BlockFace.EAST));
            nearby.add(loc.getBlock().getRelative(BlockFace.WEST));

            ShopSign shopSign = null;
            for (Block b : nearby) {
                if (b.getState() instanceof Sign) {
                    shopSign = new ShopSign((Sign) b.getState());
                    if (shopSign.isShopSign()) {
                        break;
                    }
                }
            }

            if (!shopSign.isShopSign()) {
                return;
            }

            if (shopSign != null && !shopSign.getPlayer().equals(player)) {
                if (!player.hasPermission("lanashops.break.others")) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.BreakShopContainer());
                } else if (player.hasPermission("lanashops.break.others") && !player.isSneaking()) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.BreakShopOthers());
                }
            }
        }
    }
}