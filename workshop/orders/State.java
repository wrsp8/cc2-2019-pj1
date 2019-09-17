package workshop.orders;


/** Representa el estado de una orden. */
public enum State {
    /** Cuando una orden está esperando a ser procesada. */
    WAITING,
    /** Cuando una orden está siendo procesada. */
    PROCESSING,
    /** Cuando una orden ya ha sido procesada. */
    DONE;
}
