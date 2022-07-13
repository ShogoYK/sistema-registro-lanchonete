package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton Compra = new JButton("Compra");
		Compra.setBounds(77, 101, 85, 21);
		frame.getContentPane().add(Compra);
		Compra.addActionListener(e->{
			compraIndex();
		});
		
		JButton Venda = new JButton("Venda");
		Venda.setBounds(235, 101, 85, 21);
		frame.getContentPane().add(Venda);
		Venda.addActionListener(e->{
			vendaIndex();
		});
	}
	
	private void vendaIndex() {
		VendaView window = new VendaView();
		window.frame.setVisible(true);		
	}

	private void compraIndex() {
		CompraView window = new CompraView();
		window.frame.setVisible(true);
	}
}
