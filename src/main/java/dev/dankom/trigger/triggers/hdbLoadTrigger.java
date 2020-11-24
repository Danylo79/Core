package dev.dankom.trigger.triggers;

import dev.dankom.trigger.Trigger;
import me.arcaniax.hdb.api.HeadDatabaseAPI;

public class hdbLoadTrigger extends Trigger {
    private HeadDatabaseAPI headDatabaseAPI;

    public hdbLoadTrigger(HeadDatabaseAPI headDatabaseAPI) {
        super("hdb-load");
        this.headDatabaseAPI = headDatabaseAPI;
    }

    public HeadDatabaseAPI getHeadDatabaseAPI() {
        return headDatabaseAPI;
    }
}
