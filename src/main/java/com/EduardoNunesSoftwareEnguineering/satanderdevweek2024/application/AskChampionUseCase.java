package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.exception.ChampionNotFoundException;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.ChampionsRepository;

import java.util.Optional;

public record AskChampionUseCase(ChampionsRepository repository) {
    public String askChampion(Long championId, String question){
        //using or Else in case that don't find a champion, this exception will be thrown
        Champions champion = repository.findById(championId).orElseThrow(()-> new ChampionNotFoundException(championId));
        String championContext = champion.generateContextByQuestion(question);
        //todo: evolve the business logic to consider the ias generative integration
        return championContext;
    }
}
