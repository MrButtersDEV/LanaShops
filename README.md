# LanaShops
 Trading based chest chops


[![Discord](https://img.shields.io/badge/Discord-BUTTERFIELD8%233907-blue)](https://discord.gg/nnC7nkT)
[![PayPal](https://img.shields.io/badge/Donate-Paypal-orange)](https://paypal.me/ButterfieldMedia?locale.x=en_US)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/MrButtersDEV/LanaShops)](https://github.com/MrButtersDEV/LanaShops/releases)

### Commands & Permissions:
+ Reload Command: `/ls-reload`
    - Permission: `lanashops.reload`

### Other Permissions 
+ `lanashops.create`:
   - Description: Allows a player to create a shop.
   - Default: true
+ `lanashops.create.others`:
   - Description: Allows a player to create a shop of another player.
   - Default: op
+ `lanashops.break.others`:
   - Description: Allows a player to remove a shop owned by another player.
   - Default: op
+ `lanashops.reload`:
   - Description: Allows a player to reload the plugin's config
   - Default: op

### Config:
```yaml
#Plugin Options & Settings
Options:
  notifyTypes:
    invalidMaterial: 'CHAT' #SIGN or CHAT
    invalidNumber: 'CHAT' #SIGN or CHAT
  shopSign:
    header: "[price]" #This is what is placed on the 1st line that identifies a shop
    simpleHeader: "PRICE"
    errorHeader: "[&cERROR&r]"
    formattedHeader: "[&5PRICE&r]" #This is what the above option will get set too
    #(Use "&r" to reset to black instead of &0 if you would like dye to color the brackets)

#Plugin Messages
Messages:
  prefix: "&5&lSHOP >"
  reload: "&cReloading..."
  invalidMaterial: "&6That material doesn't exist!"
  invalidNumber: "&6Invalid number!"
  alreadyShop: "&cFailed to create shop! &3- &7A shop already exists for this container!"
  shopCreate: "&6Shop successfully created!"
  shopNotEnough: "&6You don't have &c{amount} &6of &c{material}." #Placeholders: {amount} & {material}
  shopBuyRequiredMaterial: "&7You can't buy the same item as the shop is requesting"
  shopOpenUnownedContainer: "&eYou may not open this as it's a shop owned by {owner}" #Placeholders: {owner}
  shopInUse: "&7This shop is in use please check back later!"
  breakShopSign: "&cYou cannot break someone else's shop sign"
  breakShopContainer: "&cYou cannot break someone else's shop chest"
  breakShopOthers: "&bYou need to shift-click to break another players shop"
```
