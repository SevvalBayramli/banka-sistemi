package BankaSistemi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class KrediOnay extends JFrame {

	private JPanel contentPane;

	private JTable table;
	DefaultTableModel modelim = new DefaultTableModel();

	Object[] kolonlar = { "kredi_id","kredi_adi", "faiz_orani","gecikme_faiz_orani","taksitSayisi","kredi_tutari","aylik_odencek_tutar","baslangic_tarihi","bitis_tarihi" ,"musteri_id","onay"};
	Object[] satirlar = new Object[11];
	private JButton btnNewButton_1;
	private JTextField id;
	private JButton btnNewButton_2;
	double faizz=0;
	double gfaizz=0;
	double krediTutari=0;
	double bankaBakiye=0;
	double kredi_tutari=0;
	int musteri_id=0;
	int taksitSayisi=0;
	int kredi_id=0;
	double aylikOdenecekTutar=0;
	double odenecekFaiz=0;
	double odenecekToplamTutar=0;
	String baslangic_tarihi;
	static Musteri musteri = new Musteri();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KrediOnay frame = new KrediOnay(musteri);
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
	public KrediOnay(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(21, 36, 443, 233);
		contentPane.add(scrollPane);

		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);// scrollPane ile tabloyu bağladık ve kolon bilgileri gözükmeye başladı

		JButton btn1 = new JButton("Listele");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM kredi_turu where onay='onaylanmadı' and musteri_id="+musteri.getMusteri_id());
				String sql="Select * from hesap where hesap_id=123";
				ResultSet rs=veritabani_baglanti.tabloyaBaglantiYap(sql);
				try {
					if(rs.next())
					bankaBakiye=rs.getDouble("bakiye");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					while (myRs.next()) {
						satirlar[0] = myRs.getString("kredi_id");
						satirlar[1] = myRs.getString("kredi_adi");
						satirlar[2] = myRs.getString("faiz_orani");
						satirlar[3] = myRs.getString("gecikme_faiz_orani");
						satirlar[4] = myRs.getString("taksitSayisi");
						satirlar[5] = myRs.getString("kredi_tutari");
						satirlar[6] = myRs.getString("aylik_odenecek_tutar");
						satirlar[7] = myRs.getString("baslangic_tarihi");
						satirlar[8] = myRs.getString("bitis_tarihi");
						satirlar[9] = myRs.getString("musteri_id");
						satirlar[10] = myRs.getString("onay");
						
						musteri_id=Integer.parseInt(myRs.getString("musteri_id"));
						krediTutari=Double.parseDouble(myRs.getString("kredi_tutari"));
						taksitSayisi=Integer.parseInt(myRs.getString("taksitSayisi"));
						aylikOdenecekTutar=Double.parseDouble(myRs.getString("aylik_odenecek_tutar"));
						kredi_tutari=Double.parseDouble(myRs.getString("kredi_tutari"));
						baslangic_tarihi = myRs.getString("baslangic_tarihi");
						odenecekFaiz=(aylikOdenecekTutar*taksitSayisi)-krediTutari;
						odenecekToplamTutar=(aylikOdenecekTutar*taksitSayisi) ;
						
						

						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(21, 279, 85, 21);
		contentPane.add(btn1);
		
		id = new JTextField();
		id.setColumns(10);
		id.setBounds(219, 280, 96, 19);
		contentPane.add(id);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("kredi id");
		lblNewLabel_1_1.setBounds(152, 283, 96, 13);
		contentPane.add(lblNewLabel_1_1);
		
		
		String sql8="select * from faiz";
		ResultSet rs=veritabani_baglanti.tabloyaBaglantiYap(sql8);
		
		try {
			if(rs.next()) {
			faizz =  rs.getDouble("faiz");
			gfaizz= rs.getDouble("gecikme_faizi");
			
		}} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		


		btnNewButton_1 = new JButton("Onayla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sqlsorgu = "UPDATE kredi_turu SET onay=  'onaylandı' where kredi_id="+id.getText();
				System.out.println(sqlsorgu);
				veritabani_baglanti.update(sqlsorgu);
				
				String sql_sorgu = "UPDATE hesap SET bakiye=" + (bankaBakiye - krediTutari)
						+ " where hesap_id=123";
				veritabani_baglanti.update(sql_sorgu);
				String sql="INSERT INTO kredi_musteri(kredi_id,musteri_id,taksitSayisi,aylikOdenecekTutar,anapara,odenecekToplamTutar,"
						+ "odenecekFaiz,odenenTaksitSayisi,baslangicTarihi,sonOdenmeTarihi) VALUES ("+id.getText()+","+musteri_id+","+taksitSayisi+","+aylikOdenecekTutar+","
						+kredi_tutari+","+odenecekToplamTutar+","+odenecekFaiz+",0,'"+baslangic_tarihi+"','"+baslangic_tarihi+"')";
				veritabani_baglanti.ekle(sql);
				
				sql="INSERT INTO islem(hedef_id,kaynak_id,islem_adi,tarih,kaynak_bakiye,hedef_bakiye,tutar) VALUES (123, 123,'krediÇekme' ,'"+(MyDate.date)+"',"+bankaBakiye+","+bankaBakiye+","+kredi_tutari +")";
				System.out.println(sql);
				veritabani_baglanti.ekle(sql);
				
				
			}
		});
		btnNewButton_1.setBounds(340, 279, 85, 21);
		contentPane.add(btnNewButton_1);


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText((String) modelim.getValueAt(table.getSelectedRow(), 0));
			}
		});

		// contentPane.add(table);
	}
}
