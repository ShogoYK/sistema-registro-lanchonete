package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.CompraController;
import dao.IngredienteDAO;
import model.Compra;
import model.Fornecedor;
import model.Ingrediente;
import model.ItemCompra;

public class CompraView {

	JFrame frame;
	private JTextField quantField;
	private JLabel itensLabel;
	private JLabel totalLabel;
	private JComboBox <String> fornecedor;
	private JComboBox<Ingrediente> comboBox;
	private JTextField precoUnidade;

	/**
	 * Create the application.
	 * @return 
	 */
	public CompraView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 409);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fornecedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(44, 28, 163, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(480, 323, 95, 27);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(e->{
			finalizarCompra();
		});
		
		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(450, 138, 125, 27);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(e -> {
			adicionarItemClick();
			});
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o ingrediente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(32, 80, 175, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		totalLabel = new JLabel("Total: ");
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		totalLabel.setBounds(44, 310, 349, 20);
		frame.getContentPane().add(totalLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Quantidade");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(394, 50, 85, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		quantField = new JTextField();
		quantField.setBounds(394, 82, 76, 27);
		frame.getContentPane().add(quantField);
		quantField.setColumns(10);
		
		itensLabel = new JLabel("Ingredientes:");
		itensLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		itensLabel.setAutoscrolls(true);
		itensLabel.setVerticalTextPosition(SwingConstants.TOP);
		itensLabel.setVerticalAlignment(SwingConstants.TOP);
		itensLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		itensLabel.setBounds(55, 139, 383, 161);
		frame.getContentPane().add(itensLabel);
		
		fornecedor = new JComboBox<String>();
		fornecedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fornecedor.setModel(new DefaultComboBoxModel<String>(new String[] {null, "Rei Lingui\u00E7a", "Pastelanches", "Carne & Cia", "Massas massas"}));
		fornecedor.setBounds(168, 32, 142, 31);
		frame.getContentPane().add(fornecedor);
		
		JButton limpar = new JButton("Limpar");
		limpar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		limpar.setBounds(480, 273, 95, 27);
		frame.getContentPane().add(limpar);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(217, 79, 166, 27);
		frame.getContentPane().add(comboBox);
		
		precoUnidade = new JTextField();
		precoUnidade.setColumns(10);
		precoUnidade.setBounds(490, 82, 76, 27);
		frame.getContentPane().add(precoUnidade);
		
		JLabel lblNewLabel_3_1 = new JLabel("Pre\u00E7o/Unidade");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(490, 50, 110, 22);
		frame.getContentPane().add(lblNewLabel_3_1);
		limpar.addActionListener(e->{
			limparCompra();
		});
		adicionarIngrediente();
	}
	
	private void atualizarItens() {
		String mensagem = "";
		
		for(ItemCompra i: CompraController.compra.getListaItens()) {
			mensagem+=i.getIngrediente().getNomeIngrediente()+" "+i.getQuant()+"x "+i.getValor()+" = " +i.getQuant()*i.getValor()+"<br>";
			}
		itensLabel.setText("<html> itens: <br>"+mensagem+"</html>");
		quantField.setText("");	
		precoUnidade.setText("");
		totalLabel.setText("Total: R$"+String.format("%.2f",CompraController.compra.getValorTotal()));
	}
	
	private void adicionarIngrediente() {
		ArrayList<Ingrediente> lista = IngredienteDAO.readAll();
		lista.add(0, null);
		Ingrediente[] vetor = new Ingrediente[lista.size()];	
		lista.toArray(vetor);
		comboBox.setModel(new DefaultComboBoxModel<Ingrediente>(vetor));
		
	}

	private void adicionarItemClick() {
		Ingrediente ingrediente = (Ingrediente) comboBox.getSelectedItem();
		if(ingrediente == null) {
			JOptionPane.showMessageDialog(frame, "Selecione uma opção de ingrediente");
			return;
		}
		
		try {
			int quant = Integer.parseInt(quantField.getText());
			double preco = Double.parseDouble(precoUnidade.getText());
			CompraController.adicionarIngrediente(ingrediente, quant, preco);
			System.out.println(CompraController.compra.getListaItens());
			atualizarItens();
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Insira uma quantidade");
			return;
		}
	}
	
	private void finalizarCompra(){
		String fornecedorSelecionado = (String) fornecedor.getSelectedItem();
		String confirmar = "Deseja finalizar sua compra?";
		
		
		if(fornecedorSelecionado == null) {
			JOptionPane.showMessageDialog(frame, "Selecione um fornecedor");
			return;
		}
		if(CompraController.compra.getListaItens().size()<=0) {
			JOptionPane.showMessageDialog(frame, "Adicione um ingrediente");
			return;
		}
		
		int opcao = JOptionPane.showConfirmDialog(frame, confirmar, "Tela de confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(opcao == JOptionPane.YES_OPTION) {
			CompraController.finalizarCompra();
			atualizarItens();
			limparCompra();
			JOptionPane.showMessageDialog(frame, "Compra cadastrada com sucesso");
		}
}

	private void limparCompra() {
		quantField.setText("");
		precoUnidade.setText("");
		CompraController.compra = new Compra();
		atualizarItens();
		comboBox.setSelectedIndex(0);
		fornecedor.setSelectedIndex(0);
	}
}
