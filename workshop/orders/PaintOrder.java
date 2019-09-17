package workshop.orders;


/** Representa de forma general una orden de pintura. Todas las demás órdenes deben de heredar de esta clase. */
public abstract class PaintOrder {

    /** Número de orden. */
    protected int number;
    /** Número de placa. */
    protected String plate;
    /** Estado de la orden. */
    protected State state;
    /** Cantidad de tiempo por pieza. */
    protected double time;
    /** Número total de piezas a pintar. */
    protected int total;
    /** Cantidad de piezas pintadas al momento. */
    protected int painted;

    /**
     * Falta ver que poner aqui.
     *
     * @param number número de orden
     * @param plate número de placa
     * @param total número total de piezas a pintar
     * @param time cantidad de tiempo por pieza
     */
    protected PaintOrder(int number, String plate, int total, double time) {
        this.number = number;
        this.plate = plate;
        this.total = total;
        this.time = time;
        painted = 0;
        state = State.WAITING;
    }

    /**
     * Retorna el tipo de orden.
     *
     * @return un String que representa el tipo de orden. Ejemplo  {@code "sedan"}.
     */
    public abstract String getType();

    /**
     * Retorna el precio dependiendo el tipo de orden. Este método debería
     * de devolver un String {@code "?"} cuando la orden no está finalizada, de lo
     * contrario un String con el precio total de la orden.
     *
     * @return un String que representa el precio total de la orden. Ejemplo {@code "1200"}.
     */
    public abstract String getPrice();

    /**
     * Método que finaliza la órden. Debe cambiar el estado de la misma
     * */
    public abstract void end();

    /**
     * Método que cambia el estado de la orden a espera.
     * */
    public void waiting() {
        this.state = State.WAITING;
    }

    /**
     * Se utiliza para pintar una cantidad de piezas de la orden.
     * Este método recibe la cantidad de piezas a pintar.
     * Cambia el estado de la orden
     * @param pieces Cuántas piezas se deben pintar.
     *
     * @throws Exception si la cantidad de piezas es negativa, o mayor a las restantes.
     * */
    public final void paint(int pieces) throws Exception {
        if (pieces > (this.total - this.painted) || pieces <= 0) {
            throw new Exception("Error, cantidad de piezas por pintar incorrecta");
        }
        this.state = State.WAITING;
        this.painted = this.painted + pieces;
    }

    /**
     * Retorna el número de orden.
     *
     * @return un int que representa el número de orden.
     */
    public final int getOrder() {
        return number;
    }

    /**
     * Retorna la cantidad de piezas pintadas de la orden hasta el momento.
     *
     * @return un int que representa la cantidad de piezas pintadas de la orden hasta el momento.
     */
    public final int getPainted() {
        return painted;
    }

    /**
     * Retorna la cantidad de piezas a pintar en total.
     *
     * @return un int que representa la cantidad de piezas a pintar en total.
     */
    public final int getTotal() {
        return total;
    }

    /**
     * Retorna la cantidad de tiempo que se toma en pintar una pieza.
     *
     * @return un double que representa la cantidad de tiempo que se toma en pintar una pieza.
     */
    public final double getTime() {
        return time;
    }

    /**
     * Retorna la placa del vehículo.
     *
     * @return un String que representa la placa del vehículo.
     */
    public final String getLicensePlate() {
        return plate;
    }

    /**
     * Retorna el estado actual de la orden.
     *
     * @see State
     * @return un {@link State} que representa el estado actual de la orden.
     */
    public final State getState() {
        return state;
    }

    /** {@inheritDoc} */
    @Override
    public final String toString() {
        return String.format("[#%d - place=%s - piezas=%d/%d - estado=%s - tipo=%s - pago=Q%s]",
            number,
            plate,
            painted,
            total,
            state,
            getType(),
            getPrice()
        );
    }

}
