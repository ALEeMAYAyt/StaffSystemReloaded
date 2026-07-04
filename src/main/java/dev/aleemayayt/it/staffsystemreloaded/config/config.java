package dev.aleemayayt.it.staffsystemreloaded.config;

import org.bukkit.configuration.Configuration;

import static dev.aleemayayt.it.staffsystemreloaded.StaffSystem.*;

public class config {

    public static Configuration configuration = StaffSystem.getConfig();

    public static Configuration getC(){
        return configuration;
    }

    public static void reload(){ StaffSystem.reloadConfig();  }
    public static void save(){
        StaffSystem.saveConfig();
    }
    public static void create(){
        StaffSystem.getConfig().options().copyDefaults(true);
        StaffSystem.saveConfig();
    }


}
