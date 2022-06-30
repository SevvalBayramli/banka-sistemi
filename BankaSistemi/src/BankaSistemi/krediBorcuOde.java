package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class krediBorcuOde extends JFrame {
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();
	static Musteri musteri = new Musteri();
	int hid;
	int cekId;
	int odenenTaksitSayisi, taksitSayisi;
	double cekBakiye, cekKur, bankaBakiye, aylikOdenecekTutar = 0, anapara;
	String kredi_id;
	String bakiye;
	int yil, ay, gun;
	boolean flag = false;

	Object[] kolonlar = { "kredi_musteri_id", "kredi_id", "musteri_id", "taksitSayisi", "aylikOdenecekTutar", "anapara",
			"odenecekToplamTutar", "odenecekFaiz", "odenenTaksitSayisi", "baslangic_tarihi", "sonOdenmeTarihi" };

	Object[] satirlar = new Object[11];
	private JTextField h_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					krediBorcuOde frame = new krediBorcuOde(musteri);
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
	public krediBorcuOde(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(21, 10, 700, 183);
		contentPane.add(scrollPane);

		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);// scrollPane ile tabloyu bağladık ve kolon bilgileri gözükmeye başladı

		h_id = new JTextField();
		h_id.setBounds(100, 206, 96, 19);
		contentPane.add(h_id);
		h_id.setColumns(10);

		JLabel lblNewLabel = new JLabel("Hesap ID");
		lblNewLabel.setBounds(31, 206, 80, 19);
		contentPane.add(lblNewLabel);

		JButton btn1 = new JButton("Listele");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti
						.tabloyaBaglantiYap("select * from kredi_musteri where musteri_id=" + musteri.getMusteri_id());

				try {
					while (myRs.next()) {
						satirlar[0] = myRs.getString("kredi_musteri_id");
						satirlar[1] = myRs.getString("kredi_id");
						satirlar[2] = myRs.getString("musteri_id");
						satirlar[3] = myRs.getString("taksitSayisi");
						satirlar[4] = myRs.getString("aylikOdenecekTutar");
						satirlar[5] = myRs.getString("anapara");
						satirlar[6] = myRs.getString("odenecekToplamTutar");
						satirlar[7] = myRs.getString("odenecekFaiz");
						satirlar[8] = myRs.getString("odenenTaksitSayisi");
						satirlar[9] = myRs.getString("baslangicTarihi");
						satirlar[10] = myRs.getString("sonOdenmeTarihi");

						modelim.addRow(satirlar);

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(206, 203, 127, 31);
		contentPane.add(btn1);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				kredi_id = (String) modelim.getValueAt(table.getSelectedRow(), 1);
				String odenenTaksitSayisi2 = (String) modelim.getValueAt(table.getSelectedRow(), 8);
				String baslangicTarihi = (String) modelim.getValueAt(table.getSelectedRow(), 9);
				String aylikOdenecekTutar2 = (String) modelim.getValueAt(table.getSelectedRow(), 4);
				String anapara2 = (String) modelim.getValueAt(table.getSelectedRow(), 5);
				String taksitSayisi2 = (String) modelim.getValueAt(table.getSelectedRow(), 3);

				anapara = Double.parseDouble(anapara2);
				aylikOdenecekTutar = Double.parseDouble(aylikOdenecekTutar2);
				odenenTaksitSayisi = Integer.parseInt(odenenTaksitSayisi2);
				taksitSayisi = Integer.parseInt(taksitSayisi2);

				yil = Integer.parseInt(baslangicTarihi.substring(0, 4));// 2022-05-09
				ay = Integer.parseInt(baslangicTarihi.substring(5, 7));
				gun = Integer.parseInt(baslangicTarihi.substring(8, 10));
				System.out.println(ay);

			}
		});

		JButton aKrediBordu = new JButton("Aylık Kredi Borcunu Öde");
		aKrediBordu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs, rs1;

				LocalDate Tarih = LocalDate.of(yil, ay, gun);
				System.out.println(Tarih);
				LocalDate Tarih2 = Tarih.plusMonths(odenenTaksitSayisi + 1);

				cekId = Integer.parseInt(h_id.getText());

				double tutar = aylikOdenecekTutar;

				String sql_sorgu2 = "select * from hesap ,para_birimi where hesap_id=" + cekId
						+ " and hesap.musteri_id=" + musteri.getMusteri_id()
						+ " and hesap.parabirimi_id=para_birimi.para_birimi_id;";
				String sql_sorgu1 = "select * from hesap where hesap_id=123;";

				rs = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu2);
				rs1 = veritabani_baglanti.tabloyaBaglantiYap(sql_sorgu1);

				try {

					while (rs.next()) {
						cekBakiye = rs.getDouble("bakiye");
						cekKur = rs.getDouble("kur_degeri");

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
				cekBakiye = cekBakiye * cekKur;

				if (cekBakiye < tutar) {
					JOptionPane.showMessageDialog(null, "Yetersiz Bakiye", "Hata", 2);
					System.out.println(cekBakiye + "-" + tutar);
					flag = true;
				} else {

					if (Tarih2.isAfter(MyDate.date2())) {
						String sql_sorgu5 = "UPDATE hesap SET bakiye= " + ((cekBakiye - tutar) / cekKur)
								+ " WHERE hesap_id=" + cekId;

						//tutar *= cekKur;

						String sql_sorgu3 = "UPDATE hesap SET bakiye= " + (bankaBakiye + aylikOdenecekTutar) + " WHERE hesap_id=123";

						veritabani_baglanti.update(sql_sorgu5);
						veritabani_baglanti.update(sql_sorgu3);

						String sql = "INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES (123"
								+ ", " + cekId + ",'krediOdeme' ,'" + MyDate.date2().toString() + "'," + cekBakiye
								+ "," + bankaBakiye + "," + aylikOdenecekTutar + ")";

						veritabani_baglanti.tabloyaBaglantiYap("SET GLOBAL FOREIGN_KEY_CHECKS=0;");
						veritabani_baglanti.ekle(sql);

						sql = "Update kredi_musteri set odenenTaksitSayisi=" + (odenenTaksitSayisi + 1)
								+ ",sonOdenmeTarihi='" + MyDate.date2().toString() + "' where kredi_id=" + kredi_id;

						veritabani_baglanti.update(sql);

						JOptionPane.showMessageDialog(null, "Transfer Gerçekleşti", "", 2);
						flag = true;

					} else {
						String sql1 = "Select * from faiz";
						ResultSet rs3 = veritabani_baglanti.tabloyaBaglantiYap(sql1);
						double gecikmeFaizi = 0, faiz = 0;
						try {
							if (rs3.next())
								gecikmeFaizi = rs3.getDouble("gecikme_faizi");
							faiz = rs3.getDouble("faiz");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						double taksit = (anapara / taksitSayisi);
						aylikOdenecekTutar = ((taksit * (gecikmeFaizi / 100)) + (taksit * (faiz / 100))) + taksit
								+ taksit;

						tutar = aylikOdenecekTutar;

						

						cekBakiye = cekBakiye * cekKur;
						String sql_sorgu5 = "UPDATE hesap SET bakiye= " + ((cekBakiye - tutar) / cekKur)
								+ " WHERE hesap_id=" + cekId;

						tutar *= cekKur;

						String sql_sorgu3 = "UPDATE hesap SET bakiye= " + (bankaBakiye + aylikOdenecekTutar) + " WHERE hesap_id=123";

						veritabani_baglanti.update(sql_sorgu5);
						veritabani_baglanti.update(sql_sorgu3);

						String sql = "INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES (123"
								+ ", " + cekId + ",'krediOdeme' ,'" + MyDate.date2().toString() + "'," + cekBakiye
								+ "," + bankaBakiye + "," + aylikOdenecekTutar + ")";

						veritabani_baglanti.tabloyaBaglantiYap("SET GLOBAL FOREIGN_KEY_CHECKS=0;");
						veritabani_baglanti.ekle(sql);

						sql = "Update kredi_musteri set odenenTaksitSayisi=" + (odenenTaksitSayisi + 1)
								+ ", sonOdenmeTarihi= '" + MyDate.date2().toString() + "' where kredi_id=" + kredi_id;

						veritabani_baglanti.update(sql);

						JOptionPane.showMessageDialog(null, "Transfer Gerçekleşti", "", 2);

						flag = true;
					}
					if (flag == false) {
						JOptionPane.showMessageDialog(null, "Hesap doğrulanama hatası", "Hata", 2);
					}

				}

			}

		});
		aKrediBordu.setBounds(533, 203, 170, 31);
		contentPane.add(aKrediBordu);
		JButton btnKrediBorcununTamamn = new JButton("Kredi Borcunun Tamamını Öde");
		btnKrediBorcununTamamn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				double tutar = (taksitSayisi - odenenTaksitSayisi) * (anapara / taksitSayisi);
				
				cekBakiye = cekBakiye * cekKur;
				
				cekId = Integer.parseInt(h_id.getText());
				String sql_sorgu5 = "UPDATE hesap SET bakiye= " + ((cekBakiye - tutar) / cekKur)
						+ " WHERE hesap_id=" + cekId;

				//tutar *= cekKur;

				String sql_sorgu3 = "UPDATE hesap SET bakiye= " + (bankaBakiye + tutar) + " WHERE hesap_id=123";

				veritabani_baglanti.update(sql_sorgu5);
				veritabani_baglanti.update(sql_sorgu3);
				
				String sql = "Update kredi_musteri set odenenTaksitSayisi=" + taksitSayisi
						+ ", sonOdenmeTarihi= '" + MyDate.date2().toString() + "' where kredi_id=" + kredi_id;
				veritabani_baglanti.update(sql);
				sql = "INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES (123"
						+ ", " + cekId + ",'krediOdeme' ,'" + MyDate.date2().toString() + "'," + cekBakiye
						+ "," + bankaBakiye + "," + tutar + ")";

				veritabani_baglanti.tabloyaBaglantiYap("SET GLOBAL FOREIGN_KEY_CHECKS=0;");
				veritabani_baglanti.ekle(sql);
				
			}

		});

		btnKrediBorcununTamamn.setBounds(343, 203, 180, 31);
		contentPane.add(btnKrediBorcununTamamn);

	}}
	

//hepsini öde derse kredinin tutarını çek taksit sayisina böl kalan taksit sayısıyla çarp