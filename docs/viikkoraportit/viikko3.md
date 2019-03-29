## Viikko 3

#### Tällä viikolla tiralabrassa
Opettelin bittioperaatioita ja sain yksinkertaisen tiedostonpakkauksen ja purkamisen toimimaan.
Taistelin aika kauan pakkauksen kanssa, kunnes siirryin käyttämään Javan int:ejä tavujen kuvaamiseen.
Tämä tuo omat haasteensa mutta nyt ne eivät ole piilossa jonkun ulkopuolisen luokan API:n takana. 
Purkaminen oli suht helppo tehdä kun olin päässyt jyvälle siitä miten tavuja ja bittejä kannattaa käsitellä.
Toivon että tämä oli projektin työläin osa. 
Kirjoitin lisäksi testit Huffman-luokalle, mutta niitä voisi laajentaa reunatapauksilla.

Tällä hetkellä tiedoston voi pakata ja purkaa muistissa olevan Huffman-puun avulla.
Satunnaiselle suomenkieliselle kirjalle pakattu tiedosto on noin 60% alkuperäisestä, josta puuttu headeri joka sisältää puun. 
Seuraavaksi pitäisi koodata puu tiedoston mukaan, että ohjelmalla tekee oikeasti jotain. 
Oheessa Vänrikki Stoolin tarinat pakattuna: 

```
$ diff tarinat.txt decoded.file.huf -s
Files tarinat.txt and decoded.file.huf are identical
$ ll
-rw-rw-r--  1 tomko tomko 220026 maali 29 22:03 tarinat.txt
-rw-rw-r--  1 tomko tomko 130392 maali 29 22:04 tarinat.txt.huf
130/220 ~ 0.6
```

Käytin yhteensä aikaa tällä viikolla reilut 10 tuntia.

Mitä teen seuraavaksi eli TODO-lista

* Huffman-puu pakatun tiedoston mukaan
* Refaktorointi
* Implementoi minimikeko, arraylist
* Korvike BitSetille että pääsee eroon siihen liittyvästä spagetista 