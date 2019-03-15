## Määrittelydokumentti

Projektin  tarkoitus on toteuttaa Huffman (ja mahdollisesti joku muu) pakkaus/purku-algoritmi[1]. Tarvittavia tietorakenteita ovat binääripuu Huffman-puun esittämiseen, minimikeko (tai jono+järjestysalgo) puun rakentamiseen. Lisäksi dynaaminen taulukko-rakenne à la Javan ArrayList on todennäköisesti hyödyllinen, mutta ei välttämätön. 


Ongelma on tilan säästö arkistointia tai tiedonsiirtoa varten. Tietorakenteet on valittu kirjallisuuden[2] perusteella.

Ohjelma ottaa syötteenä tiedoston ja pakkaa sen toivottavasti pienempään tilaan uudeksi tiedostoksi. Pakatun tiedoston voi purkaa alkuperäiseksi ainakin samalla ohjelmalla, Huffman-pakatulla tiedostotyypillä ei taida olla standardia.

Tavoitteena on että pakattu tiedosto vie tilaa noin 20-90% alkuperäisestä tiedostokoosta[2].

Aikavaativuus on O(n log a) jossa n = syötteen pituus tavuina ja a = 0-2<sup>8</sup> eli uniikkien tavujen määrä. Merkistön frekvenssien laskeminen on O(n) operaatio, keon muodostaminen O(a)[2], puun muodostaminen O(a log a), pakatun bittijonon muodostaminen O(n log a), joissa log a <= 8. 
 
Tilavaativuus on O(n) sillä enimmillään muistissa on syötetiedosto, Huffman-puu jossa on korkeintaan 2<sup>8</sup> alkiota ja koodattu tiedosto jonka pituus on korkeintaan (n + Huffman-puu + file header).  

#### Lähteet

[1]Huffman coding, https://en.wikipedia.org/wiki/Huffman_coding

[2]Introduction to Algorithms, 3rd Edition (The MIT Press), CLRS