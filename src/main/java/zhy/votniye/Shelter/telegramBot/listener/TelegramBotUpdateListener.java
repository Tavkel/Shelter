package zhy.votniye.Shelter.telegramBot.listener;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.implementations.TgBotServiceImpl;
import zhy.votniye.Shelter.services.interfaces.TgBotService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdateListener implements UpdatesListener {
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);
    private final TelegramBot telegramBot;
    private final TgBotService botService;
    private Map<String, Method> singleArgCommands;
    private Map<String, Method> doubleArgCommands;
    private Map<String, Method> callbacks;

    public TelegramBotUpdateListener(TelegramBot telegramBot, TgBotService botService) {
        this.telegramBot = telegramBot;
        this.botService = botService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
        singleArgCommands = Commands.getSingleArgCommandsMap();
        doubleArgCommands = Commands.getDoubleArgCommandsMap();
        callbacks = Commands.getCallbacks();
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null) {
                try {
                    processMessage(update.message());
                } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                    throw new RuntimeException(e); //todo handle exceptions
                }
            } else if (update.callbackQuery() != null) {
                processCallback(update.callbackQuery());
            }


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processMessage(Message message) throws
            IllegalArgumentException,
            InvocationTargetException,
            IllegalAccessException {
        Pattern patternDouble = Pattern.compile("(^/[\\w?]+)(\\s)(.+)");
        Matcher matcher = patternDouble.matcher(message.text());
        if (matcher.matches()) {
            var command = matcher.group(1);
            var args = matcher.group(3);
            if (doubleArgCommands.containsKey(command)) {
                var method = doubleArgCommands.get(command);
                method.invoke(botService, message.chat().id(), args);
                return;
            }
        }
        //DRY??
        Pattern patternSingle = Pattern.compile("(^/[\\w?]+)");
        matcher = patternSingle.matcher(message.text());
        if (matcher.matches()) {
            var command = matcher.group(1);
            if (singleArgCommands.containsKey(command)) {
                var method = singleArgCommands.get(command);
                method.invoke(botService, message.chat().id());
                return;
            }
        }
        throw new IllegalArgumentException("command not found");
    }

    private void processCallback(CallbackQuery callback) {

    }

    private static class Commands {
        //all commands in lower case!
        //todo automate the process maybe?
        private static Map<String, Method> getSingleArgCommandsMap() {
            Map<String, Method> result;
            //add commands here
            try {
                result = Map.of("/start", TgBotServiceImpl.class.getMethod("sayHello", long.class));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            return result;
        }

        private static Map<String, Method> getDoubleArgCommandsMap() {
            return null;
        }

        private static Map<String, Method> getCallbacks() {
            return null;
        }
    }
}
