## Käyttöohje

#### Käyttö
```
Pakkaus (compress)
java -jar pakkaja.jar -c huf/lzw text.txt
Purkaminen (extract)
java -jar pakkaaja.jar -x huf/lzw text.txt.huf/lzw
```

Ohjelmaa käytetään komentoriviltä. Olen testannut sitä vain 64-bittisellä Linuxilla.
Vivullinen argumentti -c tai -x kertoo ohjelmalle pakataanko vaiko puretaan.
Sen jälkeen valitaan algoritmi huf tai lzw.
Ohjelma pakkaa melkein minkä tahansa tiedoston kunhan se mahtuu muistiin. 
Ainakin yksi rajoitus on Javan:n arrayn maksimikoko eli 2 gigatavua. 




Mahdollisesti puuttuvat suoritusoikeudet saa asetettua komennolla
```
chmod u+x pakkaaja.jar
```
Esimerkkejä: 
```
java -jar pakkaaja.jar -c huf teksti.txt
java -jar pakkaaja.jar -x huf teksti.txt.huf
java -jar pakkaaja.jar -c lzw data.dat
java -jar pakkaaja.jar -x lzw data.dat.lzw
```

#### Projektin rakennus:
```
git clone https://github.com/kotommi/pakkaaja.git
cd pakkaaja
./gradlew build
```
.jar-paketti löytyy kansiosta
```
build/libs/compress-1.0-SNAPSHOT.jar
```

Huom. Käytän openjdk11:ta, jos käytät koneellasi jotain muuta versiota avaa tiedosto ```build.gradle``` ja muuta riviä 
```
sourceCompatibility = 1.11
```
esim ```sourceCompatibility = 1.8``` openjdk-8:lle.

