package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model;

public record Champions(Long id, String name,String role, String lore, String imageUrl) {
    public String generateContextByQuestion(String question){
        return """
                Question: %s
                Champion Name: %s
                Role: %s
                Lore: %s
                """.formatted(question, this.name,this.role,this.lore);

    }
}
