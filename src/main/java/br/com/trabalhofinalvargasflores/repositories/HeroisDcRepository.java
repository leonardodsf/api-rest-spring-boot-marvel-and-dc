package br.com.trabalhofinalvargasflores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.trabalhofinalvargasflores.entities.HeroisDc;

@Repository
public interface HeroisDcRepository extends JpaRepository<HeroisDc, Long> {

}
