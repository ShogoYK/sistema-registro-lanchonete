package model;

public class ItemCompra {
	private int quant;
	private Compra compra;
	private Ingrediente ingrediente;
	private double valor;
	
	public ItemCompra() {
    }

    public ItemCompra(Compra compra, Integer quant, Ingrediente ingrediente) {
        this.compra = compra;
        this.quant = quant;
        this.ingrediente = ingrediente;
    }
    public Double calcularTotal(double valor, int quant) {
    	double res = valor*quant;
    	return res;
    }

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setIdCompra(Compra compra) {
		this.compra = compra;
	}
	
	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
