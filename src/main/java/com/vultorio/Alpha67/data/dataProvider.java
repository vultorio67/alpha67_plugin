package com.vultorio.Alpha67.data;

import com.archyx.aureliumskills.AureliumSkills;
import com.vultorio.Alpha67.Alpha67;
import org.bukkit.entity.Player;

import java.io.IOException;

public abstract class dataProvider {

    public final Alpha67 plugin;

    public dataProvider(Alpha67 plugin) {
        this.plugin = plugin;
    }



    public void save(Player player) {
        save(player, true);
    }

    public abstract void save(Player player, boolean removeFromMemory);
}
