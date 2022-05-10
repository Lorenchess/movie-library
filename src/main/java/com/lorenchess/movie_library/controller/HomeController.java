package com.lorenchess.movie_library.controller;

import com.lorenchess.movie_library.entities.Movie;
import com.lorenchess.movie_library.repositoties.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    MovieRepo movieRepo;

    @GetMapping("/")
    public String displayHome(Model model) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("movies", movies);

        return "main/home";
    }





}
