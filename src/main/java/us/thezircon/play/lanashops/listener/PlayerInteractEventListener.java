package us.thezircon.play.lanashops.listener;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.BuyMenu;
import us.thezircon.play.lanashops.utils.Messages;
import us.thezircon.play.lanashops.utils.ShopSign;

public class PlayerInteractEventListener implements Listener {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        Player player = e.getPlayer();

        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            assert block != null;

            // Check if sign is a shop
            ShopSign shopSign;
            if (block.getState() instanceof Sign) {
                shopSign = new ShopSign((Sign) block.getState());
                if (!shopSign.isShopSign()) {
                    return;
                } else {
                    for (String lines : ((Sign) block.getState()).getLines()) {
                        if (lines.contains(player.getName())) {
                            return;
                        }
                    }
                }
            } else {
                return;
            }


            if (plugin.shopInUse.containsKey(block.getLocation())) {
                if (plugin.shopInUse.get(block.getLocation())) {
                    player.sendMessage(Messages.ShopInUse());
                    return;
                }
            }

            // Get sign type and the container its attached too.
            Block container = null;
            if (block.getState() instanceof Directional) {
                container = block.getRelative(BlockFace.DOWN);
            } else {
                Directional directional = (Directional) block.getBlockData();
                container = block.getRelative(directional.getFacing().getOppositeFace());
            }

            BuyMenu buyMenu = new BuyMenu(player, container, shopSign.getAmt(), shopSign.getMaterial(), block.getLocation());
            plugin.openShop.put(player, buyMenu);
            plugin.shopInUse.put(block.getLocation(), true);
            plugin.containerInUse.put(container.getLocation(), true);
            buyMenu.open();
        }
    }
}