Android Test
============

Kullanıcı kaydı oluşturma
-------------------------
Tags:kullanici-kaydi-olusturma
* Permission sayfası geçilip uygulamanın açıldığı görülür
* Register butonuna tıklanır ve kullanıcı kayıt oluşturma sayfasının açıldığı kontrol edilir
* İsim, kullanıcıadı, şifre, ve email alanları, ilgili csv içerisinden rastgele doldurulur ve tutulur
* Programlama dili listesinden rastgele bir dil seçilir ve değeri tutulur
* Reklamları kabul ediyorum butonuna tıklanıp işaretlendiği kontrol edilir
* Kayıt ol butonuna tıklanır
* Kullanıcı doğrulama sayfasının açıldığı kontrol edilir
* Kullanıcı doğrulama sayfasındaki verilerin, kullanıcının girdiği verilerle aynı olduğu kontrol edilir
* Kullanıcıyı Kaydet butonuna basılır ve uygulama anasayfasına dönüldüğü kontrol edilir

Web View Interaction sayfasında “Say Hello Demo” seçeneği
---------------------------------------------------------
Tags:view-interaction-sayfasinda-say-hello-demo-secenegi
* Permission sayfası geçilip uygulamanın açıldığı görülür
* Chrome butonuna tıklanır ve ilgili sayfanın açıldığı kontrol edilir
* Aksiyon listesi dropdownuna tıklanır
* Aksiyon listesinden "Say Hello" aksiyonu seçilir
* Enter your name here alanı temizlenir ve "burak" yazılır
* Araba markası seçmek için dropdown açılır
* Markası adı "Mercedes" olan seçenek işaretlenir
* Send me your name butonuna tıklanır
* Atılan requestin formatının "http://localhost:4450/sayhello?name=burak&car=mercedes" şeklinde olduğu kontrol edilir

Touch Actions alanında tıklama kontrolü
--------------------------------------------
Tags:touch-actions-alaninda-tiklama-kontrolu
* Permission sayfası geçilip uygulamanın açıldığı görülür
* Touch Actions butonuna tıklanır ve ilgili sayfanın açıldığı kontrol edilir
* TouchActions alanına tıklanır
* TouchActions ekranında "SINGLE TAP CONFIRMED" yazdığı kontrol edilir

Uygulamanın kapatılması
-----------------------
Tags:uygulamanin-kapatilmasi
* Permission sayfası geçilip uygulamanın açıldığı görülür
* Uygulamayı sonlandırmak için END butonuna basılır ve ilgili popup var mı kontrol edilir
* Uygulama kapatmak için çıkan popupta ok butonuna basılır ve uygulamanın kapandığı görülür

