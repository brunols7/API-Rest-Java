package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;

public record DadosPacienteResponse(Long id, String nome, String email, String telefone, Endereco endereco, Boolean ativo) {

    public DadosPacienteResponse(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco(), paciente.getAtivo());
    }

}
