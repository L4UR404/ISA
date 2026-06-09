# ISA
Ispitni projekat. 
<br>


<br>
<h1>Uvod</h1>
<br>
Projekat je realizovan kao Spring MVC web aplikacija sa MySQL bazom podataka.
<br>
Cilj je implementacija CRUD operacija nad entitetima: Student, Profesor, Predmet 
<br>
Aplikacija obezbeđuje autentifikaciju i autorizaciju preko rola (STUDENT, PROFESOR) i upravljanjem sesijama.
<br>
Frontend je realizovan pomoću Thymeleaf šablona i CSS‑a za stilizaciju.
<br>


<h2>Link za postman:</h2>
<br>
https://documenter.getpostman.com/view/55495092/2sBXwqsWqm
<br>


<h3>Arhitektura</h3>

Backend: Spring Boot + Spring MVC + Spring Security.
<br>
Frontend: Thymeleaf + CSS.
<br>
Baza podataka: MySQL sa 5 tabela.
<br>
Paketi:
<br>
controller – kontroleri za CRUD operacije.
<br>
service – Spring Security.
<br>
repository – JPA repozitorijumi.
<br>
modeli – entiteti (Student, Profesor, Predmet, Korisnik).
<br>


<h3>Baza podataka</h3>

<h5>Tabele</h5>

Student – id, ime, prezime, indeks
<br>
Profesor – id, ime, prezime
<br>
Predmet – id, naziv, opis, profesor_id
<br>
Korisnik – id, username, password, role
<br>
Student_Predmet – spojna tabela za ManyToMany vezu
<br>
<h5>Relacije</h5>

Profesor – Predmet: OneToMany
<br>
Student – Predmet: ManyToMany preko spojne tabele
<br>
Korisnik – Role: definisano u polju role
<br>


<h3>Funkcionalnosti</h3>

-CRUD operacije za entitete.
<br>
-Autentifikacija i autorizacija:
<br>
Login forma (/login).
<br>
Role STUDENT i PROFESOR.
<br>
-STUDENT: može da vidi listu predmeta i studenata.
<br>
-PROFESOR: može da menja predmete, dodaje studente, briše i menja podatke.
<br>
-Upravljanje sesijama:
<br>
Samo jedna sesija po korisniku (maximumSessions(1)).
<br>
Logout briše sesiju i cookie (invalidateHttpSession(true), deleteCookies("JSESSIONID")).
<br>
Timeout sesije: 60 minuta neaktivnosti (server.servlet.session.timeout=60m).
<br>
Zaštita od session hijacking‑a (sessionFixation().migrateSession()).
<br>


<h3>Frontend</h3>

<h5>Thymeleaf stranice:</h5>

index.html – početna stranica sa navigacijom.
<br>
students.html, profesors.html, predmeti.html – liste sa CRUD dugmadima.
<br>
student-form.html, profesor-form.html, predmet-form.html – forme za unos/izmenu.
<br>
login.html – login forma.
<br>
 <h5>Role‑based prikaz:</h5>

Dugmad za CRUD sakrivena studentima (sec:authorize="hasRole('PROFESOR')").
<br>
Student vidi samo listu predmeta.
