package dev.dankom.util.coreHelpers;

public class CoreLocation {
    private final double x;
    private final double y;
    private final double z;

    public CoreLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
