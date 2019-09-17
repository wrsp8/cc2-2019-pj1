package workshop.policies;

import workshop.orders.PaintOrder;


/** Interfaz con métodos que debería de tener un administrador de órdenes. */
public interface OrderManager {

    /**
     * Agrega una nueva orden a la cola de atención.
     *
     * @see PaintOrder
     * @param order nueva orden a agregar
     */
    public void add(PaintOrder order);

    /**
     * Remueve la orden que ya fue atendida de la cola, dependiendo de la política de atención.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la orden que ya fue atendida.
     */
    public PaintOrder remove();

    /**
     * Retorna la siguiente orden a atender de la cola, dependiendo de la política de atención.
     *
     * @see PaintOrder
     * @return un {@link PaintOrder} que es la siguiente orden a atender.
     */
    public PaintOrder next();

}
