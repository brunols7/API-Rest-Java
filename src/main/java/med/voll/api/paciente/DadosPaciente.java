package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.endereco.DadosEndereco;

public record DadosPaciente(

        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @Valid
        DadosEndereco endereco
) {

}
