import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {

    @Disabled("Откл.")
    @Timeout(22)
    @DisplayName("Проверка, что main выполняется быстрее 22 секунд")
    @Test
    void mainTimeOutIn22Seconds() throws Exception {

        Main.main(new String[]{});

    }
}