## Testausdokumentti

Yksikkötestit toteutettu junit:illa. 
Ne löytyvät kansiosta [src/test/java/compress](https://github.com/kotommi/pakkaaja/tree/master/src/test/java/compress).
Lisäksi tehty integraatiotestejä jotka tarkistavat että data pysyy häviöttömänä purku- ja pakkauskierroksen jälkeen.
#### Vertailut

Testimetodit:
Tiedostokoko on mitattu ```ls -la``` komentoa käyttöän ja se ilmoittaa tavujen määrän.
Pakkausuhde on laskettu kaavalla pakattu koko/alkuperäinen koko ja ilmoitettu prosentteina alkuperäisestä.

Ajan mittaus suoritettu unixin time ohjelmalla ja mittayksikkönä sen ilmoittama user time.
User time tarkoittaa käytettyä prosessoriaikaa joka laskee mukaan mahdollisesti moniytimisesti suoritetun laskennan lineaarisena.

#### Vertailualgoritmi
 
Valitsin gzipin, koska sen DEFLATE-algoritmi on yhdistelmä Huffmania ja Lempel-Ziviä.
Lisäksi se on yleisesti käytössä.

Käytetyt tiedostot:
[The Large Canterbury Corpus](http://www.data-compression.info/Corpora/CanterburyCorpus/index.html)
* world192.txt - The CIA world fact book - 2473400 tavua
* bible.txt - The King James version of the bible - 4047392 tavua
* E.coli - Complete genome of the E. Coli bacterium - 4638690 tavua

Kaikki tiedostot ovat tekstiä. Lisäksi E.coli-tiedosto on hyvin toisteista pienellä aakkostolla.

#### Suoritusnopeuden ja aikavaativuuden testaaminen
Otin satunnaista dataa testin toteutuksessa komennolla ```dd if=/dev/urandom of="$i"mfile bs=1M count=$i``` ja mittasin aikaa time-ohjelmalla.


![Huffman pakkaus](kuvat/huf_pak.png)
