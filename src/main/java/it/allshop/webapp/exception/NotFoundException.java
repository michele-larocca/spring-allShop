package it.allshop.webapp.exception;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -8729169303699924451L;
	private String messaggio;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String messaggio) {
		super(messaggio);
		this.messaggio = messaggio;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

}
