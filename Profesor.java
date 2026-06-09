package com.example.isa.entiteti;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;
    @OneToMany(mappedBy = "profesor")
    private Set<Predmet> predmeti = new HashSet<>();


        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getIme() {
            return ime;
        }
        public void setIme(String ime) {
            this.ime = ime;
        }

        public String getPrezime() {
            return prezime;
        }
        public void setPrezime(String prezime) {
            this.prezime = prezime;
        }
    public Set<Predmet> getPredmeti() {
        return predmeti;
    }
    public void setPredmeti(Set<Predmet> predmeti) {
        this.predmeti = predmeti;
    }
}