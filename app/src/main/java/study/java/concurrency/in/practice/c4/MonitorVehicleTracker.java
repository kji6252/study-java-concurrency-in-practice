package study.java.concurrency.in.practice.c4;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint mutablePoint = locations.get(id);
        return mutablePoint == null ? null : new MutablePoint(mutablePoint);
    }

    public synchronized void setLocations(String id, int x, int y) {
        MutablePoint mutablePoint = locations.get(id);
        if (mutablePoint == null) {
            throw new IllegalArgumentException("No such ID: " + id);
        }
        mutablePoint.x = x;
        mutablePoint.y = y;
    }

    private Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> locations) {
        Map<String, MutablePoint> result = new HashMap<>();
        for (String key : locations.keySet()) {
            result.put(key, new MutablePoint(locations.get(key)));
        }
        return Collections.unmodifiableMap(result);
    }
}
