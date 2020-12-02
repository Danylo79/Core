package dev.dankom.core.menu;

import dev.dankom.core.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class ListedMenu extends Menu {

    private List<Menu> pages = new ArrayList<>();
    public int currentPage = 0;

    public ListedMenu(String title, boolean canTake, int amtOfSlots) {
        super(title, canTake, amtOfSlots);
    }

    public List<Menu> getPages() {
        return pages;
    }

    public Menu getPage() {
        return getPages().get(currentPage);
    }

    public void nextPage(Profile profile) {
        if (pages.get(currentPage + 1) != null) {
            currentPage += 1;
            pages.get(currentPage + 1).openInv(profile);
        }
    }
}
