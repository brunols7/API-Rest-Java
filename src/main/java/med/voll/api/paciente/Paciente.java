package med.voll.api.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.voll.api.endereco.Endereco;

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

    public Paciente (DadosPaciente dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
   }

}
