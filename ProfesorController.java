package com.example.isa.controller;

import com.example.isa.entiteti.Predmet;
import com.example.isa.entiteti.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.isa.repository.PredmetRepository;
import com.example.isa.repository.ProfesorRepository;

@Controller
@RequestMapping("/profesors")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepo;

    @Autowired
    private PredmetRepository predmetRepo;


    @GetMapping
    public String list(Model model) {
        model.addAttribute("profesors", profesorRepo.findAll());
        return "profesors";
    }


    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("profesor", new Profesor());
        return "profesor-form";
    }


    @PostMapping
    public String save(@ModelAttribute Profesor profesor,
                       @RequestParam String nazivPredmeta) {
        Profesor saved = profesorRepo.save(profesor);

        Predmet predmet = new Predmet();
        predmet.setNaziv(nazivPredmeta);
        predmet.setProfesor(saved);

        predmetRepo.save(predmet);

        return "redirect:/profesors";
    }


    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor Id:" + id));
        model.addAttribute("profesor", profesor);
        return "profesor-form";
    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Profesor profesorPodaci,
                         @RequestParam(required = false) String nazivPredmeta) {
        Profesor profesor  = profesorRepo.findById(id)
                        .orElseThrow(()-> new IllegalArgumentException("invalid profesor id"+ id));
        profesor.setIme(profesorPodaci.getIme());
        profesor.setPrezime(profesorPodaci.getPrezime());
        profesor.setPredmeti(profesorPodaci.getPredmeti());
        Profesor saved = profesorRepo.save(profesor);


        if (nazivPredmeta != null && !nazivPredmeta.isEmpty()) {
            Predmet predmet = new Predmet();
            predmet.setNaziv(nazivPredmeta);
            predmet.setProfesor(saved);
            predmetRepo.save(predmet);
        }

        return "redirect:/profesors";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        profesorRepo.deleteById(id);
        return "redirect:/profesors";
    }
}
