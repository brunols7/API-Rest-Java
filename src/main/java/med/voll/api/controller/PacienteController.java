package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity addPaciente(@RequestBody @Valid DadosPaciente request, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(request);
        pacienteRepository.save(paciente);

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosPacienteResponse(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> listPacientes(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        var pacientes = pacienteRepository.findAllByAtivoTrue(paginacao).map(PacienteResponse::new);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPacienteById(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosPacienteResponse(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);

        return ResponseEntity.ok(new DadosPacienteResponse(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

}
