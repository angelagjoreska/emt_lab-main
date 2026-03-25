#  Biblioteka API - EMT Laboratoriska Vezba
**Angela Gjoreska**

### 1. Filtriranje i Paginacija
Ovozmoženo e prebaruvanje na knigi so paginacija i sortiranje preku:
- `GET /api/books/filter`
- Poddržuva filtri za: Kategorija, Avtor i Dostapnost.

### 2. Proekcii (Projections)
Za pobrz odgovor na serverot, se koristat dva tipa na prikazi:
- **Short:** Osnovni podatoci za knigata.
- **Details:** Proširen prikaz so ime na avtor i država.

### 3. Database Views
- **Običen View:** Prikaz na site detali za knigite na edno mesto.
- **Materialized View:** Statistika po kategorija koja se osvežuva avtomatski na sekoi 60 sekundi.

### 4. Nastani i Logiranje (Events)
- Sekoja aktivnost (kako iznajmuvanje kniga) se zapišuva vo `ActivityLog`.
- Dokolku knigata ja potroši zalihata, se zapišuva `OutOfStockEvent`.

## 🛠 Tehnologii
- **Java 21** & **Spring Boot 3**
- **PostgreSQL** (Baza na podatoci)
- **Flyway** (Migracii na šemata)
- **Lombok** (Za počist kod)

## 📖 Kako da se koristi?
1. Proverete dali PostgreSQL e pušten na porta `5434`.
2. Startuvajte ja aplikacijata od `LibraryapiApplication.java`.
3. Testirajte gi site metodi preku **Swagger** na:
   `http://localhost:8080/swagger-ui/index.html`