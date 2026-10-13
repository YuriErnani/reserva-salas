package br.com.alura.reserva_salas.model.sala;

import br.com.alura.reserva_salas.infra.exception.RegraDeNegocioException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "salas")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int capacidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSala status;

    public Sala(String nome, int capacidade) {
        validar(nome, capacidade);

        this.nome = nome;
        this.capacidade = capacidade;
        this.status = StatusSala.ATIVA;
    }

    private static void validar(String nome, int capacidade) {
        if (nome == null || nome.isBlank()) throw new RegraDeNegocioException("Nome da sala é obrigatório.");
        if (capacidade <= 0) throw new RegraDeNegocioException("Capacidade da sala deve ser positiva.");
    }

    public void atualizar(String nome, int capacidade) {
        validar(nome, capacidade);

        if(this.nome.equals(nome) && this.capacidade == capacidade) throw new RegraDeNegocioException("Atualize os campos com novos campos distintos.");

        this.nome = nome;
        this.capacidade = capacidade;
    }

    public void ativar() {
        this.status = StatusSala.ATIVA;
    }

    public void desativar() {
        this.status = StatusSala.DESATIVADA;
    }

}
