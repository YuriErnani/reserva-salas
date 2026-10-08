package br.com.alura.reserva_salas.model;

import br.com.alura.reserva_salas.exception.RegraDeNegocioException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    public Usuario(String nome, String email) {
        validar(nome, email);

        this.nome = nome;
        this.email = email;
    }

    public static void validar(String nome, String email) {
        if (nome == null || nome.isBlank()) throw new RegraDeNegocioException("Nome do usuário obrigatório.");
        if (email == null || email.isBlank()) throw new RegraDeNegocioException("Email do usuário obrigatório.");
    }

    public void atualizar(String nome, String email) {
        validar(nome, email);

        if(this.email.equals(email)) throw new RegraDeNegocioException("Atualize os campos com novos campos distintos.");

        this.nome = nome;
        this.email = email;
    }

}
