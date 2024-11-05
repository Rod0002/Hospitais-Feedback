package com.uninove.hospitaisfeedback.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco; // Pode ser adicionado mais campos conforme necessário

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    @JsonManagedReference // Evita recursão infinita ao serializar hospital
    private List<Feedback> feedbacks;

    @Column(name = "media_avaliacoes")
    private double mediaAvaliacoes; // Atributo para armazenar a média das avaliações

    // Método para calcular a média de avaliações
    public double calcularMediaAvaliacoes() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return 0;
        }
        return feedbacks.stream()
                .mapToInt(Feedback::getAvaliacao)
                .average()
                .orElse(0);
    }

    // Método para atualizar a média de avaliações
    public void atualizarMediaAvaliacoes() {
        this.mediaAvaliacoes = calcularMediaAvaliacoes();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
        atualizarMediaAvaliacoes(); // Atualiza a média sempre que a lista de feedbacks for alterada
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public void setMediaAvaliacoes(double mediaAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }
}
