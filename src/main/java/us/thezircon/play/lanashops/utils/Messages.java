package us.thezircon.play.lanashops.utils;

import org.bukkit.ChatColor;
import us.thezircon.play.lanashops.LanaShops;

public class Messages {

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    private static String Prefix() {
        return color(plugin.getConfig().getString("Messages.prefix"));
    }

    public static String InvalidMaterial() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.invalidMaterial"));
    }

    public static String InvalidNumber() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.invalidNumber"));
    }

    public static String AlreadyShop() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.alreadyShop"));
    }

    public static String ShopCreate() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopCreate"));
    }

    public static String ShopNotEnough() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopNotEnough"));
    }

    public static String ShopBuyRequiredMaterial() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopBuyRequiredMaterial"));
    }

    public static String ShopOpenUnownedContainer() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopOpenUnownedContainer"));
    }

    public static String ShopInUse() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopInUse"));
    }

    public static String BreakShopSign() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.breakShopSign"));
    }

    public static String BreakShopContainer() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.breakShopContainer"));
    }

    public static String BreakShopOthers() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.breakShopOthers"));
    }

    public static String ShopIgnoreEnable() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopIgnoreEnable"));
    }

    public static String ShopIgnoreDisable() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.shopIgnoreDisable"));
    }

    public static String Reloading() {
        return Prefix() + " " + color(plugin.getConfig().getString("Messages.reload"));
    }

    public static String Header() {
        return plugin.getConfig().getString("Options.shopSign.header");
    }

    public static String SimpleHeader() {
        return plugin.getConfig().getString("Options.shopSign.simpleHeader");
    }

    public static String FormattedHeader() {
        return color(plugin.getConfig().getString("Options.shopSign.formattedHeader"));
    }

    public static String FormattedError() {
        return color(plugin.getConfig().getString("Options.shopSign.errorHeader"));
    }

    private static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
