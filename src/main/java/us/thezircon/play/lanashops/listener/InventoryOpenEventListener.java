package us.thezircon.play.lanashops.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.Messages;
import us.thezircon.play.lanashops.utils.ShopSign;

import java.util.ArrayList;


public class InventoryOpenEventListener implements Listener {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Inventory inv = e.getInventory();
        Player player = (Player) e.getPlayer();

        // Checks if the inventory is from a container
        if (inv.getHolder() instanceof Container) {
            Container c = (Container) inv.getHolder();
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

            if (shopSign!=null && !shopSign.isShopSign()) {
                return;
            }

            if (plugin.containerInUse.containsKey(loc)) {
                if (plugin.containerInUse.get(loc))
                    e.setCancelled(true);
                player.sendMessage(Messages.ShopInUse());
            }

            if (shopSign != null) {
                if (!player.getName().equals(shopSign.getPlayer()) && shopSign.getPlayer() != null  && !plugin.shopignore_list.contains(player)) {
                    e.setCancelled(true);
                    player.sendMessage(Messages.ShopOpenUnownedContainer().replace("{owner}", shopSign.getPlayer()));
                }
            }
        }
    }
}