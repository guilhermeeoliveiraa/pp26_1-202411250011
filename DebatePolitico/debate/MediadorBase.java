package debate;

import configuracao.ConfiguraTempo;
import log.LogSistem;

public interface MediadorBase {
    void debate(ConfiguraTempo config, LogSistem log);
}