package med.voll.api.infra.security;

import java.util.Arrays;

public enum PublicEndPoint {
	
	AUTH_LOGIN("/auth/login"),
	AUTH_CADASTRAR_USUARIOS("/auth/cadastrarUsuario"),
	SWAGGER_DOCS("/v3/api-docs/**"),
    SWAGGER_UI("/swagger-ui/**");
	
	private final String path;

    PublicEndPoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static String[] toArray() {
        return Arrays.stream(values())
                .map(PublicEndPoint::getPath)
                .toArray(String[]::new);
    }

}
