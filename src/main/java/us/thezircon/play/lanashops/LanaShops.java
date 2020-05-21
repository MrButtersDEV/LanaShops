package us.thezircon.play.lanashops;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.thezircon.play.lanashops.commands.reload;
import us.thezircon.play.lanashops.listener.*;
import us.thezircon.play.lanashops.utils.BuyMenu;

import java.util.HashMap;
import java.util.logging.Logger;

public final class LanaShops extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    public HashMap<Player, BuyMenu> openShop = new HashMap<>();
    public HashMap<Location, Boolean> shopInUse = new HashMap<>();
    public HashMap<Location, Boolean> containerInUse = new HashMap<>();

    @Override
    public void onEnable() {
        // Setup - Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Setup - Commands
        getCommand("ls-reload").setExecutor(new reload());

        // Setup - Listeners
        getServer().getPluginManager().registerEvents(new SignChangeEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenEventListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickEventListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseEventListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryMoveItemEventListener(), this);

    }
}
