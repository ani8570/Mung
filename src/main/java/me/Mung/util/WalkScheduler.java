package me.Mung.util;

import me.Mung.Model.CharHomeworkDAO;
import me.Mung.Model.IdHomeworkDAO;
import net.dv8tion.jda.api.entities.Activity;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;
import static me.Mung.Bot.jda;

public class WalkScheduler {
    public static final String SEOUL_ZONE = "Asia/Seoul";
    public static final int ONE_DAY = 1;
    public static final int WALKING_TERM = 5;
    public static final int REST_HOUR = 9;
    public static final int ONE_DAY_AS_SECOND = 24 * 60 * 60;
    public static final int ONE_WEEKEND_AS_SECOND = 7 * 24 * 60 * 60;
    public static final int ONE_WEEKEND_AS_DAY = 7;
    public static final int ONE_MINUTE_AS_MILLISECOND = 60 * 1000;
    public static final int ONE_MINUTE_AS_SECOND = 60;
    public static final int SINGLE_POOL_SIZE = 1;
    public static Timer sc;

    private final ScheduledExecutorService scheduler;
    Runnable command = new Runnable() {
        //입력시간에 산책 30분 후 원 상태로
        @Override
        public void run() {
            try {
                jda.getPresence().setActivity(Activity.playing("산책"));
                Thread.sleep(30 * ONE_MINUTE_AS_MILLISECOND);
                jda.getPresence().setActivity(Activity.playing("숙제 관리"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public WalkScheduler() {
        this.scheduler = Executors.newScheduledThreadPool(SINGLE_POOL_SIZE);
    }

    public void walking(int hour, int minute, int second) {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));
        ZonedDateTime nextExecutionTime = this.getNextExecutionTime(hour, minute, second, now);
        scheduler.scheduleAtFixedRate(command, this.getInitialExecutionTime(now, nextExecutionTime), ONE_DAY_AS_SECOND, SECONDS);
    }

    public void resetHomework(int hour, int minute, int second) {
        ZonedDateTime now = ZonedDateTime.of(now(), ZoneId.of(SEOUL_ZONE));
        ZonedDateTime nextExecutionTime = this.getNextExecutionTime(hour, minute, second, now);
        //수요일은 전부 초기화
        int countWednesday = (10 - nextExecutionTime.getDayOfWeek().getValue()) % ONE_WEEKEND_AS_DAY;
        scheduler.scheduleAtFixedRate(() -> {
                    CharHomeworkDAO.resetWeeklyCharHomework();
                    IdHomeworkDAO.resetWeeklyIdHomework();
                },
                this.getInitialExecutionTime(now, nextExecutionTime.plusDays(countWednesday)) + ONE_MINUTE_AS_SECOND * 10,
                ONE_WEEKEND_AS_SECOND, SECONDS);
        scheduler.scheduleAtFixedRate(CharHomeworkDAO::resetDailyCharHomework,
                this.getInitialExecutionTime(now, nextExecutionTime),
                ONE_DAY_AS_SECOND, SECONDS);
    }

    private ZonedDateTime getNextExecutionTime(int hour, int minute, int second, ZonedDateTime now) {
        ZonedDateTime nextExecutionTime;
        nextExecutionTime = now
                .withHour(hour)
                .withMinute(minute)
                .withSecond(second);
        if (this.isOverDay(now, nextExecutionTime))
            nextExecutionTime = nextExecutionTime.plusDays(ONE_DAY);

        return nextExecutionTime;
    }

    private boolean isOverDay(ZonedDateTime zonedNow, ZonedDateTime nextExecutionTime) {
        return zonedNow.compareTo(nextExecutionTime) > 0;
    }

    private long getInitialExecutionTime(ZonedDateTime now, ZonedDateTime nextExecutionTime) {
        Duration duration = Duration.between(now, nextExecutionTime);
        return duration.getSeconds();
    }

}
