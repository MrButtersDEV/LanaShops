package us.thezircon.play.lanashops.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.BuyMenu;

public class InventoryCloseEventListener implements Listener {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();

        if (plugin.openShop.containsKey(player)) {
            BuyMenu buyMenu = plugin.openShop.get(player);
            if (e.getView().getTitle().equals(buyMenu.getTitle()) && e.getView().getType().equals(buyMenu.getType())) {
                plugin.shopInUse.remove(buyMenu.getSign());
                plugin.containerInUse.remove(buyMenu.getContainer().getLocation());
            }
        }
    }
}
