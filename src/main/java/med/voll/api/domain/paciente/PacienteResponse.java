package med.voll.api.domain.paciente;

public record PacienteResponse(Long id, String nome, String email, String telefone) {

    public PacienteResponse(Paciente paciente){
        this(paciente.getId (), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }

}
