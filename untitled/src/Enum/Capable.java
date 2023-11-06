package Enum;

/**
 * Capable Interface
 * contains 3 instances to be implemented by other classes
 */
public interface Capable {
    boolean hasCapability(Enum<?> capability);
    void addCapability(Enum<?> capability);
    void removeCapability(Enum<?> capability);
}
