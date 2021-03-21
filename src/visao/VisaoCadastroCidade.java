package visao;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Cidade;
import servicos.Servicos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VisaoCadastroCidade extends JFrame {

	private JPanel contentPane;
	private JTextField textCidade;

	public VisaoCadastroCidade() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int resposta = Servicos.isValidaSair(0);
				if( resposta == 0) {
					dispose();
					VisaoCadastroCidade frameCidade = new VisaoCadastroCidade();
					frameCidade.setVisible(true);
				} else if( resposta == 1)
					System.exit(0);
			}
		});
		
		setTitle("ELEICOES 2020");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 43, 166, 164);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 166, 164);
		panel.add(label);
		label.setIcon(new ImageIcon(VisaoCadastroCidade.class.getResource("/imagens/cidade.png")));
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setForeground(Color.BLACK);
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblCidade.setFont(new Font("Impact", Font.PLAIN, 19));
		lblCidade.setBounds(186, 147, 215, 20);
		contentPane.add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setBounds(186, 124, 215, 20);
		contentPane.add(textCidade);
		textCidade.setColumns(10);
		
		JButton btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textCidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Voce precisa preencher o nome da cidade para continuar", "Campo vazio",JOptionPane.ERROR_MESSAGE);
				} else if(validacao.Validacao.validaNome(textCidade.getText().trim()) == true) {
					Cidade candidatos = new Cidade(validacao.Validacao.nomeCidade(textCidade.getText()));
					VisaoMenu frameMenu = new VisaoMenu(candidatos);
					setVisible(false);
					frameMenu.setVisible(true);
				}
			}
		});
		
		btnProximo.setBackground(Color.LIGHT_GRAY);
		btnProximo.setBounds(326, 178, 75, 72);
		btnProximo.setToolTipText("Avançar");
		btnProximo.setIcon(new ImageIcon(VisaoCadastroCidade.class.getResource("/imagens/proximo.png")));
		contentPane.add(btnProximo);
	}
}
