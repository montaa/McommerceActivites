package com.mexpeditions.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mexpeditions.dao.ExpeditionDao;
import com.mexpeditions.model.Expedition;
import com.mexpeditions.web.exceptions.ExpeditionImpossibleException;
import com.mexpeditions.web.exceptions.ExpeditionIntrouvableException;

@RestController
public class ExpeditionController {

	@Autowired
	ExpeditionDao expeditionsDao;

	@PostMapping(value = "/expeditions")
	public ResponseEntity<Expedition> ajouterExpedition(@RequestBody Expedition expedition) {

		Expedition nouvelleExpedition = expeditionsDao.save(expedition);

		if (nouvelleExpedition == null)
			throw new ExpeditionImpossibleException("Impossible d'ajouter cette expedition");

		return new ResponseEntity<Expedition>(expedition, HttpStatus.CREATED);
	}

	@GetMapping(value = "/expeditions/{id}")
	public Optional<Expedition> recupererUneExpedition(@PathVariable int id) throws ExpeditionIntrouvableException {

		Optional<Expedition> expedition = expeditionsDao.findById(id);

		if (!expedition.isPresent())
			throw new ExpeditionIntrouvableException("Cette expedition n'existe pas");

		return expedition;
	}

	@PutMapping(value = "/expeditions/{id}")
	public void updateExpedition(@PathVariable int id, @RequestBody Expedition expedition)
			throws ExpeditionIntrouvableException {

		Optional<Expedition> opt = expeditionsDao.findById(id);
		if (opt.isPresent()) {

			Expedition toUpdate = opt.get();
			toUpdate.setEtat(expedition.getEtat());
			toUpdate.setIdCommande(expedition.getIdCommande());
			expeditionsDao.save(toUpdate);
		} else
			throw new ExpeditionIntrouvableException("Cette expedition n'existe pas");

	}
}
