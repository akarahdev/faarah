package dev.akarah.faarah.api.util;

public class FinePosition {
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    private FinePosition(double x, double y, double z, float pitch, float yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public static FinePosition of(double x, double y, double z) {
        return new FinePosition(x, y, z, 0.0f, 0.0f);
    }

    public static FinePosition of(double x, double y, double z, float pitch, float yaw) {
        return new FinePosition(x, y, z, pitch, yaw);
    }

    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    public float pitch() {
        return this.pitch;
    }

    public float yaw() {
        return this.yaw;
    }

    public FinePosition add(FinePosition other) {
        return new FinePosition(
                this.x() + other.x(),
                this.y() + other.y(),
                this.z() + other.z(),
                this.pitch() + other.pitch(),
                this.yaw() + other.yaw()
        );
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + ", " + this.z + " @ " + this.pitch + ", " + this.yaw + "]";
    }
}
