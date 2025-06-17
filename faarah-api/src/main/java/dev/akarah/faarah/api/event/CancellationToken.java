package dev.akarah.faarah.api.event;

public class CancellationToken {
    boolean cancelled;

    private CancellationToken(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public static CancellationToken of() {
        return new CancellationToken(false);
    }

    public CancellationToken setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        return this;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
