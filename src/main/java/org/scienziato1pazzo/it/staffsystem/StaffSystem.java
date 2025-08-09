package org.scienziato1pazzo.it.staffsystem;

import com.jeff_media.updatechecker.UpdateCheckSource;
import com.jeff_media.updatechecker.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;
import org.scienziato1pazzo.it.staffsystem.commandg.gmc;
import org.scienziato1pazzo.it.staffsystem.commandg.gms;
import org.scienziato1pazzo.it.staffsystem.commandg.gmsp;
import org.scienziato1pazzo.it.staffsystem.config.config;
import org.scienziato1pazzo.it.staffsystem.fly.fly;
import org.scienziato1pazzo.it.staffsystem.invsee.InvSee;
import org.scienziato1pazzo.it.staffsystem.staffmode.listener.OnLeft;
import org.scienziato1pazzo.it.staffsystem.staffmode.listener.Picchiatore;
import org.scienziato1pazzo.it.staffsystem.staffmode.listener.StaffModeListener;
import org.scienziato1pazzo.it.staffsystem.staffmode.StaffModCommand;
import org.scienziato1pazzo.it.staffsystem.staffmode.listener.TerminatorListener;
import org.scienziato1pazzo.it.staffsystem.vanish.Freeze;
import org.scienziato1pazzo.it.staffsystem.vanish.Vanish;

public final class StaffSystem extends JavaPlugin {

    public static StaffSystem StaffSystem;
    private static final int SPIGOT_RESOURCE_ID = 111796;

    @Override
    public void onEnable() {
        this.StaffSystem = this;
        config.create();
        config.save();
        config.reload();
        getCommand("gmc").setExecutor(new gmc());
        getCommand("gms").setExecutor(new gms());
        getCommand("gmsp").setExecutor(new gmsp());
        getCommand("fly").setExecutor(new fly());
        getCommand("staff").setExecutor(new StaffModCommand());
        getCommand("invsee").setExecutor(new InvSee());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("freeze").setExecutor(new Freeze());

        getServer().getPluginManager().registerEvents(new StaffModeListener(), this);
        getServer().getPluginManager().registerEvents(new Vanish(), this);
        getServer().getPluginManager().registerEvents(new Freeze(), this);
        getServer().getPluginManager().registerEvents(new Picchiatore(), this);
        getServer().getPluginManager().registerEvents(new OnLeft(), this);
        TerminatorListener.register( this);
        //getServer().getPluginManager().registerEvents(new OnJoin(), this);
        //new vanish.Reload().runTaskTimer(this, 0L, 60L);

        //Duration checkInterval = Duration.ofMinutes(1);

        //new UpdateChecker(this, 111796, checkInterval);
        //UpdateChecker.checkForUpdates();
        new UpdateChecker(this, UpdateCheckSource.SPIGOT, "111796") // The Spigot ID of your plugin
                .setDownloadLink("https://www.spigotmc.org/resources/staffsystem-like-coralmc.111796")
                .checkNow()
                .checkEveryXHours(0.1)
                .setNotifyOpsOnJoin(true);

        System.out.println("[StaffSystemReloaded] Plugin abilitato!");
        System.out.println("[StaffSystemReloaded] Una fork di ALEeMAYAyt");
        System.out.println("[StaffSystemReloaded] Crediti: scienziato1pazzo");
    }

    @Override
    public void onDisable() {

    }
}
