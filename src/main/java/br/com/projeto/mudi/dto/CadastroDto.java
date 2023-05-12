package br.com.projeto.mudi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record CadastroDto(
		@NotBlank
		String username,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String password) {

}
