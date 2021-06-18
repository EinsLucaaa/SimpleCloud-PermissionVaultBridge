package de.combit.bridge;

import de.combit.bridge.perms.PermissionBridge;
import lombok.Getter;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
» Author:  1combit
» Date: 18.06.2021 | 19:10
» Twitter: @1combit
» Github: 1combit
» Project: SimpleCloud-PermissionVaultBridge
 */

@Getter
public class VaultBridge extends JavaPlugin {

    public static VaultBridge instance;

    @Override
    public void onEnable() {
        instance = this;
        ServicesManager servicesManager = instance.getServer().getServicesManager();

        PermissionBridge permissionBridge = new PermissionBridge();
        servicesManager.register(Permission.class, permissionBridge, instance, ServicePriority.Highest);


    Bukkit.getConsoleSender().sendMessage("§aVault Support for SimpleCloud-Permission-System was loaded!");



    }



    @Override
    public void onDisable() {

    }
}
