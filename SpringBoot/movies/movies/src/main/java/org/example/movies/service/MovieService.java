package org.example.movies.service;

import lombok.RequiredArgsConstructor;
import org.example.movies.dto.*;
import org.example.movies.entity.Movie;
import org.example.movies.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public CreateMoiveResponse save(CreateMoiveRequest request){
        Movie movie = new Movie(
                request.getTitle()
        );
        Movie saveMovie = movieRepository.save(movie);
        return new CreateMoiveResponse(
                saveMovie.getId(),
                saveMovie.getTitle(),
                saveMovie.getCreatedAt(),
                saveMovie.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetMoiveResponse> getALL(){
        List<Movie> movies = movieRepository.findAll();
        List<GetMoiveResponse> dtos = new ArrayList<>();

        for (Movie movie : movies) {
            GetMoiveResponse dto = new GetMoiveResponse(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getCreatedAt(),
                    movie.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetMoiveResponse getOne(Long moiveId) {
        Movie movie = movieRepository.findById(moiveId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 영화입니다.")
        );
        return new GetMoiveResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getCreatedAt(),
                movie.getModifiedAt()
        );
    }

    @Transactional
    public UpdateMoiveResponse update(Long movieId, UpdateMoiveRequest request){
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalStateException("존재하지않는 영화입니다.")
        );
        return new UpdateMoiveResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getCreatedAt(),
                movie.getModifiedAt()
        );
    }

    @Transactional
    public void delete(Long movieId){
        boolean existence = movieRepository.existsById(movieId);
        if (!existence){
            throw new IllegalStateException("존재하지않는 영화입니다.");
        }
        movieRepository.deleteById(movieId);
    }




}
