package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class ParaGonderme extends JFrame {

	private JPanel contentPane;
	private JTextField cekHesap;
	private JTextField gonHesap;
	private JTextField tutar;
	double cekBakiye = 0;
	double gonBakiye = 0;
	double bankaBakiye=0;
	static Musteri musteri = new Musteri();
	private JTextField textField;
	double cekKur,gonKur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaGonderme frame = new ParaGonderme(musteri);
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
	public ParaGonderme(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Para Gonderme");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(116, 10, 208, 39);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Paran\u0131n \u00C7ekilece\u011Fi Hesap ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(26, 59, 226, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Paran\u0131n Gonderilece\u011Fi Hesap ID");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(26, 108, 226, 35);
		contentPane.add(lblNewLabel_1_1);

		cekHesap = new JTextField();
		cekHesap.setBounds(262, 59, 106, 29);
		contentPane.add(cekHesap);
		cekHesap.setColumns(10);

		gonHesap = new JTextField();
		gonHesap.setColumns(10);
		gonHesap.setBounds(262, 112, 106, 29);
		contentPane.add(gonHesap);

		JLabel Tutar = new JLabel("Tutar");
		Tutar.setHorizontalAlignment(SwingConstants.CENTER);
		Tutar.setFont(new Font("Tahoma", Font.BOLD, 13));
		Tutar.setBounds(26, 153, 226, 35);
		contentPane.add(Tutar);

		textField = new JTextField();
		textField.setBounds(202, 162, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Para Gonderme");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs, rs1;
				LocalDate date = LocalDate.now();
				String cekId = cekHesap.getText();
				String gonId = gonHesap.getText();

				double tutar = Double.parseDouble(textField.getText());

				//String sql_sorgu = "SELECT * FROM hesap WHERE hesap_id=" + cekId + " and musteri_id="
						//+ musteri.getMusteri_id();
				String sql_sorgu2="select * from hesap ,para_birimi where hesap_id="+cekId+" and hesap.musteri_id="+musteri.getMusteri_id()+" and hesap.parabirimi_id=para_birimi.para_birimi_id;";
				String sql_sorgu1 = "select * from hesap ,para_birimi where hesap_id="+gonId+" and hesap.parabirimi_id=para_birimi.para_birimi_id;";

				rs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu2);
				rs1 = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu1);

				boolean flag = false;
				try {

					while (rs.next()) {
						cekBakiye = rs.getDouble("bakiye");
						cekKur=rs.getDouble("kur_degeri");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					while (rs1.next()) {
						gonBakiye = rs1.getDouble("bakiye");
						gonKur=rs1.getDouble("kur_degeri");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("++++"+tutar);

				if (cekBakiye < tutar) {
					JOptionPane.showMessageDialog(null, "Yetersiz Bakiye", "Hata", 2);
					System.out.println(cekBakiye + "-" + tutar);
					flag=true;
				} else  {
					

					String sql_sorgu5 = "UPDATE hesap SET bakiye= " + (cekBakiye - (tutar)) + " WHERE hesap_id="
							+ cekId;
					
					tutar*=cekKur;
					tutar/=gonKur;
					
					String sql_sorgu3 = "UPDATE hesap SET bakiye= " + (gonBakiye + tutar) + " WHERE hesap_id=" + gonId;

					veritabani_baglanti.update(sql_sorgu5);
					veritabani_baglanti.update(sql_sorgu3);
					
					
					//System.out.println(gonBakiye + "  " + tutar);
					String sql = "INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES ("
							+ gonId + ", " + cekId + ",'paragonderme' ,'" + date.toString() + "'," + cekBakiye + ","
							+ gonBakiye + "," + tutar + ")";
					//System.out.println(sql+"\n"+sql_sorgu3);
					veritabani_baglanti.ekle(sql);
					
					JOptionPane.showMessageDialog(null, "Transfer Gerçekleşti", "", 2);

					flag = true;
				}
				if (cekKur==0) {
					JOptionPane.showMessageDialog(null, "Hesap doğrulanama hatası", "Hata", 2);
				}

			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(26, 215, 142, 39);
		contentPane.add(btnNewButton);
		
		JButton btnBankayaParaGonderme = new JButton("Bankaya Para Gonderme");
		btnBankayaParaGonderme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs, rs1;
				LocalDate date = LocalDate.now();
				String cekId = cekHesap.getText();
				String gonId = gonHesap.getText();

				int tutar = Integer.parseInt(textField.getText());

				//String sql_sorgu = "SELECT * FROM hesap WHERE hesap_id=" + cekId + " and musteri_id="
						//+ musteri.getMusteri_id();
				String sql_sorgu2="select * from hesap ,para_birimi where hesap_id="+cekId+" and hesap.musteri_id="+musteri.getMusteri_id()+" and hesap.parabirimi_id=para_birimi.para_birimi_id;";
				String sql_sorgu1 = "select * from bankahesabi ;";

				rs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu2);
				rs1 = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu1);

				boolean flag = false;
				try {

					while (rs.next()) {
						cekBakiye = rs.getDouble("bakiye");
						cekKur=rs.getDouble("kur_degeri");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					while (rs1.next()) {
						bankaBakiye = rs1.getDouble("bakiye");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("++++"+bankaBakiye);

				if (cekBakiye < tutar) {
					JOptionPane.showMessageDialog(null, "Yetersiz Bakiye", "Hata", 2);
					System.out.println(cekBakiye + "-" + tutar);
					flag=true;
				} else {
					

					String sql_sorgu5 = "UPDATE hesap SET bakiye= " + (cekBakiye - (tutar)) + " WHERE hesap_id="
							+ cekId;
					
					tutar*=cekKur;
					
					
					String sql_sorgu3 = "UPDATE bankahesabi SET bakiye= " + (bankaBakiye + tutar) + " WHERE idbanka=123";

					veritabani_baglanti.update(sql_sorgu5);
					veritabani_baglanti.update(sql_sorgu3);
					
					
					//System.out.println(gonBakiye + "  " + tutar);
					String sql = "INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES (123"
							+ ", " + cekId + ",'paragonderme' ,'" + date.toString() + "'," + cekBakiye + ","
							+ bankaBakiye + "," + tutar + ")";
					//System.out.println(sql+"\n"+sql_sorgu3);
					veritabani_baglanti.tabloyaBaglantiYap("SET GLOBAL FOREIGN_KEY_CHECKS=0;");
					veritabani_baglanti.ekle(sql);
					
					JOptionPane.showMessageDialog(null, "Transfer Gerçekleşti", "", 2);

					flag = true;
				}
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "Hesap doğrulanama hatası", "Hata", 2);
				}

			}
			
		});
		btnBankayaParaGonderme.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBankayaParaGonderme.setBounds(211, 215, 195, 39);
		contentPane.add(btnBankayaParaGonderme);

	}
}
