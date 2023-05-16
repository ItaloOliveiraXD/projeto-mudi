package br.com.projeto.mudi.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.projeto.mudi.dto.NovoPedidoDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nomeProduto;
	private BigDecimal valor;
	private LocalDate dataDaEntrega;
	private String urlProduto;
	private String urlImagem;
	private String descricao;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private User user;
	
	public Pedido(NovoPedidoDto novoPedidoDto, User user) {
		
		this.user = user;
		
		this.nomeProduto = novoPedidoDto.nomeProduto();
		this.urlProduto = novoPedidoDto.urlProduto();
		this.urlImagem = novoPedidoDto.urlImagem();
		this.descricao = novoPedidoDto.descricao();
		this.status = StatusPedido.AGUARDANDO;
	}

}
