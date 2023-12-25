# Projekt Zaliczeniowy - PAI

Stworzyłem prostą aplikację zarządzającą todo
(rzeczami do zrobienia)

### Można za jej pomocą:
 - zalogowac sie na istniejące konto lub utworzyc nowe
 - dodawać (lub usówać) nowe todos - każde zawiera opis i planowany termin wykonania
 - edytować istniejące todos

### Aplikacja jest prosta, ale działa bez zarzutu, ponadto
### spełnia wszystkie postawione przed nią założenia, a mianowicie:
 - [x] ochronę treści strony przed niezalogowanymi użytkownikami
 - [x] utworzenie tabeli users, w której będą przechowywane dane urzytkowników
 - [x] możliwość przeprowadzania operacji na bazie danych (edytowanie todo)
 - [x] odporność na ataki typu SQL injection itd.
 - [x] Pełną walidację danych - zarówno użytkownika jak i todo


# Obsługa aplikacji
Aby móc uruchomić aplikację należy najpierw skonfigurować połączenie z bazą danych
Robi się to poprzez plik application.properties (src/main/resources/application.properties)

Należy skonfigurować następujące parametry:
 - spring.datasource.url
 - spring.datasource.username
 - spring.datasource.password

W przypadku kozystania z xamppa należy:
1. Uruchomić xamppa - server apache i mysql
2. utworzyc nową baze danych (nazwa wedlug uznania - w moim przypadku "project")
3. ustawic parametr spring.datasource.url - wystarczy zmienic ostatni człon linku na nazwę utworzonej przez siebie bazy danych
4. ustawic parametr spring.datasource.username na nazwę użytkownika, który będzie komunikował się z bazą danych (domyślny użytkownik to root i to sugeruje zostawic)
5. ustawic parametr spring.datasource.password na haslo uzytkownika podanego punkt wyzej (jesli uzytkownikiem jest root - haslo jest niepotrzebne - parametr mozna usunac)

## Po wykonaniu powyzszych krokow aplikacja jest gotowa do uruchomienia
Tabele zostaną utworzone automatycznie, ponadto automatycznie zostaną też utworzeni 2 urzytkownicy
o danych:

| username   | email                 | password    |
|------------|-----------------------|-------------|
| student    | student@pollub.edu.pl | 1234Pa$word |
| prowadzocy | prowadzocy@pollub.pl  | 1234Pa$word |
Oczywiście możliwe jest dodawanie nowych użytkowników

## Numer portu & linki
Aplikacja zostaje uruchomiona na serverze **tomcat**, na porcie **8080** - to od niego
nalezy rozpoczynac wpisywanie jakichkolwiek adresów 
#### Linki w aplikacji:

*AuthController*

| Endpoint       | Metoda | Opis                                        |
|----------------|--------|---------------------------------------------|
| /index         | GET    | Obsługuje żądanie strony głównej            |
| /              | GET    | Przekierowuje do strony głównej             |
| /register      | GET    | Wyświetla formularz rejestracji użytkownika |
| /register/save | POST   | Obsługuje żądanie rejestracji użytkownika   |
| /login         | GET    | Wyświetla formularz logowania               |
| /login         | POST   | obsługuje logowanie urzytkownika            |


<br>

*ToDoController*

| Endpoint     | Metoda | Opis                                         |
|--------------|--------|----------------------------------------------|
| /list-todos  | GET    | Pobiera listę wszystkich zadań               |
| /add-todo    | GET    | Wyświetla stronę do dodawania nowego zadania |
| /add-todo    | POST   | Dodaje nowe zadanie                          |
| /delete-todo | GET    | Usuwa zadanie o określonym identyfikatorze   |
| /update-todo | GET    | Wyświetla stronę aktualizacji zadania        |
| /update-todo | POST   | Aktualizuje zadanie                          |
