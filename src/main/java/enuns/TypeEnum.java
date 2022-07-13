package enuns;

import org.springframework.security.core.GrantedAuthority;

public enum TypeEnum implements GrantedAuthority {

	ROLE_ADMIN("Usuario tipo Administrador"), 
	ROLE_COPERADO("Usuario tipo Coperado");
	
	private final String label;

	TypeEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String getAuthority() {
		return null;
	}
	
}
