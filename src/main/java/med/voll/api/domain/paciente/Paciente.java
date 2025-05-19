package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;

    @Embedded
    @Valid
    private Endereco endereco;

    private Boolean ativo;

    public Paciente (DadosPaciente dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
   }

    public void atualizarPaciente(@Valid DadosAtualizacaoPaciente dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
    }

    public void excluir() {
        this.ativo = false;
    }
}
