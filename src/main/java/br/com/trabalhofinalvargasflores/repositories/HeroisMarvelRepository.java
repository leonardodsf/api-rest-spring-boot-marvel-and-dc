package br.com.trabalhofinalvargasflores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trabalhofinalvargasflores.entities.HeroisMarvel;


@Repository
public interface HeroisMarvelRepository extends JpaRepository<HeroisMarvel, Long> {

}
