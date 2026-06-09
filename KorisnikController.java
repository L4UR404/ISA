package com.example.isa.controller;

import com.example.isa.entiteti.Korisnik;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.isa.repository.KorisnikRepository;

@Controller
@RequestMapping("/korisnici")
public class KorisnikController {

    @Autowired
    private KorisnikRepository korisnikRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("korisnik", new Korisnik());
        return "korisnik-form";
    }

    @PostMapping
    public String save(@ModelAttribute Korisnik korisnik) {

        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        korisnikRepo.save(korisnik);
        return "redirect:/";
    }
}
