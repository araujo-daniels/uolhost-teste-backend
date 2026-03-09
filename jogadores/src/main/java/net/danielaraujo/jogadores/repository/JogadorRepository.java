package net.danielaraujo.jogadores.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.danielaraujo.jogadores.model.Jogador;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends CrudRepository<Jogador, Integer>{

	Integer countByGrupoAndCodinome(String grupo, String codinome);

	@Query("SELECT j.codinome FROM Jogador j WHERE j.grupo = :grupo")
	List<String> findCodinomeByGrupo(@Param("grupo") String grupo);
	
}
