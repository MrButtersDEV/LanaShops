package us.thezircon.play.lanashops.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import us.thezircon.play.lanashops.utils.ShopSign;

import java.util.ArrayList;

public class InventoryMoveItemEventListener implements Listener {

    @EventHandler
    public void onMove(InventoryMoveItemEvent e) {
        if (e.getDestination().getType() == InventoryType.HOPPER) {
            Block block = e.getSource().getLocation().getBlock();
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

                if (shopSign!=null && !shopSign.isShopSign()) {
                    return;
                }

                if (!e.getItem().getType().equals(shopSign.getMaterial())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}