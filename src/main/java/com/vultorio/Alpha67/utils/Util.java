package com.vultorio.Alpha67.utils;

import java.util.ArrayList;
import java.util.List;

public final class Util {

    //pour le GUI
    public static List<String> ModList() {
        String[] array = {"Mods World","Iron Chest" , "Storage Drawers", "Tinker's Construct", "Cyclic", "Decorative block", "Elevator Block", "Fuze Relics", "Pretty Pipes", "MrCrayfish's Furniture Mod", "Iron Jetpack", "Ender Storage", "Create", "Immersive Engineering", "Mystical Agriculture", "ActuallyAdditions", "Environemental Tech", "All Thermal Mod", "All mekanism", "All RFtools", "Solar Flux Reborn", "applied energistics 2", "Draconic Evolution"};
        List<String> mod = new ArrayList<String>();
        for (String lang : array) {
            mod.add(lang);
        }
        return mod;
    }

    //pour le check de craft et de breakblock
    public static List<String> ModListId() {
        String[] array = {"Mods World", "IRONCHEST", "STORAGE", "CONSTRUCT", "CYCLIC", "DECORATIVE", "ELEVATOR", "FUZE", "PIPES", "FURNI", "JETPACK", "ENDER", "CREATE", "IMMERSIVE", "MYSTICALAGRICULTURE", "ADDITIONS", "ENVIRON", "THERMAL", "MEKANISM", "RFTOOLS", "SOLARFLUX", "APPLIED", "DRACONIC"};
        List<String> modId = new ArrayList<String>();
        for (String lang : array) {
            modId.add(lang);
        }
        return modId;
    }

}
