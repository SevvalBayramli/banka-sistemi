# Banka-Sistemi
Banka içerisinde müşteri, temsilci ve banka müdürü olmak üzere 3 adet rol bulunmaktadır. Müşteriler ve
çalışanlar için gerekli tanımlayıcı bilgiler (Ad Soyad, Telefon, TC No, Adres, E-posta) veri tabanında
saklanmalıdır. Bir müşterinin birden fazla hesabı bulunabilir. Hesaplar sistem içerisinde kayıtlı bulunan
herhangi bir para birimi cinsinden açılabilir (TL varsayılan olarak gelmelidir). Hesaplar arası para
transferinde gerekli durumlarda kur dönüşümü otomatik olarak yapılmalıdır. Rollerin gerçekleştirdiği
eylemler aşağıda belirtilmiştir. Tüm bu eylemlerin tasarlanan bir arayüz üzerinden görsel bir şekilde
gösterilmesi gerekmektedir.

Sistemdeki Roller:
![g4](https://user-images.githubusercontent.com/72937239/176694730-8424126b-79e6-435f-ad9c-81057c3b04bf.PNG)

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

