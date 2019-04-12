# Pakkaaja

Käyttö:
```
./gradlew run --args=[filename]
```
Tällä hetkellä outputtaa tiedoston original.file.huf joka on pakattu tiedosto ilman otsaketta(todo) ja tiedoston decoded.file.huf joka on  jvm:n muistissa olevan Huffman-puun avulla purettu. Sen että tiedosto on pysynyt samana pakkaus ja purkukierroksen jälkeen voi tarkistaa loitsulla 
```
diff -s original.file decoded.file.huf
```

## Dokumentaatio
[Määrittely](https://github.com/kotommi/pakkaaja/blob/master/docs/M%C3%A4%C3%A4rittely.md)


Viikkoraportit:
*  [Viikko 1](https://github.com/kotommi/pakkaaja/blob/master/docs/viikkoraportit/viikko1.md)
*  [Viikko 2](https://github.com/kotommi/pakkaaja/blob/master/docs/viikkoraportit/viikko2.md)
*  [Viikko 3](https://github.com/kotommi/pakkaaja/blob/master/docs/viikkoraportit/viikko3.md)
*  [Viikko 4](https://github.com/kotommi/pakkaaja/blob/master/docs/viikkoraportit/viikko4.md)
*  [Viikko 5](https://github.com/kotommi/pakkaaja/blob/master/docs/viikkoraportit/viikko5.md)
