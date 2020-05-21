package us.thezircon.play.lanashops.listener;

import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.BuyMenu;
import us.thezircon.play.lanashops.utils.Messages;

public class InventoryClickEventListener implements Listener {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getCurrentItem()==null) {
            return;
        }

        if (plugin.openShop.containsKey(player)) {
            BuyMenu buyMenu = plugin.openShop.get(player);
            if (e.getView().getTitle().equals(buyMenu.getTitle()) && e.getView().getType().equals(buyMenu.getType())) {
                e.setCancelled(true);

                if (e.getCurrentItem().getType().equals(buyMenu.getMaterial())) {
                    player.sendMessage(Messages.ShopBuyRequiredMaterial());
                    return;
                }

                // Player has the required items to buy from the shop
                if (player.getInventory().contains(buyMenu.getMaterial())) {
                    int slot = player.getInventory().first(buyMenu.getMaterial());
                    if (player.getInventory().getItem(slot).getAmount()>=buyMenu.getAmt()) {
                        //Player Side
                        player.getInventory().addItem(e.getCurrentItem());
                        e.getCurrentItem().setType(buyMenu.getMaterial());
                        e.getCurrentItem().setAmount(buyMenu.getAmt());
                        ItemMeta meta = new ItemStack(buyMenu.getMaterial()).getItemMeta();
                        e.getCurrentItem().setItemMeta(meta);
                        player.getInventory().setItem(slot, new ItemStack(buyMenu.getMaterial(), player.getInventory().getItem(slot).getAmount()-buyMenu.getAmt()));
                        //Chest Side
                        ItemStack items[] = e.getView().getTopInventory().getContents();
                        Block b = buyMenu.getBlock();
                        ((Container) b.getState()).getInventory().setContents(items);
                    } else {
                        player.sendMessage(Messages.ShopNotEnough().replace("{amount}", buyMenu.getAmt()+"").replace("{material}", buyMenu.getMaterial().name().toLowerCase()));
                    }
                } else {
                    player.sendMessage(Messages.ShopNotEnough().replace("{amount}", buyMenu.getAmt()+"").replace("{material}", buyMenu.getMaterial().name().toLowerCase()));
                }
            }
        }
    }
}
