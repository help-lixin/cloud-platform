package help.lixin.starlink.plugin.gitlab.dto.group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum AccessLevel {
    GUEST(10), REPORTER(20), DEVELOPER(30),  MAINTAINER(40), OWNER(50);
    public final Integer value;
    AccessLevel(int value) {
        this.value = value;
    }

    private static Map<Integer, AccessLevel> valuesMap = new HashMap<Integer, AccessLevel>(9);
    static {
        for (AccessLevel accessLevel : AccessLevel.values())
            valuesMap.put(accessLevel.value, accessLevel);

        // Make sure MAINTAINER is mapped to 40 and not MASTER (MASTER is deprecated)
        valuesMap.put(MAINTAINER.value, MAINTAINER);
    }

    @JsonCreator
    public static AccessLevel forValue(Integer value) {

        AccessLevel level = valuesMap.get(value);
        if (level != null) {
            return (level);
        }
        return (value == null ? null : GUEST);
    }

    @JsonValue
    public Integer toValue() {
        return (value);
    }

    @Override
    public String toString() {
        return (value.toString());
    }
}
