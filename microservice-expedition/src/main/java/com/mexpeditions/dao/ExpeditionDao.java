package com.mexpeditions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mexpeditions.model.Expedition;

@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {
	
	Expedition findByIdCommande(int idCommande);

}
