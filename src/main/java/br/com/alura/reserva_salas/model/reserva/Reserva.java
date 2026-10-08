package br.com.alura.reserva_salas.model.reserva;

import br.com.alura.reserva_salas.exception.RegraDeNegocioException;
import br.com.alura.reserva_salas.model.sala.Sala;
import br.com.alura.reserva_salas.model.Usuario;
import br.com.alura.reserva_salas.model.sala.StatusSala;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Sala sala;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusReserva status;

    public Reserva(Sala sala, Usuario usuario, LocalDateTime inicio, LocalDateTime fim) {
        validar(sala, usuario, inicio, fim);

        this.sala = sala;
        this.usuario = usuario;
        this.inicio = inicio;
        this.fim = fim;
        this.status = StatusReserva.ATIVA;
    }

    private static void validar(Sala sala, Usuario usuario, LocalDateTime inicio, LocalDateTime fim) {
        if (sala == null || usuario == null) throw new RegraDeNegocioException("Sala e usuários obrigatórios.");

        if (sala.getStatus() == StatusSala.DESATIVADA) throw new RegraDeNegocioException("Não é possível reservar uma sala inativa.");

        if (inicio == null || fim == null) throw new RegraDeNegocioException("Início e fim da reserva obrigatórios.");

        if (!fim.isAfter(inicio)) throw new RegraDeNegocioException("Fim da reserva deve ser posterior ao início.");

    }

    public boolean conflita(LocalDateTime outroInicio, LocalDateTime outroFim) {
        return this.status == StatusReserva.ATIVA && this.inicio.isBefore(outroFim) && outroInicio.isBefore(this.fim);
    }

    public void cancelar() {
        if (this.status == StatusReserva.CANCELADA) throw new RegraDeNegocioException("A reserva já está cancelada.");
        this.status = StatusReserva.CANCELADA;
    }

}
