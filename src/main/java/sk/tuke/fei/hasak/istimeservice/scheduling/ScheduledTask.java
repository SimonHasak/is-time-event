package sk.tuke.fei.hasak.istimeservice.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Šimon Hašák
 */
@Slf4j
@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 5000)
    public void checkTime() {
        log.info("vypis");
    }

}
