package com.example.isa.entiteti;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;
    private String indeks;
    @ManyToMany
    @JoinTable(
            name = "student_predmet",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "predmet_id")
    )
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

    public String getIndeks() {
        return indeks;
    }
    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public Set<Predmet> getPredmeti() {
        return predmeti;
    }
    public void setPredmeti(Set<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

}


