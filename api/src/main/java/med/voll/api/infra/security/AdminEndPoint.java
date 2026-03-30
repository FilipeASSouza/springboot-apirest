package med.voll.api.infra.security;

import java.util.Arrays;

public enum AdminEndPoint {
	
	EXCLUIR_MEDICO("/admin/medico/excluir"),
	EXCLUIR_PACIENTE("/admin/paciente/excluir"),
	LISTAR_USUARIO("/admin/usuario/listarUsuarios");
	
	private final String path;
	
	AdminEndPoint(String path) {
		this.path = path;
	}
	
	public String getPath() {
        return path;
    }

    public static String[] toArray() {
        return Arrays.stream(values())
                .map(AdminEndPoint::getPath)
                .toArray(String[]::new);
    }

}
