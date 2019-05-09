## Käyttöohje

#### Käyttö
```
compress: java -jar pakkaja.jar -c huf/lzw text.txt
extract: java -jar pakkaaja.jar -x huf/lzw text.txt.huf/lzw
```
Mahdollisesti puuttuvat suoritusoikeudet
```
chmod u+x pakkaaja.jar
```
Esimerkkejä: 
```
java -jar pakkaaja.jar -c huf teksti.txt
java -jar pakkaaja.jar -x huf teksti.huf
java -jar pakkaaja.jar -c lzw teksti.txt
java -jar pakkaaja.jar -x lzw teksti.lzw
```

#### Projektin rakennus:
```
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

