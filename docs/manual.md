## Käyttöohje
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

#### Käyttö
```
compress: ./gradlew run --args="-c text.txt"
extract/decompress: ./gradlew run --args="-x text.txt.huf"
```
tai
```
compress: java -jar name.jar -c text.txt
extract/decompress: java -jar name.jar -x text.txt.huf
```



Pakattua tiedostokokoa voi tutkia vaikka komennolla
```
ls -lhS
```   

Sen että tiedosto on pysynyt samana pakkaamisen ja purkamisen jälkeen voi tarkistaa komennolla:
```
diff -s original.file extracted.file
```