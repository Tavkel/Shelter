package zhy.votniye.Shelter.listener;

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
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.services.interfaces.tg.TgCallbackService;
import zhy.votniye.Shelter.services.interfaces.tg.TgCommandService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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

    /**
     * Creates maps of methods for commands and callbacks
     */
    @PostConstruct
    public void init() {
        singleArgCommands = Commands.getSingleArgCommandsMap();
        doubleArgCommands = Commands.getDoubleArgCommandsMap();
        callbacks = Commands.getCallbacks();
        telegramBot.setUpdatesListener(this);
    }

    /**
     * processes updates
     *
     * @see #processMessage(Message)
     * @see #processCallback(CallbackQuery)
     */
    @Override
    public int process(List<Update> updates) {

        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            if (update.message() != null && botService.getSessionIds().contains(update.message().chat().id())) {
                botService.getSession(update.message().chat().id()).setData(update.message().text());
                return;
            }

            if (update.message() != null) {
                try {
                    processMessage(update.message());
                } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException e) {
                    botService.sendErrorReport(update.message().chat().id(), e.getMessage());
                }
            } else if (update.callbackQuery() != null) {
                try {
                    processCallback(update.callbackQuery());
                } catch (InvocationTargetException | IllegalAccessException e) {
                    botService.sendErrorReport(update.message().chat().id(), e.getMessage());
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Receives message of update, checks its body for matching with patterns of command with or without arguments
     * and calls for corresponding method of {@link TgBotService}
     *
     * @param message
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void processMessage(Message message) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        if (message.text() == null) return;
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
            }
        }
    }

    /**
     * Receives {@link CallbackQuery} and calls for method of {@link TgBotService} corresponding to {@link CallbackQuery#data()}
     *
     * @param callback
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void processCallback(CallbackQuery callback) throws InvocationTargetException, IllegalAccessException {
        if (callbacks.containsKey(callback.data())) {
            var method = callbacks.get(callback.data());
            method.invoke(botService, callback.message());
        }
    }

    private static class Commands {
        //all commands in lower case!
        private static Map<String, Method> getSingleArgCommandsMap() {
            Map<String, Method> result = new HashMap<>();
            var methods = TgCommandService.class.getDeclaredMethods();
            for (var method : methods) {
                result.put(method.getName().toLowerCase(), method);
            }
            return result;
        }

        private static Map<String, Method> getDoubleArgCommandsMap() {
            return null;
        }

        private static Map<String, Method> getCallbacks() {
            Map<String, Method> result = new HashMap<>();
            var methods = TgCallbackService.class.getDeclaredMethods();
            for (var method : methods) {
                result.put(method.getName().toLowerCase(), method);
            }
            return result;
        }
    }
}
