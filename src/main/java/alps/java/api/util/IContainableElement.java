package alps.java.api.util;
/**
 * This interface defines a bit of a loose hierarchy for the pass process model elements.
 * A class can implement it and define which class its "container parent" is.
 * I.e. a State might implement IContainableElement, because states are always contained
 * inside a behavior. if the state is added to a behavior, the behavior can set itself as container
 * while only checking if the given IPASSProcessModelElement is IContainableElement,
 * it does not need to know it is a specific element (a state)
 * @param <T>
 */
public interface IContainableElement<T> {
    /**
     * Sets the container for this element
     * @param container the container class
     */
    void setContainedBy(T container);

    /**
     * Returns the container this element belongs to
     * "out" method
     * @return true if the container is not null and the element is currently contained by another instance
     */
    T getContainedBy();

    /**
     * Removes this element from its container
     */
    void removeFromContainer();
}
