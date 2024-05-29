package imdl.scalator.service;

import imdl.scalator.domain.exception.EntityNotFoundException;
import imdl.scalator.domain.Escala;
import imdl.scalator.domain.Levita;
import imdl.scalator.domain.Musica;
import imdl.scalator.entity.EscalaEntity;
import imdl.scalator.persistence.EscalaRepository;
import imdl.scalator.persistence.LevitaRepository;
import imdl.scalator.persistence.MusicasRepository;
import imdl.scalator.service.mapper.LevitaMapper;
import imdl.scalator.service.mapper.MusicaMapper;

import java.util.List;
import java.util.UUID;

public class EscalaService {

    private final EscalaRepository escalaRepository;
    private final LevitaRepository levitaRepository;
    private final MusicasRepository musicasRepository;

    public EscalaService(EscalaRepository escalaRepository, LevitaRepository levitaRepository, MusicasRepository musicasRepository) {
        this.escalaRepository = escalaRepository;
        this.levitaRepository = levitaRepository;
        this.musicasRepository = musicasRepository;
    }

    public List<Escala> findAllEscalas(){
        return null;
    }

    public List<Escala> findMonthEscalas(){
        return null;
    }

    public Escala findById(UUID id){
        EscalaEntity entity = escalaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escala não encontrada."));
        Escala escala = new Escala();
        escala.setMinistro(findLevita(entity.getMinistro()));
        escala.setBaixo(findLevita(entity.getBaixo()));
        escala.setBateria(findLevita(entity.getBateria()));
        escala.setTeclado(findLevita(entity.getTeclado()));
        escala.setViolao(findLevita(entity.getViolao()));
        escala.setDate(entity.getDate());
        escala.setId(entity.getEscalaId());
        escala.setMusicas(musicasRepository.findAllInEscala(escala.getId()).stream().map(MusicaMapper::entityToDomain).toList());
        return escala;
    }

    public Escala create(Escala escala){



        return escala;
    }

    public Escala update(Escala escala){
        EscalaEntity entity = escalaRepository.findById(escala.getId()).orElseThrow(() -> new EntityNotFoundException("Escala não encontrada."));
        entity.setEscalaId(escala.getId());
        entity.setMinistro(escala.getMinistro().getId());
        entity.setBaixo(escala.getBaixo().getId());
        entity.setBateria(escala.getBateria().getId());
        entity.setTeclado(escala.getTeclado().getId());
        entity.setViolao(escala.getViolao().getId());
        entity.setMusicas(escala.getMusicas());
        entity.setDate(escala.getDate());
        return escala;
    }

    private Levita findLevita(UUID levitaId){
        return LevitaMapper.entityToDomain(levitaRepository.findById(levitaId)
                .orElseThrow(() -> new EntityNotFoundException("Levita não encontrada.")));
    }

    private List<Musica> findMusicas(List<UUID> musicasId){

    }

}
