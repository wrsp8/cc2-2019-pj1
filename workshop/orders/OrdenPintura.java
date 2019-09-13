package workshop.orders;

public abstract class OrdenPintura {

	public enum Estado {
	    ESPERA,
	    EN_PROCESO,
	    TERMINADA
	 }	

	protected int numOrden;
	protected int piezasPintadas;
	public int piezasTotales;
	protected double tiempoPieza;
	protected String placa;
	protected Estado estado;

	public OrdenPintura(int numOrden, int numPiezas, double tiempo, String placa) {
		this.numOrden = numOrden;
		this.piezasPintadas = 0;
		this.piezasTotales = numPiezas;
		this.tiempoPieza = tiempo;
		this.placa = new String(placa);
		this.estado = OrdenPintura.Estado.ESPERA;
	}

	public final int getOrden() {
		return this.numOrden;
	}

	public final int getPiezasPintadas() {
		return this.piezasPintadas;
	}

	public final int getTotalPiezas() {
		return this.piezasTotales;
	}

	public final double tiempoPorPieza() {
		return this.tiempoPieza;
	}

	public final String getPlacaVehiculo() {
		return this.placa;
	}

	public final Estado getEstado() {
		return this.estado;
	}

	public void esperar() {
		this.estado = OrdenPintura.Estado.ESPERA;
	}

	public void pintar(int cantPiezas) throws Exception {
		if (cantPiezas > (this.piezasTotales - this.piezasPintadas) || cantPiezas <= 0) {
			throw new Exception("Error, cantidad de piezas por pintar incorrecta");
		}
		this.estado = OrdenPintura.Estado.EN_PROCESO;
		this.piezasPintadas = this.piezasPintadas + cantPiezas;
	}
	
	public abstract void finalizar();

	public abstract String getTipo();

	public abstract String getPrecio();

	@Override
	public final String toString() {
		return String.format("[#%d - placa=%s - piezas=%d/%d - estado=%s - tipo=%s - pago=Q%s]", 
			this.numOrden, 
			this.placa, 
			this.piezasPintadas,
			this.piezasTotales,
			this.estado, 
			this.getTipo(),
			this.getPrecio());
	}
}