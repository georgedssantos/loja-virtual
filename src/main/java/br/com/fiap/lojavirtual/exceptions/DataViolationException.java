package br.com.fiap.lojavirtual.exceptions;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataViolationException(String mensagem) {
		super(mensagem);
	}
	
	public DataViolationException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public DataViolationException(Long id) {
		this(String.format("Código %d não pode ser removido, pois está em uso.", id));
	}

}
