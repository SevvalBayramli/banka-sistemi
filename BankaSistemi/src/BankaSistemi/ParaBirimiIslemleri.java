package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParaBirimiIslemleri extends JFrame {

	private JPanel contentPane;

	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();

	Object[] kolonlar = { "para_birimi_id","para_birimi_adi", "kur_degeri" };
	Object[] satirlar = new Object[3];
	private JTextField textad;
	private JTextField textsoyad;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaBirimiIslemleri frame = new ParaBirimiIslemleri();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ParaBirimiIslemleri() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 771, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(21, 10, 443, 163);
		contentPane.add(scrollPane);

		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);// scrollPane ile tabloyu bağladık ve kolon bilgileri gözükmeye başladı

		JButton btn1 = new JButton("Listele");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM para_birimi");

				try {
					while (myRs.next()) {
						satirlar[0] = myRs.getString("para_birimi_id");
						satirlar[1] = myRs.getString("para_birimi_adi");
						satirlar[2] = myRs.getString("kur_degeri");

						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(485, 8, 85, 21);
		contentPane.add(btn1);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(552, 39, 96, 19);
		contentPane.add(id);

		textad = new JTextField();
		textad.setBounds(552, 65, 96, 19);
		contentPane.add(textad);
		textad.setColumns(10);

		textsoyad = new JTextField();
		textsoyad.setBounds(552, 94, 96, 19);
		contentPane.add(textsoyad);
		textsoyad.setColumns(10);

		JButton btnNewButton = new JButton("kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ad, soyad, sqlsorgu;

				ad = textad.getText();
				soyad = textsoyad.getText();

				sqlsorgu = "INSERT INTO para_birimi(para_birimi_adi,kur_degeri) VALUES (  '" + ad + "' ," + "'" + soyad
						+ "')";
				System.out.println(sqlsorgu);
				veritabani_baglanti.ekle(sqlsorgu);
			}
		});
		btnNewButton.setBounds(474, 123, 85, 21);
		contentPane.add(btnNewButton);

		lblNewLabel_1 = new JLabel("para birimi ad");
		lblNewLabel_1.setBounds(474, 68, 96, 13);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("kur degeri");
		lblNewLabel_2.setBounds(474, 97, 68, 13);
		contentPane.add(lblNewLabel_2);

		btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad, soyad, sqlsorgu,no;
				no=id.getText();
				ad = textad.getText();
				soyad = textsoyad.getText();

				sqlsorgu = "UPDATE para_birimi SET para_birimi_adi=  '" + ad + "' ,kur_degeri=" + soyad+" where para_birimi_id="+no;
				System.out.println(sqlsorgu);
				veritabani_baglanti.update(sqlsorgu);
			}
		});
		btnNewButton_1.setBounds(563, 123, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("para birimi id");
		lblNewLabel_1_1.setBounds(474, 39, 96, 13);
		contentPane.add(lblNewLabel_1_1);
		
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText((String) modelim.getValueAt(table.getSelectedRow(), 0));
				textad.setText((String) modelim.getValueAt(table.getSelectedRow(), 1));
				textsoyad.setText((String) modelim.getValueAt(table.getSelectedRow(), 2));
				
			}
		});

		// contentPane.add(table);
	}
}
