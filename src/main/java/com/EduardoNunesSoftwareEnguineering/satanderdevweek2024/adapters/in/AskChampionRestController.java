package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.adapters.in;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application.AskChampionUseCase;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application.ListChampionsUseCase;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AskChampionRestController {
    //documentation settings
    @Tag(name = "Champions", description = "Champions domine end points")
    @RestController
    @RequestMapping("/champions")
    public record ListChampionsRestController(AskChampionUseCase useCase) {
        @CrossOrigin
        @PostMapping("/{id}/ask")
        public AskChampionResponse askChampion(@PathVariable Long id,@RequestBody AskChampionRequest request){
            String answer = useCase.askChampion(id, request.question());
            return new AskChampionResponse(answer);
        }
        public record AskChampionRequest(String question){}
        public record AskChampionResponse(String answer){}
    }
}
