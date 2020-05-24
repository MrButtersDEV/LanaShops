package us.thezircon.play.lanashops.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class ShopSign {

    private String[] lines;

    public ShopSign(Sign sign) {
        lines = sign.getLines();
    }

    public boolean isShopSign(){
        return lines[0].contains(Messages.SimpleHeader()) && lines[0].contains("[") && lines[0].contains("]") && Bukkit.getOfflinePlayer(lines[3]).hasPlayedBefore() && StringUtils.isNumeric(lines[1]) && Material.matchMaterial(lines[2])!=null;
    }

    public int getAmt() {
        return Integer.parseInt(lines[1]);
    }

    public Material getMaterial() {
        return Material.matchMaterial(lines[2]);
    }

    public String getPlayer() {
        return (lines[3]);
    }

}
