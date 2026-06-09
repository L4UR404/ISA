package com.example.isa.entiteti;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;

@Entity
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String opis;

    @ManyToMany(mappedBy = "predmeti",  cascade = CascadeType.REMOVE)
    private Set<Student> studenti = new HashSet<>();



    @ManyToOne
    @JoinColumn(name = "profesor_id", nullable = true)
    @org.hibernate.annotations.OnDelete(action = org.hibernate.annotations.OnDeleteAction.SET_NULL)
    private Profesor profesor;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }
    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }
}