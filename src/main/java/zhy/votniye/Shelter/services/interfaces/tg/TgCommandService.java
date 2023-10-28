package zhy.votniye.Shelter.services.interfaces.tg;

public interface TgCommandService {
    void start(long chatId);
    void startFresh(long chatId);
}
