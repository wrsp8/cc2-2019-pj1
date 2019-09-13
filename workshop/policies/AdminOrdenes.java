package workshop.policies;

import workshop.orders.OrdenPintura;

public interface AdminOrdenes {
    void agregar(OrdenPintura orden);

    OrdenPintura remover();

    OrdenPintura siguiente();
}