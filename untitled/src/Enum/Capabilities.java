package Enum;

import java.util.HashSet;
import java.util.Set;

/**
 * Capabilities class
 */
public class Capabilities implements Capable {

    private Set<Object> capabilitySet = new HashSet<Object>();

    /**
     * hasCapability() method to check whether theres a capability
     * @param capability capability
     * @return boolean value of whether the capability is present
     */
    public boolean hasCapability(Enum<?> capability) {
        return capabilitySet.contains(capability);
    }

    /**
     * addCapability() method to add capability
     * @param capability capability
     */
    public void addCapability(Enum<?> capability) {
        capabilitySet.add(capability);
    }

    /**
     * removeCapability() method to remove the capability
     * @param capability capability
     */
    public void removeCapability(Enum<?> capability) {
        capabilitySet.remove(capability);
    }
}
