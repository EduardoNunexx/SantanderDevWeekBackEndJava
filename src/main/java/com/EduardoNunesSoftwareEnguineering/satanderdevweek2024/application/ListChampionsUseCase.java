package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.application;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.ChampionsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public record ListChampionsUseCase (ChampionsRepository repository){
    public List<Champions> findAll(){
        return repository.findAll();
    }
}
