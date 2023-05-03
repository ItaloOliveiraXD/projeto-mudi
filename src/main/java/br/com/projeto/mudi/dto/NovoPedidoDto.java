package br.com.projeto.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.StatusPedido;

public record NovoPedidoDto(
		@NotBlank
		String nomeProduto,
		@NotBlank
		String urlProduto,
		@NotBlank
		String urlImagem,
		String descricao) {
	
	public Pedido toPedido() {
		
		Pedido pedido = new Pedido();
		
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlProduto(urlProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setDescricao(descricao);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		return pedido;
	}
}
