package workshop.policies;


/** Representa de forma general una política de atención. Todas las demás políticas deben de heredar de esta clase. */
public abstract class Policy implements OrderManager {

    /** Cantidad de órdenes que están siendo atendidas. */
    protected int currentOrders;
    /** Cantidad de órdenes que están en cola esperando a ser atendidas. */
    protected int queuedOrders;
    /** Cantidad de órdenes que ya han sido atendidas. */
    protected int processedOrders;

    /** Crea una nueva pólitica de atención. Inicializa los campos de la clase a {code 0}. */
    protected Policy() {
        currentOrders = queuedOrders = processedOrders = 0;
    }

    /**
     * Retorna la cantidad de órdenes que están siendo atendidas.
     *
     * @return un int con la cantidad de órdenes que están siendo atendidas.
     */
    public final int getCurrentOrders() {
        return currentOrders;
    }

    /**
     * Retorna la cantidad de órdenes que están en la cola esperando a ser atendidas.
     *
     * @return un int con la cantidad de órdenes que están en la cola esperando a ser atendidas.
     */
    public final int getQueuedOrders() {
        return queuedOrders;
    }

    /**
     * Retorna la cantidad de órdenes que ya han sido atendidas.
     *
     * @return un int con la cantidad de órdenes que ya han sido atendidas.
     */
    public final int getProcessedOrders() {
        return processedOrders;
    }

}
