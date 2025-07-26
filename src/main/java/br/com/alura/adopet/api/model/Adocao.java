package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adocoes")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;

    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private String justificativaStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(id, adocao.id);
    }

    //default para jpa
    public Adocao(){}

    public Adocao(Pet pet, Tutor tutor, String motivo) {
        this.pet = pet;
        this.tutor = tutor;
        this.motivo = motivo;
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO;
        this.data = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String getMotivo() {
        return motivo;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO;
    }

    public void marcarComoReprovado(@NotBlank String justificativa) {
        this.status = StatusAdocao.REPROVADO;
        this.justificativaStatus = justificativa;
    }
}
