## Toteutusdokumentti

#### Huffman
Ohjelma toteuttaa Huffman-koodauksen 

#### LZW
Ohjelmassa on toteutettu 16-bitin koodisanoja käyttävä Lempel-Ziv-Welch algoritmi.
Se ottaa syötteenä 8 bitin tavuja.

### Ohjelman rakenne
Ohjelmassa on käytetty luokkia jakamaan koodia loogisiin kokonaisuuksiin.
Pakkauksessa domain on tietorakenteita, joita algoritmit käyttävät.
Pakkauksessa encode on luokat, jotka toteuttavat itse algoritmit. 
Oliomaista Javaa on ehkä tavallista vähemmän ja sitä on käytetty lähinnä tilan eristämiseen olion sisälle.

Ohjelman flow menee suurinpiirtein näin:
1. parsi komentoriviargumentit
2. lue syötetiedosto tavutaulukoksi muistiin
3. kirjoita/lue otsake (Huffman)
4. pakkaa/pura itse data
5. kirjoita se uuteen tiedostoon

#### Suorituskyky- ja O-analyysivertailu
Ks. [Testaus](./Testaus.md)

#### Riippuvuudet/Ei itse toteutetut asiat
Käytän ohjelmassa Googlen Guava-kirjastoa tiedostojen lukemiseen ja kirjoittamiseen. 
Se on wrapatty luokassa FileUtils. Lisäksi testit käyttävät mm. Javan Arrays-luokkaa tulosten varmistamiseen.

#### Puutteet/parannusehdotukset
* Isoin parannus algoritmeihin olisi kirjoittaa LZW-algoritmi käyttämään vaihtelevan pituisia koodisanoja.
* Ohjelmana isoin parannus olisi lukea tiedostot paloittain ja näin vähentää muistin käyttöä.
* Huffman-otsakkeen lyhentäminen muuttamalla tyyppiä ilmaisevat tavut yksittäisiksi biteksi.
* Voisi ylipäätään eristää bittien kirjoituksen tavuiksi johonkin luokkaan.
* Parempi käli.

[Huffman](https://en.wikipedia.org/wiki/Huffman_coding)
[LZW](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch)
