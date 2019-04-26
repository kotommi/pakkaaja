## Testausdokumentti

Yksikkötestit toteutettu junit:illa. 
Ne löytyvät kansiosta [src/test/java/compress](https://github.com/kotommi/pakkaaja/tree/master/src/test/java/compress).
Lisäksi tehty integraatiotestejä jotka tarkistavat että data pysyy häviöttömänä purku- ja pakkauskierroksen jälkeen.
Todo: E2E testit. 
#### Vertailut
Tänne aika- ja tilankäyttövertailuja yleisesti käytettyjän pakkausalgoritmien kanssa.

Testimetodit:
Tiedostokoko on mitattu ```ls -la``` komentoa käyttöän ja se ilmoittaa tavujen määrän.
Pakkausuhde on laskettu kaavalla pakattu koko/alkuperäinen koko ja ilmoitettu prosentteina alkuperäisestä.

Ajan mittaus suoritettu unixin time ohjelmalla.
TODO päätä mitä sen raportoimista ajoista käytetään. 
Real tarkoittaa oikeaa suoritusaikaa. 
User + sys tarkoittaa käytettyä prosessoriaikaa joka laskee mukaan moniytimisesti suoritetun laskennan.

Vertailualgoritmit: 
  * Valitsin gzipin, koska sen DEFLATE-algoritmi on yhdistelmä Huffmania ja Lempel-Ziviä. 
  * compress/uncompress on "Variable-width codes" toteutus LZW algoritmista joten se vaikutti mielekkäältä vertailukohteelta.
  
  
Käytetyt tiedostot:

*  [Vänrikki Stoolin tarinat](https://www.gutenberg.org/ebooks/12688.txt.utf-8) 
*  [Film: Several Different Atomic Detonations](https://www.gutenberg.org/files/5215/5215-mpg.mpg)

Valitsin yhden tekstitiedoston jolla kaikkien algoritmien pitäisi toimia nopeasti ja hyvällä pakkaussuhteella. 

Lisäksi valitsin yhden videotiedoston joka on vähän isompi ja jota on jo valmiiksi pakattu joten se on enemmän käytetyn ajan testausta varten. 


|Tiedosto |Koko tavuina |Algoritmi |Pakattu koko |Pakkausaika |Purkuaika |Pakkaussuhde |   	
|---	|---	|---	|---	|---	|---	|---	|	
|tarinat.txt |220026   	|Huffman   	|130683 |real 0,140s/user 0,187s/sys 0,024s   	|real 0,134s/user 0,182s/sys 0,025s|59.39%   	|   	
|tarinat.txt |220026   	|gzip/DEFLATE |87867|real 0,020s/user 0,016s/sys 0,004s |real 0,006s/user 0,006s/sys 0,000s   	|39.93%   	|   	
|tarinat.txt |220026   	|compress/LZW |99433|real 0,010s/user 0,007s/sys 0,003s   	|real 0,007s/user 0,005s/sys 0,000s |45.19%   	|   	
|atomic.mpg |9138192 |Huffman |9028387 |real 0,569s/user 0,451s/sys 0,069s   	|real 0,863s/user 0,702s/sys	0,052s   	|98.79%   	|   
|atomic.mpg |9138192 |gzip/DEFLATE |8491467 |real 0,298s/user 0,294s/sys 0,004s   	|real 0,072s/user 0,068s/sys	0,004s   	|92.92%   	   	|
|atomic.mpg |9138192 |compress/LZW |10298565 |real 0,160s/user 0,152s/sys 0,008s   	|real 0,067s/user 0,051s/sys 0,017s   	|112.69%   	|   