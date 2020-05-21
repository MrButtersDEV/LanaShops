package us.thezircon.play.lanashops.listener;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import us.thezircon.play.lanashops.LanaShops;
import us.thezircon.play.lanashops.utils.Messages;
import us.thezircon.play.lanashops.utils.ShopSign;

import java.util.ArrayList;

public class SignChangeEventListener implements Listener{

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    @EventHandler
    public void onChange(SignChangeEvent e) {
        Player player = e.getPlayer();
        String lines[] = e.getLines();
        Block block = e.getBlock();

        //Can player create shop
        if (!player.hasPermission("lanashops.create")) {
            return;
        }

        Container c = null;

        // Check if a shop is placed on a container
        if (block.getBlockData() instanceof WallSign) { // Wall Sign
            BlockData data = block.getBlockData();
            if (data instanceof Directional) {
                Directional directional = (Directional) data;
                Block blockBehind = block.getRelative(directional.getFacing().getOppositeFace());
                if (!(blockBehind.getState() instanceof Container)) {
                    //player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to create shop! &3- &7If you wish to create a shop please place a sign on a container!"));
                    return;
                } else {
                    c = (Container) blockBehind.getState();
                }
            }
        } else { // Standing Sign
            Block below = block.getLocation().subtract(0, 1, 0).getBlock();
            if (!(below.getState() instanceof Container)) {
               // player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFailed to create shop! &3- &7If you wish to create a shop please place a sign on a container!"));
                return;
            } else {
                c = (Container) below.getState();
            }
        }

        // Check if a shop is on this container already
        ArrayList<Block> nearby = new ArrayList<>();
        assert c != null;
        Location Chest = c.getLocation();
        nearby.add(Chest.getBlock().getRelative(BlockFace.UP));
        nearby.add(Chest.getBlock().getRelative(BlockFace.NORTH));
        nearby.add(Chest.getBlock().getRelative(BlockFace.SOUTH));
        nearby.add(Chest.getBlock().getRelative(BlockFace.EAST));
        nearby.add(Chest.getBlock().getRelative(BlockFace.WEST));

        ShopSign shopSign;
        boolean alreadyShop = false;
        for (Block b : nearby) {
            if (alreadyShop) {
                player.sendMessage(Messages.AlreadyShop());
                return;
            }
            if (b.getState() instanceof Sign) {
                shopSign = new ShopSign((Sign) b.getState());
                if (shopSign.isShopSign()) {
                    alreadyShop = true;
                }
            }
        }

        // Check & format sign shop
        if (lines[0].equalsIgnoreCase(Messages.Header())) {
            e.setLine(0, Messages.FormattedHeader());

            // Checks if amt is int
            if (!StringUtils.isNumeric(lines[1])) {
                if (plugin.getConfig().getString("Options.notifyTypes.invalidNumber").equalsIgnoreCase("CHAT")) {
                    player.sendMessage(Messages.InvalidNumber());
                    e.setLine(0, Messages.FormattedError());
                    //return;
                } else {
                    e.setLine(1, ChatColor.translateAlternateColorCodes('&', "&cError: &4Not a num!"));
                }
            }

            // Checks for valid material
            if (Material.matchMaterial(lines[2].toUpperCase()) == null) {
                if (plugin.getConfig().getString("Options.notifyTypes.invalidMaterial").equalsIgnoreCase("CHAT")) {
                    player.sendMessage(Messages.InvalidMaterial());
                    e.setLine(0, Messages.FormattedError());
                    //return;
                } else {
                    e.setLine(2, ChatColor.translateAlternateColorCodes('&', "&cError: &4Material?"));
                }
            }

            // Checks / Sets line 4 to be players name
            if (!lines[3].equals(player.getName()))
            try {
                 if (!Bukkit.getOfflinePlayer(lines[3]).hasPlayedBefore() || !player.hasPermission("lanashops.create.others")) {
                     e.setLine(3, player.getName());
                 }
            } catch (IllegalArgumentException er) {
                e.setLine(3, player.getName());
            }
        }
    }
}