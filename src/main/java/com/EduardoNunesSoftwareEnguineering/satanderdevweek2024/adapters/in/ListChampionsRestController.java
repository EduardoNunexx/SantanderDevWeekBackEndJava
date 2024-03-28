package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.adapters.in;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application.ListChampionsUseCase;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.ChampionsRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//documentation settings
@Tag(name = "Champions", description = "Champions domine end points")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
    @GetMapping
    public List<Champions> findAll(){
        return useCase.findAll();
    }
}
