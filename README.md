# Vize-Odevi
 ## Karşılaştığım hatalar ve buglar
 1. ->1.ve 2. hamle yapılınca fotograflar açıldıktan sonra hatalı seçim ise 3.hamle yapılmadan 1.ve 2. hamledeki fotograflar kapanmama veya çok hızlı kapanma sorunu
 2. ->doğru seçim olan fotografların tekrar tıklanabilir olması sorunu
 3. ->doğru seçim olan fotografların tekrar tıklanabilir olmasından dolayı eşleşme sayısının toplam skoru geçme sorunu
 4. ->çok hızlı tıklamalar ile açılan ilk 2 fotograf kapanmadan 3. seçimi yapma sorunu
 5. ->oyun bittiğinde zamanın durmama sorunu
 6. ->skoru kaydet butonunun oyun bitmeden aktif olarak gelme sorunu
 7. ->recycler view kullanım zorluğu(adapter , xml ,class)
 8. ->timer saat dakika saniye cinsinden yazdırma sorunu
 9. ->veri tabanı kaydetme ve veri çekme sorunları.
 10. ->aynı anda üst üste çok tıklamalar ile programın çökme sorunu
 11. ->bazı veri tabanı işlemleri eklenirken hatalı kelimeler veya cümleler yüzünden emulator çökme hata verme sorunları
 12. ->kart indexlerinin doğru bir şekilde kontrol edilmesi sorunu
 13. ->hatırlayabildiğim hatalar ve buglar bu kadardı hatırlayamadığım ve düzelttiğim daha bir çok hata ve bug vardır kesinlikle.
 
## Oynanış
 1. oyun açıldığında ekranda önceki kaydedilmiş veriler geliyor fotografta verinin hangi modda kaydedildiği gösteriliyor fotografta 4  var ise kolay modda (4x4) 6 var ise zor modda (6x6) , zaman,score ve hamle sayısı bizi karşılıyor.
 2. iki adet oynanış biçimimiz var kolay ve zor mod kolay modda (4x4) toplamda 16 fotograf 8 eşleşme yapabiliyoruz.zor modda (6x6) 36 fotograf 18 eşleşme yapabiliyoruz
 3. oyun modu seçildiğinde otomatik olarak oyun başlıyor zaman sayacı başlıyor tıklamalar ile eşleşmeler bulunmaya çalışıyor.
 4. oyun ekranında 2 adet buton bizi karşılıyor skoru kaydet ve ana sayfa butonları
 5. ana sayfa butonu tıklandığında açılmış fotograflar zaman eşleşme sayısı skor ve hamle temizlenir ve veri kaydetmeden ana sayfa ekranına gidilir.
 6. skoru kaydet butonu oyun tamamen bitmeden aktif hala gelmez oyundaki bütün eşleşmeler tamamlandığında otomatik olarak aktif olur tıklandığında otomatik olarak oyunun hangi modda oynandığını algılar ona göre resim öğesini seçer ayrıca hamle , zaman ve skor veri tabanımıza kaydedilir.Ana sayfadaki recyclerview içerisinden bunu görebilirsiniz.
 7. oyun modu açılır açılmaz zaman sayacı saymaya başlar eşleşmeler bittiğinde otomatik olarak sayaç kendini durdurur.
 8. veriler sqlite veri tabanında tutulur
 9. veri gösterme işlemi recyclerview ile gösterilir
 ## açıklama
 1. oyundaki bulabildiğim bütün hatalar ve buglar denemeler analizler sonucu tarafımca tespit edilmiştir.
 2. aktif olarak tespit edilen bütün hata ve buglar giderilmiştir.
 ## ana sayfa görünüm gifi
 ![ansayfagif](https://user-images.githubusercontent.com/33986500/163313328-3bfa3d32-546e-473c-ad2c-9fc134ea582a.gif)
## oyun modu görünüm gifi
![2](https://user-images.githubusercontent.com/33986500/163313622-62755f01-0ac5-4c6d-8798-6942520779fb.gif)
## oyun oynanış gifleri
- oyunun açılış ekranı oyun ekranı oynanış biçimi veri tabanına kaydetme işlemleri gibi bir çok işlemi gösteren oynanış gifleri.
![1](https://user-images.githubusercontent.com/33986500/163313819-f03d9e28-4a71-4703-8585-7bcf2e35add2.gif)
1. ilk gifimizde ana sayfa görünümü ve oyun moduna giriş işlemleri görülmektedir
![2](https://user-images.githubusercontent.com/33986500/163313838-b820fdb3-3562-482b-b002-92f3e3125a9b.gif)
2. ikinci gifimizde oyunun oynanış biçimi ve seçme işlemleri doğru seçme yanlış seçme işlemleri görülmektedir.
![3](https://user-images.githubusercontent.com/33986500/163314242-3d493152-dc12-441c-a000-df952294668c.gif)
3. üçüncü gifimizde seçme işlemleri devam etmektedir doğru yanlış seçimler yapılmaktadır.
![4](https://user-images.githubusercontent.com/33986500/163314620-4ee92d37-98f9-4e0e-b025-1cb9d966fe8a.gif)
4. dördüncü gifimizde ise seçme işlemlerinin bitmesi ile zaman sayacının durması ve skor hamle sayısı ve eşleşme sayılarının son halini alması.
![5](https://user-images.githubusercontent.com/33986500/163315140-08c56c9e-4b5c-4135-8f4c-362268eb7380.gif)
5. beşinci gifimizde ise açılmış fotograflara tekrar tıklanabilirliğin kontrolü, oyunun bitmesi ile aktif hale gelen skoru kaydet butonuna tıklanması verilerin veri tabanına kaydedilmesi veri tabanına kaydedilen verinin ana sayfadaki recyclerview içersindeki görünmesi.Son oynadığımız verilerinin görünmesi bizi karşılıyor.

## iletişim
- ibrahimmminal@gmail.com
- ibrahimmminall@gmail.com


