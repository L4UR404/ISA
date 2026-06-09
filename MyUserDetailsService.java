package com.example.isa.service;

import com.example.isa.entiteti.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.isa.repository.KorisnikRepository;

import java.util.Collections;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private KorisnikRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Korisnik korisnik = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nije pronađen"));
        return new User(
                korisnik.getUsername(),
                korisnik.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + korisnik.getRole()))
        );
    }


}