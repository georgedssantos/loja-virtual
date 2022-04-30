package br.com.fiap.lojavirtual.exceptions;


public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public NotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public NotFoundException(Long id) {
		this(String.format("Não existe um cadastro com código %d.", id));
	}

}
