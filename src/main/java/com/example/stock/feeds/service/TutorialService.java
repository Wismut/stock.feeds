package com.example.stock.feeds.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TutorialService {
    final TutorialRepository tutorialRepository;

    public Flux<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    public Flux<Tutorial> findByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    public Mono<Tutorial> findById(int id) {
        return tutorialRepository.findById(id);
    }

    public Mono<Tutorial> save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    public Mono<Tutorial> update(int id, Tutorial tutorial) {
        return tutorialRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        tutorial.setId(id);
                        return tutorialRepository.save(tutorial);
                    }
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(int id) {
        return tutorialRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return tutorialRepository.deleteAll();
    }

    public Flux<Tutorial> findByPublished(boolean isPublished) {
        return tutorialRepository.findByPublished(isPublished);
    }
}
