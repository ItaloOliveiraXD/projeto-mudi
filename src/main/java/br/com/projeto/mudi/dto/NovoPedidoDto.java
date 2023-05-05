package br.com.projeto.mudi.dto;

import javax.validation.constraints.NotBlank;

public record NovoPedidoDto(
		@NotBlank
		String nomeProduto,
		@NotBlank
		String urlProduto,
		@NotBlank
		String urlImagem,
		String descricao) {
	
}
