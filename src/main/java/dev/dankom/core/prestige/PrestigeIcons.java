package dev.dankom.core.prestige;

import dev.dankom.core.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class PrestigeIcons {
    private static List<PrestigeIcon> prestigeIcons = new ArrayList<>();

    public static List<PrestigeIcon> getPrestigeIcons() {
        List<PrestigeIcon> out = new ArrayList<>();
        //Cards
        out.add(new PrestigeIcon("♦", "Diamond") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getGuild().getLvl() >= 10;
            }

            @Override
            public String getNeededToUnlock() {
                return "Guild Level 10+";
            }
        });
        out.add(new PrestigeIcon("♣", "Club") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getGuild().getLvl() >= 20;
            }

            @Override
            public String getNeededToUnlock() {
                return "Guild Level 20+";
            }
        });
        out.add(new PrestigeIcon("♠", "Spade") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getGuild().getLvl() >= 100;
            }

            @Override
            public String getNeededToUnlock() {
                return "Guild Level 100+";
            }
        });
        out.add(new PrestigeIcon("♥", "Heart") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.getGuild().getLvl() >= 1000;
            }

            @Override
            public String getNeededToUnlock() {
                return "Guild Level 1000+";
            }
        });

        //Level Based
        out.add(new PrestigeIcon("✟", "Level 5") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 5;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 5+";
            }
        });
        out.add(new PrestigeIcon("☢", "Level 10") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 10;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 10+";
            }
        });
        out.add(new PrestigeIcon("⚔", "Level 50") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 50;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 50+";
            }
        });
        out.add(new PrestigeIcon("⌨", "Level 100") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 100;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 100+";
            }
        });
        out.add(new PrestigeIcon("♛", "Level 500") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 500;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 500+";
            }
        });
        out.add(new PrestigeIcon("☾", "Level 1000") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return (int) profile.get("network.level") >= 1000;
            }

            @Override
            public String getNeededToUnlock() {
                return "Level 1000+";
            }
        });

        //Achievement Prestige
        //Coin Master
        out.add(new PrestigeIcon("¢", "Amateur Merchant") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.isAchievementUnlocked("coin_master_I");
            }

            @Override
            public String getNeededToUnlock() {
                return "Amateur Merchant Achievement";
            }
        });
        out.add(new PrestigeIcon("$", "Wealthy Merchant") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.isAchievementUnlocked("coin_master_II");
            }

            @Override
            public String getNeededToUnlock() {
                return "Wealthy Merchant Achievement";
            }
        });
        out.add(new PrestigeIcon("฿", "Filthy Rich Merchant") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.isAchievementUnlocked("coin_master_III");
            }

            @Override
            public String getNeededToUnlock() {
                return "Filthy Rich Merchant Achievement";
            }
        });
        //Omega
        out.add(new PrestigeIcon("Ω", "Omega") {
            @Override
            public boolean isUnlocked(Profile profile) {
                return profile.isAchievementUnlocked("omega");
            }

            @Override
            public String getNeededToUnlock() {
                return "Omega Achievement";
            }
        });
        return out;
    }
}
