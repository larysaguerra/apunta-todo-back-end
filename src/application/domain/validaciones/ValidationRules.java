package application.domain.validaciones;

import java.util.function.Predicate;

public class ValidationRules {

    public static final Predicate<String> NOMBRE_VALIDO =
            s -> s != null && !s.isBlank() && s.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");

    public static final Predicate<String> EMAIL_VALIDO =
            s -> s != null && s.matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");

    public static final Predicate<String> CONTRASENA_VALIDA =
            s -> s != null && s.length() >= 8;

    public static final Predicate<Integer> CANTIDAD_VALIDA =
            n -> n != null && n > 0;

    public static final Predicate<String> FECHA_VALIDA =
            s -> s != null && s.matches("\\d{4}-\\d{2}-\\d{2}");
}
