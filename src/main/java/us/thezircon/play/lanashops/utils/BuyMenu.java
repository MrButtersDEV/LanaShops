package us.thezircon.play.lanashops.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import us.thezircon.play.lanashops.LanaShops;

public class BuyMenu {

    private ItemStack[] items;
    private Player player;
    private Inventory gui;
    private String title;
    private InventoryType type;
    private Container container;
    private Block block;
    private Location sign;

    private int amt;
    private Material material;

    private static final LanaShops plugin = LanaShops.getPlugin(LanaShops.class);

    public BuyMenu(Player player, Block block, int amt, Material material, Location sign){
        this.block = block;
        this.sign = sign;
        this.amt = amt;
        this.material = material;
        this.player = player;
        this.container = (Container) block.getState();
        type = container.getInventory().getType();
        this.items = container.getInventory().getContents();

        this.title = "Shop";
        if (container.getCustomName()!=null) {
            this.title=container.getCustomName();
        }

        this.gui = Bukkit.createInventory(player, type, this.title);
        setupGUI();
    }

    private void setupGUI(){
        for (int x = 0; x<container.getInventory().getSize(); x++) {
            gui.setItem(x, container.getInventory().getItem(x));
        }
    }

    public void open(){
        player.openInventory(gui);
    }

    public Inventory getGui() {
        return gui;
    }

    public String getTitle() {
        return title;
    }

    public InventoryType getType() {
        return type;
    }

    public int getAmt(){
        return amt;
    }

    public Material getMaterial() {
        return material;
    }

    public Block getBlock() {
        return block;
    }

    public Container getContainer() {
        return container;
    }

    public Location getSign(){
        return sign;
    }
}
