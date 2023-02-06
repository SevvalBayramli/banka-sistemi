# Banka-Sistemi
Banka içerisinde müşteri, temsilci ve banka müdürü olmak üzere 3 adet rol bulunmaktadır. Müşteriler ve
çalışanlar için gerekli tanımlayıcı bilgiler (Ad Soyad, Telefon, TC No, Adres, E-posta) veri tabanında
saklanmalıdır. Bir müşterinin birden fazla hesabı bulunabilir. Hesaplar sistem içerisinde kayıtlı bulunan
herhangi bir para birimi cinsinden açılabilir (TL varsayılan olarak gelmelidir). Hesaplar arası para
transferinde gerekli durumlarda kur dönüşümü otomatik olarak yapılır. Rollerin gerçekleştirdiği
eylemler aşağıda belirtilmiştir. 

![g1](https://user-images.githubusercontent.com/72937239/176695761-808a8517-048a-4384-8c32-825e2e255112.PNG)

Sistemdeki Roller:

● Müşteriler;

o Hesaplarından para çekebilirler ve yatırabilirler.
o Yeni hesap açma ve var olan bir hesabı silme talebinde bulunabilirler.
▪ Bakiyesi “0” olmayan bir hesap silinemez.
o Birbirleri arasında para transferi yapabilirler.
▪ Farklı para birimlerine sahip hesaplar arası transferler sırasında gönderilen miktar
hedef para birimine otomatik olarak çevrilmelidir.
o Bilgilerini güncelleyebilirler. (Adres, Telefon vs.)
o Bankaya para transferi yapabilirler. (Kredi borcu ödeme)
o Bankadan kredi talep edebilirler.
▪ Kredi sadece TL cinsinden talep edilebilmektedir.
▪ Bankanın kredi talebini onaylaması durumunda istenilen vade oranınca (faiz ve
anapara toplamı) bölünerek aylara borç olarak yansıtılır.
▪ Aylık özet görüntülemede kredi borcu ödemeleri için ödenen faiz ve anapara ayrı
ayrı görüntülenmelidir.
▪ Müşterinin aylık borcunun tamamını ödememesi durumunda kalan borç ek faiz
hesaplanarak bir sonraki aya devreder.
▪ Faiz ve gecikme faiz oranı banka müdürü tarafından belirlenir.
▪ Aylık borç ve kalan borç ayrı ayrı görüntülenmeli. (müşteri isterse tüm borcunu
tek seferde ödeyebilir)
▪ Erken ödeme durumlarında gelecek aylar için faiz alınmayacaktır.
o Aylık özetlerini görüntüleyebilirler. (Geçerli ay içerisinde yaptığı para gönderme, çekme,
kredi borcu ödeme gibi işlemlerin özeti)

![g4](https://user-images.githubusercontent.com/72937239/176696838-b589eb61-3632-4da5-b130-8b738ef19870.PNG)![g5](https://user-images.githubusercontent.com/72937239/176697686-a2e92265-6635-4d13-b93f-faa57db4a8c0.PNG)

● Banka müdürü;

o Bankanın genel durumunu (gelir, gider, kar ve toplam bakiye) görüntüleyebilmektedir.
o Yeni para birimi (Dolar, Euro, Sterling vs.) ekleyebilir ve kur değerlerini güncelleyebilir.
o Çalışanların maaş ücretlerini belirleyebilecektir.
▪ Tek bir çalışan türü vardır (müşteri temsilcisi). Hepsinin maaş miktarı aynıdır.
o Kredi ve gecikme faiz oranını belirler.
o Müşteri ekleyebilir.
▪ Sisteme yeni bir müşteri eklenmesi durumunda en az müşteriye sahip olan
temsilciye atanır.
o Sistemi bir ay ilerletebilir.
▪ İsterlerin test edilebilmesi için sizlerden uygulama tarihini bir ay ileriye
öteleyebilmeniz istenmektedir.
▪ Bu ilerletme işlemi sonucunda -> maaşların ödenmesi, gelir-gider durumlarının
güncellenmesi ve müşterilerin bir sonraki aya ait borçlarının kendilerine
yansıtılması gerekmektedir.
o Bankada gerçekleşen tüm işlemleri (para çekme, yatırma ve transfer)
görüntüleyebilmektedir.
▪ İşlemleri listelerken “son X adet işlemi listele” şeklinde bir seçenek sunulmalıdır.

![g8](https://user-images.githubusercontent.com/72937239/176697099-3ff15f63-82a0-4463-90c8-371698b32145.PNG)

● Müşteri temsilcisi;

o Her müşterinin bir temsilcisi vardır.
o Müşteri ekleme, silme ve düzenleme yapabilir (silme ve düzenleme işlemleri sadece kendi
müşterileri için geçerlidir).
o Müşteri bilgilerini güncelleyebilirler. (Adres, Telefon vs.)
o İlgilendikleri müşterilerin genel durumlarını (gelir, gider ve toplam bakiye)
görüntüleyebilmektedir.
o Müşterilerden gelen hesap açma, silme ve kredi taleplerini görüntüleme ve onaylama
sorumluluğu temsilcilere aittir.
o İlgilendikleri müşterilerin işlemlerini (para çekme, yatırma ve transfer)
görüntüleyebilmektedir.

![g2](https://user-images.githubusercontent.com/72937239/176696411-998e7d82-8fd9-464c-a3db-f05e1950d737.PNG) ![g3](https://user-images.githubusercontent.com/72937239/176696549-ad7e6e16-a831-4b2e-a9c5-8b0cafcfca2a.PNG)![g7](https://user-images.githubusercontent.com/72937239/176697807-07d54b08-5429-4862-a586-79bb4fafc877.PNG)






