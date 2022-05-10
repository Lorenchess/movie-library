package com.lorenchess.movie_library.controller;

import com.lorenchess.movie_library.entities.Movie;
import com.lorenchess.movie_library.fileuploadutil.FileUploadUtil;
import com.lorenchess.movie_library.repositoties.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    MovieRepo movieRepo;


   @GetMapping("/new")
   public String displayMovieForm(Model model) {
       Movie movie = new Movie();

       model.addAttribute("movie", movie);

       return "movie-pages/movie-form";
   }

   /**
    * This method also checks the hidden id passed if the Movie object contains the id passed it will update it otherwise it will save it. It also handles the upload image passed.
    * Note that we store only the file name in the database table, and the actual uploaded file is stored in the file system.
    * */

   @PostMapping("/save")
   public RedirectView saveMovie(Movie movie, @RequestParam("image") MultipartFile multipartFile)throws IOException {

       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       movie.setImage(fileName);

       Movie savedMovie = movieRepo.save(movie); //persisted to the database

       String uploadDir = "./movie-image/" + savedMovie.getId();

       FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

       return new RedirectView("/movies/new", true);
   }


    @GetMapping("/showUpdateMovie")
    public ModelAndView showUpdateMovie(@RequestParam Long id) {
        ModelAndView mav = new ModelAndView("movie-pages/movie-form"); //gets the model html page we need to display
        Movie movie = movieRepo.findById(id).get(); //finds the movie id passed by the query params
        mav.addObject("movie", movie);  //sets the view wit hte updated values
        return mav;
        //after clicking in the add movie btn in the movie-form page it will get the updated fields, and it will  save the updates thanks to the hidden id in the form

    }

    @GetMapping("/showMovie")
    public String showMovie(@RequestParam Long id, Model model) {
       Movie movie = movieRepo.findById(id).get();
       model.addAttribute("movie", movie);

       return "movie-pages/display-movie";

    }

    @GetMapping("/deleteMovie")
    public String deleteMovie(@RequestParam Long id) {
       movieRepo.deleteById(id);

       return "redirect:/";
    }

}
