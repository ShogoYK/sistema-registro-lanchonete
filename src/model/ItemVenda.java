package model;

public class ItemVenda {
	
	    private Venda venda;
	    private Integer quant;
	    private Double valor;
	    private Lanche lanche;

	    public ItemVenda() {
	    }

	    public ItemVenda(Venda venda, Integer quant, Lanche lanche) {
	        this.venda = venda;
	        this.quant = quant;
	        this.lanche = lanche;
	    }

	    public void CalcularValor(double valor, int quant) {
	        double cal = valor * quant;
	        setValor(cal);
	    }

	    public Venda getVenda() {
	        return venda;
	    }

	    public void setIdVenda(Venda venda) {
	        this.venda = venda;
	    }

	    public Integer getQuant() {
	        return quant;
	    }

	    public void setQuant(Integer quant) {
	        this.quant = quant;
	    }
	public Double getValor() {
	        return valor;
	    }

	    public void setValor(Double valor) {
	        this.valor = valor;
	    }

	    public Lanche getLanche() {
	        return lanche;
	    }

	    public void setLanche(Lanche lanche) {
	        this.lanche = lanche;
	    }

}
