package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;	

public class Compra {
	
	private Date dataCompra;
	private double valorTotal;
	private int idCompra;
	private List<ItemCompra> listaItens = new ArrayList<>();
 	
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	
	public List<ItemCompra> getListaItens() {
		return listaItens;
	}
	public void setListaItens(List<ItemCompra> listaItens) {
		this.listaItens = listaItens;
	}
	
	public void adicionarIngrediente(Ingrediente ingrediente, int quant, double preco) {
		ItemCompra itemCompra = new ItemCompra();
		itemCompra.setIdCompra(this);
		itemCompra.setIngrediente(ingrediente);
		itemCompra.setQuant(quant);
		
		this.getListaItens().add(itemCompra);
		this.setValorTotal(this.getValorTotal()+(quant*preco));
	}
	
	
	
}
