**Aihe:**
Miinaharava. Lähes klooni Microsoftin omasta käyttöjärjestelmien mukana tulevasta pelistä. Peliä pelataan logiikkaa ohjailevan ikkunan kautta - pitkälti samaan tapaan kuin perinteisessä miinaharavassakin: ikkunassa on ruudukko painikkeita, jotka edustavat ruutuja. Ruutuja voidaan avata tai merkitä lipulla. Jos ruudukkoon piilotettu miina avataan häviää pelin. Jos ruudukon avaamattomien ruutujen määrä täsmää ennalta määrättyyn miinojen määrään (joka kerrotaan pelaajalle käyttöliittymässä pelin aikana) niin katsotaan pelaajan tietävän missä kaikki miinat sijaitsevat, eli miinat on haravoitu ja pelaaja voittaa pelin. Nopeasta suorituksesta saa paremmat pisteet.

Miinojen sijaintia voi arvioida avatuista ruuduista saadun tiedon perusteella: jokainen miinoittamaton ruutu kertoo kuinka montaa miinaa se koskettaa, jos yhtään. Jos avataan ruutu joka ei kosketa ainuttakaan miinaa, avaa peli möys viereiset ruudut automaattisesti (ja niiden viereiset jos ei niistäkään löydy miinoja, rekursiivisesti).

Yksittäisen ruudun avaamisen ja ruudun lipulla merkitsemisen lisäksi on pelissä kolmaskin toiminta jonka pelaaja voi yksittäiseen pelikierrokseen liittyen suorittaa: voidaan automaattisesti avata jo avatun ruudun viereiset ruudut klikkaamalla avattua ruutua hiiren vasemmalla painikkeella, edellyttäen että klikatun ruudun vierusruuduista löytyy sama määrä lippumerkintöjä kuin ruutu kertoo olevan miinoja viereisissä ruuduissa. Tässä tapauksessa peli olettaa, että pelaaja tietää mitä on tekemässä ja avaa tälle automaattisesti kaikki klikatun ruudun viereiset lippumerkittömän ruudut. 

Itse peliruudun lisäksi näytetään pelajaalle aina pelikierroksen päätyttyä pisteruutu, ja jos nopeus esimäärätyllä vaikeusasteella riittää huippusijalle pyydetään tätä syöttämään nimi joka tallentuu huippupistetiedostoon. Pisteruutuun sisällytetään näppäimet uuden pelin aloittamiseen.

**Käyttäjä:**
Pelaaja.

**Toiminnot:**
- Uuden pelin aloitus
  - Valitaan vaikeusaste (ennaltamäärätyt ruudukon koko, miinojen määrä)
  - Voidaan myös määrittää ruudukon koko ja miinojen määrä itse (arvot validoidaan)
- Ruudun avaus
- Ruudun merkkaus
- Viereisten ruutujen avaus

**Rakennekuvaus:**
Game luokka pelin kannalta keskeisin - edustaa yhtä miinaharavakierrosta (siis yhtä lautaa). Voidaan luoda kahdenlaisia pelejä: mukautettuja (omilla asetuksilla) tai jokin kolmesta esimääritellystä vaikeusasteesta. Mukautettujen asetusten validointi ja esimääritellyt asetukset löytyy GameSettings oliosta, joka luodaan Game-luokan konstruktorissa sisäisesti. GameSettingsin voi kuitenkin getata Game instanssista, koska siitä saa hyödyllistä tietoa käyttöliittymää varten (kentän leveys, korkeus, miinojen määrä). Game-instanssiin täytyy ennen pelaamisen aloittamista setUserInterfacella liittää jokin luokka joka toteuttaa UserInterface-rajapinnan.

Board ja Grid toimivat Game-luokassa pääasiassa sisäisesti, ne päivittävät pelin tilaa kun käyttöliittymältä tulee komentoja Game-instanssille - jokaisen pelaajan tekemän siirron jälkeen kuitenkin Board-instanssista palautuu lista Grid-referenssejä Gamelle, joka sitten kutsuu UserInterface-rajapinnan lupaamaa näkymän päivitysmetodia käyttöliittymässä ja passaa tämän listan käyttöliittymälle (näin ei koko näkymää tarvitse joka siirron jälkeen päivittää, vaan pelkästään ne ruudut jotka ovat muuttuneet). Käyttöliittymä ei voi muuttaa itse Grid-olioiden sisäistä tilaa näiden tietoa muokkaavien metodien näkyvyysrajoitteiden vuoksi.

- MineMapia käytetään Board luokan konstruktorissa sisäisesti eriyttämään laudan rakennuslogiikkaa itse laudan tilasta.
- StopWatch on Game luokan sisäinen pelin kestoa edustava luokka.

Game luokka tunnistaa kun peli päättyy ja tässä tapauksessa kutsuu käyttöliittymän pelin lopettavaa metodia. Käyttöliittymän vastuulla on estää uusien siirtojen teko pelin päätyttyä.


**Luokkakaavio:**

![Luokkakaavio](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/MineSweeperClassDiagram.png)


**Sekvenssikaaviot:**

(HUOM! Eivät vastaa todellisuutta tällä hetkellä, koodin rakenne muutettu äskettäin huomattavasti... uudet sekvenssikaaviot tulossa)

![Sekvenssikaavio1](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/SequenceDiagram1.png)

![Sekvenssikaavio2](https://github.com/ahv/MineSweeper/blob/master/dokumentaatio/SequenceDiagram2.png)
