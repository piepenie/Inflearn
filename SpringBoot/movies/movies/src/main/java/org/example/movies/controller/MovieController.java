package org.example.movies.controller;

import lombok.RequiredArgsConstructor;
import org.example.movies.dto.*;
import org.example.movies.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<CreateMoiveResponse> create(@RequestBody CreateMoiveRequest request){
        CreateMoiveResponse result = movieService.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<GetMoiveResponse>> getALL(){
        List<GetMoiveResponse> result = movieService.getALL();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<GetMoiveResponse> getOne(@PathVariable Long movieId){
        GetMoiveResponse result = movieService.getOne(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<UpdateMoiveResponse> update(
            @PathVariable Long movieId,
            @RequestBody UpdateMoiveRequest request
            ) {
            UpdateMoiveResponse result = movieService.update(movieId, request);
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> delete(@PathVariable Long movieId){
        movieService.delete(movieId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
