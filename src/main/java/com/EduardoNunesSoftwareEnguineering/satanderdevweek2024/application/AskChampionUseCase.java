package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.exception.ChampionNotFoundException;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.ChampionsRepository;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.GenerativeAiApi;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiApi genAiApi) {
    @CrossOrigin
    public String askChampion(Long championId, String question){
        //using or Else in case that don't find a champion, this exception will be thrown
        Champions champion = repository.findById(championId).orElseThrow(()-> new ChampionNotFoundException(championId));
        String context = champion.generateContextByQuestion(question);
        String objective= """
                    Act like an assistant that can act like league of legends champions. 
                    Answer questions incorporating the personality and style of a determinate champion.
                    Down bellow the question, champion name and his respective lore:
           
                """;
        return genAiApi.generateContent(objective,context);
    }
}
