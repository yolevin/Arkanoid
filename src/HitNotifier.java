/**
 * The interface 'HitNotifier' defines methods for notifying listeners when a hit is occurred.
 * @author Yotam Levin
 * ID: 313248916
 */
public interface HitNotifier {

    /**
     * adds a listener to the list of listeners.
     * @param hl the hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * removes a listener from the list of listeners.
     * @param hl the listener to remove.
     */
    void removeHitListener(HitListener hl);
}