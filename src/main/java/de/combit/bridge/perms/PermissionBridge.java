package de.combit.bridge.perms;

import de.combit.bridge.util.UUIDFetcher;
import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.module.permission.PermissionPool;
import eu.thesimplecloud.module.permission.group.IPermissionGroup;
import eu.thesimplecloud.module.permission.group.PermissionGroup;
import eu.thesimplecloud.module.permission.player.IPermissionPlayer;
import eu.thesimplecloud.module.permission.player.PlayerPermissionGroupInfo;
import net.milkbowl.vault.permission.Permission;

import java.util.Arrays;

/*
» Author:  1combit
» Date: 18.06.2021 | 19:11
» Twitter: @1combit
» Github: 1combit
» Project: SimpleCloud-PermissionVaultBridge
 */public class PermissionBridge extends Permission {


    @Override
    public String getName() {
        return "SimpleCloud-PermissionModule";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean hasSuperPermsCompat() {
        return true;
    }

    @Override
    public boolean playerHas(String world, String player, String permission) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));


        return permissionPlayer.hasPermission(permission);
    }

    @Override
    public boolean playerAdd(String world, String player, String permission) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
         permissionPlayer.addPermission(new eu.thesimplecloud.module.permission.permission.Permission(permission, -1, true));
        permissionPlayer.update();
        return true;
    }

    @Override
    public boolean playerRemove(String world, String player, String permission) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        permissionPlayer.removePermission(permission);
        permissionPlayer.update();
        return true;
    }

    @Override
    public boolean groupHas(String world, String group, String permission) {
        IPermissionGroup iPermissionGroup = PermissionPool.getInstance().getPermissionGroupManager().getPermissionGroupByName(group);
        return         iPermissionGroup.hasPermission(permission);
    }

    @Override
    public boolean groupAdd(String world, String group, String permission) {
        IPermissionGroup iPermissionGroup = PermissionPool.getInstance().getPermissionGroupManager().getPermissionGroupByName(group);
        iPermissionGroup.addPermission(new eu.thesimplecloud.module.permission.permission.Permission(permission, -1, true));
        return true;
    }

    @Override
    public boolean groupRemove(String world, String group, String permission) {
        IPermissionGroup iPermissionGroup = PermissionPool.getInstance().getPermissionGroupManager().getPermissionGroupByName(group);
        iPermissionGroup.removePermission(permission);
        return true;
    }

    @Override
    public boolean playerInGroup(String world, String player, String group) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        return permissionPlayer.hasPermissionGroup(group);
    }

    @Override
    public boolean playerAddGroup(String world, String player, String group) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        permissionPlayer.addPermissionGroup(new PlayerPermissionGroupInfo(group, -1));
        permissionPlayer.update();
        return true;
    }

    @Override
    public boolean playerRemoveGroup(String world, String player, String group) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        permissionPlayer.removePermissionGroup(group);
        permissionPlayer.update();
        return true;
    }

    @Override
    public String[] getPlayerGroups(String world, String player) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        return (String[]) permissionPlayer.getAllNotExpiredPermissionGroups().toArray();
    }

    @Override
    public String getPrimaryGroup(String world, String player) {
        IPermissionPlayer permissionPlayer = PermissionPool.getInstance().getPermissionPlayerManager().getCachedPermissionPlayer(UUIDFetcher.getUUID(player));
        return permissionPlayer.getHighestPermissionGroup().getName();
    }

    @Override
    public String[] getGroups() {
        return (String[]) PermissionPool.getInstance().getPermissionGroupManager().getAllPermissionGroups().stream().toArray();
    }

    @Override
    public boolean hasGroupSupport() {
        return true;
    }
}
