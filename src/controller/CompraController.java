package controller;

import java.sql.Date;

import dao.CompraDAO;
import dao.ItemCompraDAO;
import model.Compra;
import model.Ingrediente;
import model.ItemCompra;

public class CompraController {
	
	public static Compra compra = new Compra();
	
	public static void adicionarIngrediente(Ingrediente ingrediente, int quant, double valorUnit) {
		ItemCompra itemCompra = new ItemCompra();
		itemCompra.setIdCompra(compra);
		itemCompra.setIngrediente(ingrediente);
		itemCompra.setQuant(quant);
		itemCompra.setValor(valorUnit);
		
		compra.getListaItens().add(itemCompra);
		compra.setValorTotal(compra.getValorTotal()+(itemCompra.getValor()*itemCompra.getQuant()));
	}
	
	public static void finalizarCompra(){
		compra.setDataCompra(new Date(System.currentTimeMillis()));
		CompraDAO.save(compra);
		for(ItemCompra i: compra.getListaItens()) {
			ItemCompraDAO.salvarItem(i);
		}
	}
}
