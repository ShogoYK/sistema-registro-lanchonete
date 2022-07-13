package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.VendaController;
import dao.LancheDAO;
import model.ItemVenda;
import model.Lanche;
import model.Venda;

public class VendaView {

	JFrame frame;
	private JTextField quantField;
	private JLabel itensLabel;
	private JLabel totalLabel;
	private JComboBox <String> pagamento;
	private JComboBox<Lanche> comboBox;



	/**
	 * Create the application.
	 */
	public VendaView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 624, 409);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Forma de pagamento: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(45, 263, 163, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.setBounds(480, 323, 95, 27);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(e->{
			finalizarVenda();
		});
		
		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(480, 46, 125, 27);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(e -> {
			adicionarItemClick();
			});
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o produto:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(45, 44, 163, 27);
		frame.getContentPane().add(lblNewLabel_1);
		
		totalLabel = new JLabel("Total: ");
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		totalLabel.setBounds(45, 323, 349, 20);
		frame.getContentPane().add(totalLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Quantidade");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(385, 10, 85, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		quantField = new JTextField();
		quantField.setBounds(394, 46, 76, 27);
		frame.getContentPane().add(quantField);
		quantField.setColumns(10);
		
		itensLabel = new JLabel("Itens: ");
		itensLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		itensLabel.setAutoscrolls(true);
		itensLabel.setVerticalTextPosition(SwingConstants.TOP);
		itensLabel.setVerticalAlignment(SwingConstants.TOP);
		itensLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		itensLabel.setBounds(55, 81, 383, 161);
		frame.getContentPane().add(itensLabel);
		
		pagamento = new JComboBox<String>();
		pagamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pagamento.setModel(new DefaultComboBoxModel<String>(new String[] {null, "Cr\u00E9dito", "Dinheiro", "Cheque", "D\u00E9bito"}));
		pagamento.setBounds(218, 273, 135, 21);
		frame.getContentPane().add(pagamento);
		
		JButton limpar = new JButton("Limpar Venda");
		limpar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		limpar.setBounds(480, 273, 95, 27);
		frame.getContentPane().add(limpar);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(218, 49, 166, 21);
		frame.getContentPane().add(comboBox);
		limpar.addActionListener(e->{
			limparVenda();
		});
		adicionarLanche();
	}
	
	private void atualizarItens() {
		String mensagem = "";
		for(ItemVenda i: VendaController.venda.getItensVenda()) {
			mensagem+=i.getLanche().getNomeLanche()+" "+i.getQuant()+"x "+i.getValor()+"<br>";
			}
		itensLabel.setText("<html> itens: <br>"+mensagem+"</html>");
		quantField.setText("");
		totalLabel.setText("Total: R$"+String.format("%.2f",VendaController.venda.getValorTotal()));
	}
	
	private void adicionarLanche() {
		ArrayList<Lanche> lista = LancheDAO.readAll();
		lista.add(0, null);
		Lanche[] vetor = new Lanche[lista.size()];		
		lista.toArray(vetor);
		comboBox.setModel(new DefaultComboBoxModel<Lanche>(vetor));
		
	}

	private void adicionarItemClick() {
		Lanche lanche = (Lanche) comboBox.getSelectedItem();
		if(lanche == null) {
			JOptionPane.showMessageDialog(frame, "Selecione uma opção de lanche");
			return;
		}
		try {
			int quant = Integer.parseInt(quantField.getText());
			VendaController.adicionarProduto(lanche, quant);
			System.out.println(VendaController.venda.getItensVenda());
			atualizarItens();
		}catch(Exception e){
			JOptionPane.showMessageDialog(frame, "Selecione uma quantidade");
			return;
		}
	}
		
	
	private void finalizarVenda(){
		String pagamentoSelecionado = (String) pagamento.getSelectedItem();
		String confirmar = "Deseja finalizar sua compra?";
		
		if(pagamentoSelecionado == null) {
			JOptionPane.showMessageDialog(frame, "Selecione uma opção de pagamento");
			return;
		}
		
		int opcao = JOptionPane.showConfirmDialog(frame, confirmar, "Tela de confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if(opcao == JOptionPane.YES_OPTION) {
			VendaController.finalizarVenda(pagamentoSelecionado);
			atualizarItens();
			limparVenda();
			JOptionPane.showMessageDialog(frame, "Venda cadastrada com sucesso");
		}
}

	private void limparVenda() {
		quantField.setText("");
		VendaController.venda = new Venda();
		atualizarItens();
		comboBox.setSelectedIndex(0);
		pagamento.setSelectedIndex(0);
	}

	public Window getFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
