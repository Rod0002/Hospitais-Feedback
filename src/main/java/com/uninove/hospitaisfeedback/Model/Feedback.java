package com.uninove.hospitaisfeedback.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;

    @Min(1) // Avaliação mínima é 1
    @Max(5) // Avaliação máxima é 5
    private int avaliacao;

    @ManyToOne
    @NotNull(message = "Usuário não pode ser nulo")
    private Usuario usuario;

    @ManyToOne
    @NotNull(message = "Hospital não pode ser nulo")
    private Hospital hospital;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        if (avaliacao < 1 || avaliacao > 5) {
            throw new IllegalArgumentException("Avaliação deve ser entre 1 e 5");
        }
        this.avaliacao = avaliacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
