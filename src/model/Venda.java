package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	private int id;
	private Date dataVenda;
	private double valorTotal;
	private String formaPagamento;
	private List<ItemVenda> itensVenda = new ArrayList<>();
	
	
	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}
	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double d) {
		this.valorTotal = d;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
}
