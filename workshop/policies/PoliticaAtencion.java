package workshop.policies;

public abstract class PoliticaAtencion implements AdminOrdenes {
    protected int ordenesEnAtencion;
    protected int ordenesEnCola;
    protected int ordenesAtendidas;

    public PoliticaAtencion() {
        this.ordenesEnAtencion = this.ordenesEnCola = this.ordenesAtendidas = 0;
    }

    public final int getCantidadEnAtencion() {
        return this.ordenesEnAtencion;
    }

    public final int getSize() {
        return this.ordenesEnCola;
    }

    public final int getAtendidas() {
        return this.ordenesAtendidas;
    }
}