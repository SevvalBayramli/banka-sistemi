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

public class KrediTalep extends JFrame {
	
	private JPanel contentPane;

	private JTable table;
	static Musteri musteri = new Musteri();
	DefaultTableModel modelim = new DefaultTableModel();

	Object[] kolonlar = { "kredi_id","kredi_adi", "faiz_orani","gecikme_faiz_orani","taksitSayisi","kredi_tutari","aylik_odencek_tutar","baslangic_tarihi","bitis_tarihi" ,"musteri_id","onay"};
	Object[] satirlar = new Object[11];
	private JTextField textad;
	private JTextField faiz;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
	private JTextField id;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_3;
	private JTextField gfaiz;
	private JTextField tSayisi;
	private JButton btnNewButton_2;
	double faizz=0;
	double gfaizz=0;
	private JTextField krediTutari;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KrediTalep frame = new KrediTalep(musteri);
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
	public KrediTalep(Musteri musteri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(0, 10, 464, 253);
		contentPane.add(scrollPane);

		table = new JTable();
		modelim.setColumnIdentifiers(kolonlar);

		table.setBounds(226, 225, 230, 128);
		scrollPane.setViewportView(table);// scrollPane ile tabloyu bağladık ve kolon bilgileri gözükmeye başladı

		JButton btn1 = new JButton("Onaylanan Krediler");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM kredi_turu where onay= 'onaylandı' and musteri_id="+musteri.getMusteri_id());

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
						System.out.println(myRs.getString("kredi_id"));
						

						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn1.setBounds(0, 273, 203, 43);
		contentPane.add(btn1);
		JButton btn2 = new JButton("Onaylanmayan Krediler");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modelim.setRowCount(0);

				ResultSet myRs = veritabani_baglanti.tabloyaBaglantiYap("SELECT * FROM kredi_turu where onay= 'onaylanmadı' and musteri_id="+musteri.getMusteri_id());

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
						
						
						

						modelim.addRow(satirlar);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				table.setModel(modelim);

			}
		});
		btn2.setBounds(261, 273, 203, 43);
		contentPane.add(btn2);
		
		id = new JTextField();
		id.setEditable(false);
		id.setColumns(10);
		id.setBounds(560, 40, 96, 19);
		contentPane.add(id);

		textad = new JTextField();
		textad.setBounds(560, 70, 96, 19);
		contentPane.add(textad);
		textad.setColumns(10);

		faiz = new JTextField();
		faiz.setEditable(false);
		faiz.setBounds(560, 100, 96, 19);
		contentPane.add(faiz);
		faiz.setColumns(10);
		
		gfaiz = new JTextField();
		gfaiz.setEditable(false);
		gfaiz.setColumns(10);
		gfaiz.setBounds(560, 130, 96, 19);
		contentPane.add(gfaiz);
		
		tSayisi = new JTextField();
		tSayisi.setColumns(10);
		tSayisi.setBounds(560, 187, 96, 19);
		contentPane.add(tSayisi);
		
		krediTutari = new JTextField();
		krediTutari.setColumns(10);
		krediTutari.setBounds(560, 159, 96, 19);
		contentPane.add(krediTutari);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("kredi id");
		lblNewLabel_1_1.setBounds(474, 40, 96, 13);
		contentPane.add(lblNewLabel_1_1);
		
		lblNewLabel_1 = new JLabel("kredi ad");
		lblNewLabel_1.setBounds(474, 70, 96, 13);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("faiz oranı");
		lblNewLabel_2.setBounds(474, 100, 68, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("gecikme faiz oranı");
		lblNewLabel.setBounds(474, 130, 96, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("taksit Sayisi");
		lblNewLabel_3.setBounds(474, 190, 68, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("kredi tutarı");
		lblNewLabel_3_1.setBounds(474, 162, 68, 13);
		contentPane.add(lblNewLabel_3_1);
		
		
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
		faiz.setText(""+(faizz));
		gfaiz.setText(""+gfaizz);
		
		
		

		JButton btnNewButton = new JButton("kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ad, soyad, sqlsorgu;
				LocalDate datebaslangic = LocalDate.now();
				LocalDate datebitis=datebaslangic.plusMonths(Integer.parseInt(tSayisi.getText()));
				
				double taksits=Double.parseDouble(tSayisi.getText());
				double faiz2=Double.parseDouble(faiz.getText());
				double ayliktutar2=(Double.parseDouble(krediTutari.getText())/taksits);
				double ayliktutar3=ayliktutar2+ayliktutar2*(faiz2/100.0);
				
				sqlsorgu = "INSERT INTO kredi_turu(kredi_adi,faiz_orani,gecikme_faiz_orani,taksitSayisi,kredi_tutari,aylik_odenecek_tutar,baslangic_tarihi,bitis_tarihi,musteri_id,onay)"
						+ " VALUES ('"+textad.getText() +"'," + (faizz)+","+gfaizz+","+tSayisi.getText()+","+krediTutari.getText()+","+ayliktutar3+",'"+datebaslangic+"','"
						+datebitis+ "',"+musteri.getMusteri_id()+",'onaylanmadı')";
				System.out.println(sqlsorgu);
				veritabani_baglanti.ekle(sqlsorgu);
			}
		});
		btnNewButton.setBounds(474, 284, 85, 21);
		contentPane.add(btnNewButton);

		

		btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double taksits=Double.parseDouble(tSayisi.getText());
				double faiz2=Double.parseDouble(faiz.getText());
				double ayliktutar2=(Double.parseDouble(krediTutari.getText())/taksits);
				double ayliktutar3=ayliktutar2+ayliktutar2*(faiz2/100.0);

				String sqlsorgu = "UPDATE kredi_turu SET kredi_adi=  '" + textad.getText() + "' ,taksitSayisi=" + tSayisi.getText()+", kredi_tutari="+krediTutari.getText()+", aylik_odenecek_tutar="
						+ ayliktutar3+" where kredi_id="+id.getText()+" and onay='onaylanmadı'";
				System.out.println(sqlsorgu);
				veritabani_baglanti.update(sqlsorgu);
			}
		});
		btnNewButton_1.setBounds(582, 284, 85, 21);
		contentPane.add(btnNewButton_1);


		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText((String) modelim.getValueAt(table.getSelectedRow(), 0));
				textad.setText((String) modelim.getValueAt(table.getSelectedRow(), 1));
				faiz.setText((String) modelim.getValueAt(table.getSelectedRow(), 2));
				gfaiz.setText((String) modelim.getValueAt(table.getSelectedRow(), 3));
				tSayisi.setText((String) modelim.getValueAt(table.getSelectedRow(), 4));
				krediTutari.setText((String) modelim.getValueAt(table.getSelectedRow(), 5));
			}
		});

		// contentPane.add(table);
	}
}//kredi turu tablosunda başvur diyince musteri id ekle onaylanmadı de
