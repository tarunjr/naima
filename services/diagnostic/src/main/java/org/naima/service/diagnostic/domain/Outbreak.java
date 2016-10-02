package org.naima.service.diagnostic.domain;

public class Outbreak {
    private Location location;
    private Condition condition;

    public Outbreak(Location location, Condition condition) {
        this.location = location;
        this.condition = condition;
    }
    public Location getLocation() {
        return location;
    }
    public Condition getCondition() {
        return condition;
    }

}
