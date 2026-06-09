package com.example.isa.controller;

import com.example.isa.entiteti.Predmet;
import com.example.isa.entiteti.Profesor;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.isa.repository.PredmetRepository;
import com.example.isa.repository.ProfesorRepository;

@Controller
@RequestMapping("/predmeti")
public class PredmetController {
    @Autowired
    private PredmetRepository predmetRepo;
    @Autowired private ProfesorRepository profesorRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("predmeti", predmetRepo.findAll());
        return "predmeti";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("predmet", new Predmet());
        model.addAttribute("profesors", profesorRepo.findAll());
        return "predmet-form";
    }

    @PostMapping
    public String save(@ModelAttribute Predmet predmet, @RequestParam Long profesorId) {
        Profesor profesor = profesorRepo.findById(profesorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor Id:" + profesorId));
        predmet.setProfesor(profesor);
        predmetRepo.save(predmet);
        return "redirect:/predmeti";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Predmet predmet = predmetRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid predmet Id:" + id));
        model.addAttribute("predmet", predmet);
        model.addAttribute("profesors", profesorRepo.findAll());
        return "predmet-form";
    }


    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Predmet predmet,
                         @RequestParam Long profesorId) {
        predmet.setId(id);
        Profesor profesor = profesorRepo.findById(profesorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid profesor Id:" + profesorId));
        predmet.setProfesor(profesor);
        predmetRepo.save(predmet);
        return "redirect:/predmeti";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Predmet predmet = predmetRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nevalidan id predmeta: " + id));



        predmetRepo.delete(predmet);
        return "redirect:/predmeti";
    }
}
