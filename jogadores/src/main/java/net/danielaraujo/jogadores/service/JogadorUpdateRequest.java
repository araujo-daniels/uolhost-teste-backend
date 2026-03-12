package net.danielaraujo.jogadores.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import net.danielaraujo.jogadores.model.Jogador;

@Getter
@Setter
public class JogadorUpdateRequest {
    @NotBlank(message = "O campo nome é de preenchimento obrigatório")
    private String nome;

    @Email(message = "Informe um e-mail válido")
    @NotBlank(message = "O campo e-mail é de preenchimento obrigatório")
    private String email;

    private String telefone;
}
