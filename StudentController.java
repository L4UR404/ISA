package com.example.isa.controller;

import com.example.isa.entiteti.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.isa.repository.PredmetRepository;
import com.example.isa.repository.StudentRepository;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private PredmetRepository predmetRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("predmeti", predmetRepo.findAll());
        return "student-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        model.addAttribute("predmeti", predmetRepo.findAll());
        return "student-form";
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentRepo.save(student);
        return "redirect:/students";
    }

    @PostMapping
    public String save(@ModelAttribute Student student) {
        studentRepo.save(student);
        return "redirect:/students";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentRepo.deleteById(id);
        return "redirect:/students";
    }
}
