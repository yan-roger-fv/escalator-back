package imdl.scalator.controller;

import imdl.scalator.domain.Escala;
import imdl.scalator.domain.input.EscalaInput;
import imdl.scalator.service.EscalaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("v1/escala")
public class EscalaController {

    private final EscalaService escalaService;

    public EscalaController(EscalaService escalaService) {
        this.escalaService = escalaService;
    }

    @GetMapping
    @Operation(summary = "Lista com todas as escalas.")
    public List<Escala> listEscalas(){
        return escalaService.findAllEscalas();
    }

    @GetMapping("/{mes}")
    @Operation(summary = "Lista com todas as escalas de um determinado mes.")
    public List<Escala> listInPeriodo(){
        return escalaService.findMonthEscalas();
    }

    @PostMapping
    @Operation(summary = "Criar uma nova escala.")
    public Escala createEscala(EscalaInput input){
        return escalaService.create(input);
    }

}
