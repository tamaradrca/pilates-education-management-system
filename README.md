
Desktop Java aplikacija zasnovana na troslojnoj Klijent-Server arhitekturi 
sa prilagođenim Persistence Layer-om (Generic DB Broker).

---

## 🏗️ Struktura Projekta (Moduli)

Sistem se sastoji od tri odvojena NetBeans projekta:
1. **`Common`** – Sadrži domenske klase (Model) i zajedničke 
interfejse/prenosne objekte koji se koriste na obe strane.
2. **`Server`** – Poslovna logika, komunikacija sa MySQL bazom podataka, 
upravljanje transakcijama i serverski soketi.
3. **`Client`** – User Interface (Java Swing), kontroleri i komunikacija 
sa serverom.

---

## 📄 Dokumentacija

* 📘 [Pogledaj kompletnu dokumentaciju sistema (100 str. PDF)] 
https://drive.google.com/drive/folders/1xPOIo0-DohSRU-qsxc7cKqjsJw6tbvJf?usp=share_link
