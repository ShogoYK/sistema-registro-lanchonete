package controller;

import java.sql.Date;

import dao.ItemVendaDAO;
import dao.LancheDAO;
import dao.VendaDAO;
import model.ItemVenda;
import model.Lanche;
import model.Venda;

public class VendaController {
	
	
	public static Venda venda = new Venda();
	
	public static void adicionarProduto(Lanche lanche, int quant) {
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setIdVenda(venda);
		itemVenda.setLanche(lanche);
		itemVenda.setQuant(quant);
		itemVenda.setValor(quant*lanche.getPreco());
		
		venda.getItensVenda().add(itemVenda);
		venda.setValorTotal(venda.getValorTotal()+itemVenda.getValor());
	}
	
	public static void finalizarVenda(String pagamento){
		venda.setFormaPagamento(pagamento);
		venda.setDataVenda(new Date(System.currentTimeMillis()));
		VendaDAO.save(venda);
		for(ItemVenda i: venda.getItensVenda()) {
			ItemVendaDAO.salvarVenda(i);
			
		}
	}
	
	
}
