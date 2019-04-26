## Toteutusdokumentti

WIP
 

#### Huffman
Ohjelma toteuttaa Huffman-koodauksen jonka perusidea on korvata useasti syötteessä esiintyvät merkit lyhyillä bittijonoilla ja kasvattaa bittijonon pituutta aina harvinaisimmille merkeille. 
Merkit ovat ohjelman tapauksessa tavuja ja ohjelma pystyy pakkaamaan myös tiedostot jotka eivät ole pelkkää tekstiä.
Käytännössä tämä tapahtuu ensin lukemalla tiedosto muistiin, laskemalla eriarvoisten tavujen jakauma ja sitten luomalla binääripuu "pohja-ensin" menetelmällä, jossa harvinaisimmat tavut sijoitetaan puun pohjalle.
Binääripuun vasen/oikea-haarat kuvaavat nollia ja ykkösiä ja niiden avulla luodaan bittijonot merkkejä varten. 
Sitten tavut yksinkertaisesti korvataan niitä vastaavilla bittijonoilla. 
Lisäksi käytetty binääripuu kirjoitetaan tiivistetyssä muodossa otsaakkeksi tiedoston alkuun.   

### Ohjelman rakenne
Ohjelmassa on käytetty luokkia jakamaan koodia loogisiin kokonaisuuksiin. 
Oliomaista Javaa on ehkä tavallista vähemmän ja sitä on käytetty lähinnä tilan eristämiseen olion sisälle.
Ohjelman flow menee suurinpiirtein näin:
1. parsi komentoriviargumentit
2. lue syötetiedosto tavutaulukoksi muistiin
3. kirjoita/lue otsake
4. pakkaa/pura itse data
5. kirjoita se uuteen tiedostoon

#### Riippuvuudet
Käytän ohjelmassa Googlen Guava-kirjastoa tiedostojen lukemiseen ja kirjoittamiseen. 
Se on wrapatty luokassa FileUtils.