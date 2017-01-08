**Aihe:**
Miinaharava. Klooni microsoftin omasta käyttöjärjestelmien mukana tulevasta pelistä. Peliä pelataan logiikkaa ohjailevan ikkunan kautta - pitkälti samaan tapaan kuin perinteisessä miinaharavassakin: ikkunassa on ruudukko painikkeita, jotka edustavat ruutuja. Ruutuja voidaan avata tai merkitä lipulla. Jos ruudukkoon piilotettu miina avataan häviää pelin. Jos ruudukon avaamattomien ruutujen määrä täsmää ennalta määrättyyn miinojen määrään (joka kerrotaan pelaajalle käyttöliittymässä pelin aikana) niin katsotaan pelaajan tietävän missä kaikki miinat sijaitsevat, eli miinat on haravoitu ja pelaaja voittaa pelin. Nopeasta suorituksesta saa paremmat pisteet.

Miinojen sijaintia voi arvioida avatuista ruuduista saadun tiedon perusteella: jokainen miinoittamaton ruutu kertoo kuinka montaa miinaa se koskettaa, jos yhtään. Jos avataan ruutu joka ei kosketa ainuttakaan miinaa, avaa peli möys viereiset ruudut automaattisesti (ja niiden viereiset jos ei niistäkään löydy miinoja, rekursiivisesti).

Yksittäisen ruudun avaamisen ja ruudun lipulla merkitsemisen lisäksi on pelissä kolmaskin toiminta jonka pelaaja voi yksittäiseen pelikierrokseen liittyen suorittaa: voidaan automaattisesti avata jo avatun ruudun viereiset ruudut klikkaamalla avattua ruutua molemmilla hiiren painikkeilla, edellyttäen että klikatun ruudun vierusruuduista löytyy sama määrä lippumerkintöjä kuin ruutu kertoo olevan miinoja viereisissä ruuduissa. Tässä tapauksessa peli olettaa, että pelaaja tietää mitä on tekemässä ja avaa tälle automaattisesti kaikki klikatun ruudun viereiset lippumerkittömän ruudut. 

Itse peliruudun lisäksi näytetään pelajaalle aina pelikierroksen päätyttyä pisteruutu, ja jos nopeus esimäärätyllä vaikeusasteella riittää huippusijalle pyydetään tätä syöttämään nimi joka tallentuu huippupistetiedostoon. Pisteruutuun sisällytetään näppäimet uuden pelin aloittamiseen.

**Käyttäjä:**
Pelaaja.

**Toiminnot:**
- Uuden pelin aloitus
  - Valitaan vaikeusaste (ennaltamäärätyt ruudukon koko, miinojen määrä)
  - Voidaan myös määrittää ruudukon koko ja miinojen määrä itse (arvot validoidaan)
- Ruudun avaus
- Ruudun merkkaus
- Huippupisteiden katselu

**Luokkakaavio:**


![Luokkakaavio](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/MineSweeperClassDiagram.png)


**Sekvenssikaaviot:**

![Sekvenssikaavio1](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/SequenceDiagram1.png)

![Sekvenssikaavio2](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/SequenceDiagram2.png)
