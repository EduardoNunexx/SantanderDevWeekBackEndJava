package com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.adapters.out;

import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.model.Champions;
import com.EduardoNunesSoftwareEnguineering.satanderdevweek2024.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {
    //control inversion
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Champions> rowMapper;
    //dependency injection
    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = ((rs,rowNum)->new Champions(rs.getLong("id"),rs.getString("name"),rs.getString("role"),rs.getString("lore"),rs.getString("name")));
    }

    @Override
    public List<Champions> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", rowMapper);
    }

    @Override
    public Optional<Champions> findById(Long id) {
        String sql= "SELECT * FROM CHAMPIONS WHERE ID = ?";
        List<Champions>  champion =jdbcTemplate.query(sql,rowMapper, id);
        return champion.stream().findFirst();
    }
}