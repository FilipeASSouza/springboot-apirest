package med.voll.api.infra.exception;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ConstraintViolationResolver {

    private static final Map<String, String> CONSTRAINT_MESSAGES = Map.of(
            "medicos_crm_key", "CRM já cadastrado.",
            "medicos_email_key", "E-mail já cadastrado."
    );

    public String resolveMessage(Throwable rootCause) {
        if (rootCause == null) {
            return "Erro de integridade.";
        }

        String msg = rootCause.getMessage();

        for (Map.Entry<String, String> entry : CONSTRAINT_MESSAGES.entrySet()) {
            if (msg.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        if (msg.contains("duplicate key")) {
            return "Registro já existe (valor duplicado).";
        }

        return null;
    }
}
