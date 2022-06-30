package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ParaYatirma extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String tutar;
	String hesapId;

	static Musteri musteri = new Musteri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaYatirma frame = new ParaYatirma(musteri);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @param musteri
	 */
	public ParaYatirma(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tutar Giriniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 59, 124, 40);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(173, 67, 163, 29);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblHesapid = new JLabel("HesapId");
		lblHesapid.setHorizontalAlignment(SwingConstants.CENTER);
		lblHesapid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHesapid.setBounds(10, 9, 124, 40);
		contentPane.add(lblHesapid);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(173, 17, 163, 29);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Para Yat\u0131r");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs;
				tutar = textField.getText();
				hesapId = textField_1.getText();
				LocalDate date = LocalDate.now();
				double bakiye = 0;

				String sql_sorgu1 = "SELECT * FROM hesap WHERE hesap_id=" + hesapId + " and musteri_id="
						+ musteri.getMusteri_id();
				rs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu1);
				try {
					while (rs.next()) {
						bakiye = (rs.getDouble("bakiye"));
					}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String sql_sorgu = "UPDATE hesap SET bakiye=" + (bakiye + Integer.parseInt(tutar)) + " where hesap_id="
						+ hesapId;
				veritabani_baglanti.update(sql_sorgu);
				String sql="INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES ("+hesapId + ", " +hesapId +",'parayatirma' ,'"+date.toString()+"',"+bakiye+","+bakiye+","+tutar +")";
				System.out.println(sql);
				veritabani_baglanti.ekle(sql);
			}
		});
		btnNewButton.setBounds(116, 127, 85, 21);
		contentPane.add(btnNewButton);
	}
}
