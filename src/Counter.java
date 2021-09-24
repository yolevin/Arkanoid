/**
 * The class 'Counter' is in charge of counting different things.
 * @author Yotam Levin
 * ID: 313248916
 */
public class Counter {
    private int value;

    /**
     * Constructor method.
     * @param value the counter's initial value.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Constructor method.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Add a number to the current count.
     * @param number a number to add to the count.
     */
    void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * Subtract a number from the current count.
     * @param number a number to subtract from the count.
     */
    void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * Get method - the current count.
     * @return the current count.
     */
    int getValue() {
        return this.value;
    }
}