package net.allthemods.allthetweaks.client.discord;

import net.allthemods.allthetweaks.api.ATT;

public enum PackMode {
    ATM11(
            "All The Mods 11",
            "allthemods",
            1495847949358465084L,
            "https://www.curseforge.com/minecraft/modpacks/all-the-mods-11"
    );
    
    private final String windowTitle;
    private final String iconPath; // appended with _16x16.png and _32x32.png
    private final long applicationId;
    private final String curseforgeUrl;
    
    PackMode(String windowTitle, String iconPath, long applicationId, String curseforgeUrl) {
        this.windowTitle = windowTitle;
        this.iconPath = iconPath;
        this.applicationId = applicationId;
        this.curseforgeUrl = curseforgeUrl;
    }
    
    public String getWindowTitle() {
        return this.windowTitle;
    }
    
    public String getIcon16() {
        return String.format("/assets/%s/icons/%s_16x16.png", ATT.MOD_ID, this.iconPath);
    }
    
    public String getIcon32() {
        return String.format("/assets/%s/icons/%s_32x32.png", ATT.MOD_ID, this.iconPath);
    }
    
    public long getApplicationId() {
        return this.applicationId;
    }
    
    public String getCurseforgeUrl() {
        return this.curseforgeUrl;
    }
}