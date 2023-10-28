//package zhy.votniye.Shelter.services.implementations;
//
//import com.pengrad.telegrambot.TelegramBot;
//import com.pengrad.telegrambot.model.Chat;
//import com.pengrad.telegrambot.model.Message;
//import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
//import com.pengrad.telegrambot.request.EditMessageReplyMarkup;
//import com.pengrad.telegrambot.request.EditMessageText;
//import com.pengrad.telegrambot.request.SendMessage;
//import com.pengrad.telegrambot.response.SendResponse;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import zhy.votniye.Shelter.services.implementations.tg.TgBotServiceImpl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class TgBotServiceImplTest {
//    @Mock
//    private TelegramBot telegramBot;
//    @InjectMocks
//    private TgBotServiceImpl sut;
//
//    @Mock
//    private Message message;
//    @Mock
//    private Chat chat;
//    @Mock
//    private SendResponse response;
//
//    @Test
//    void sayHello() {
//        long chatId = 1L;
//        SendMessage sendMessage = new SendMessage(chatId, "Hello! This is SHELTER-NAME bot-assistant! " +
//                "Please tell what i can do for you, or if you don't want to interact with me " +
//                "I can call one of the leather bags.");
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.addRow(TgBotServiceImpl.Button.ABOUT_SHELTER_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.LEAVE_CONTACT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.SUBMIT_REPORT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.CALL_VOLUNTEER_BUTTON.getButton());
//        sendMessage.replyMarkup(keyboard);
//
//        when(telegramBot.execute(any())).thenReturn(response);
//        when(response.isOk()).thenReturn(true);
//
//        sut.sayHello(chatId);
//        ArgumentCaptor<SendMessage> argument = ArgumentCaptor.forClass(SendMessage.class);
//        verify(telegramBot, times(1)).execute(argument.capture());
//        var args = argument.getAllValues();
//        var expectedMessageParams = sendMessage.getParameters();
//        var actualMessageParams = args.get(0).getParameters();
//        assertEquals(expectedMessageParams, actualMessageParams);
//    }
//
//    @Test
//    void callVolunteer_shouldSendMessage() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        SendMessage expectedMessage = new SendMessage(message.chat().id(),
//                "Called volunteer. Please await response, someone will reach out to you soon!");
//
//        ArgumentCaptor<SendMessage> argumentCaptorText = ArgumentCaptor.forClass(SendMessage.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.callVolunteer(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedMessageParams = expectedMessage.getParameters();
//        var actualMessageParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        assertEquals(expectedMessageParams, actualMessageParams);
//    }
//
//    @Test
//    void about_shouldEditMessageAndKeyboard() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                "What exactly I should tell you about our shelter?");
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.addRow(TgBotServiceImpl.Button.ABOUT_GENERAL_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_CONTACTS_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_PERMIT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_RULES_ON_TERRITORY.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.about(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void aboutGeneral_shouldEditMessageAndKeyboard() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                TgBotServiceImpl.ResponseMessages.getAboutGeneralMessage());
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard
//                .addRow(TgBotServiceImpl.Button.ABOUT_CONTACTS_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_PERMIT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_RULES_ON_TERRITORY.getButton())
//                .addRow(TgBotServiceImpl.Button.BACK_MAIN_BUTTON.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.aboutGeneral(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void aboutContacts() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                TgBotServiceImpl.ResponseMessages.getAboutContactsMessage());
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard
//                .addRow(TgBotServiceImpl.Button.ABOUT_GENERAL_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_PERMIT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_RULES_ON_TERRITORY.getButton())
//                .addRow(TgBotServiceImpl.Button.BACK_MAIN_BUTTON.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.aboutContacts(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void aboutEntryPermit() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                TgBotServiceImpl.ResponseMessages.getAboutEntryPermitMessage());
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard
//                .addRow(TgBotServiceImpl.Button.ABOUT_GENERAL_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_CONTACTS_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_RULES_ON_TERRITORY.getButton())
//                .addRow(TgBotServiceImpl.Button.BACK_MAIN_BUTTON.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.aboutEntryPermit(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void aboutRulesOnTerritory() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                TgBotServiceImpl.ResponseMessages.getAboutRulesOnTerritoryMessage());
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard
//                .addRow(TgBotServiceImpl.Button.ABOUT_GENERAL_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_CONTACTS_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.ABOUT_PERMIT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.BACK_MAIN_BUTTON.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.aboutRulesOnTerritory(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void backToMain() {
//        int messageId = 1;
//        long chatId = 1L;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(chatId);
//        when(message.messageId()).thenReturn(messageId);
//
//        EditMessageText expectedEditText = new EditMessageText(message.chat().id(),
//                message.messageId(),
//                "Want something else?");
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard
//                .addRow(TgBotServiceImpl.Button.ABOUT_SHELTER_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.LEAVE_CONTACT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.SUBMIT_REPORT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.CALL_VOLUNTEER_BUTTON.getButton());
//        EditMessageReplyMarkup expectedReplyMarkup = new EditMessageReplyMarkup(message.chat().id(),
//                message.messageId()).replyMarkup(keyboard);
//        ArgumentCaptor<EditMessageText> argumentCaptorText = ArgumentCaptor.forClass(EditMessageText.class);
//        ArgumentCaptor<EditMessageReplyMarkup> argumentCaptorMarkup = ArgumentCaptor.forClass(EditMessageReplyMarkup.class);
//
//        sut.backToMain(message);
//        verify(telegramBot, times(1)).execute(argumentCaptorText.capture());
//        verify(telegramBot, times(1)).execute(argumentCaptorMarkup.capture());
//        var expectedEditTextParams = expectedEditText.getParameters();
//        var expectedEditReplyMarkupParams = expectedReplyMarkup.getParameters();
//        var actualEditTextParams = argumentCaptorText.getAllValues().get(0).getParameters();
//        var actualEditReplyMarkupParams = argumentCaptorMarkup.getAllValues().get(0).getParameters();
//        assertEquals(expectedEditTextParams, actualEditTextParams);
//        assertEquals(expectedEditReplyMarkupParams, actualEditReplyMarkupParams);
//    }
//
//    @Test
//    void leaveContact() {
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(1L);
//        sut.leaveContact(message);
//        verify(telegramBot, times(2)).execute(any());
//    }
//
//    @Test
//    void leaveContactStep_finalStep_shouldSendMessageOk() {
//        int step = TgBotServiceImpl.LeaveContactSteps.FINISH.getStep();
//        long chatId = 1;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(1L);
//
//        sut.leaveContact(message);
//        sut.leaveContactStep(chatId, step);
//
//        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
//        keyboard.addRow(TgBotServiceImpl.Button.ABOUT_SHELTER_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.LEAVE_CONTACT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.SUBMIT_REPORT_BUTTON.getButton())
//                .addRow(TgBotServiceImpl.Button.CALL_VOLUNTEER_BUTTON.getButton());
//
//        SendMessage expectedMessage = new SendMessage(chatId, "Alright! All data written!").replyMarkup(keyboard);
//        ArgumentCaptor<SendMessage> sendMessageArgumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
//        verify(telegramBot, times(2)).execute(sendMessageArgumentCaptor.capture());
//        var expectedParams = expectedMessage.getParameters();
//        var actualParams = sendMessageArgumentCaptor.getAllValues().get(1).getParameters();
//        assertEquals(expectedParams, actualParams);
//    }
//
//    @Test
//    void leaveContactStep_notFinalStep_shouldSendMessageForNexStep() {
//        int step = TgBotServiceImpl.LeaveContactSteps.EMAIL.getStep();
//        long chatId = 1;
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(1L);
//        SendMessage expectedMessage = new SendMessage(chatId, "and now i need " + TgBotServiceImpl.LeaveContactSteps.values()[step - 1]);
//        ArgumentCaptor<SendMessage> sendMessageArgumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
//
//        sut.leaveContact(message);
//        sut.leaveContactStep(chatId, step);
//
//        verify(telegramBot, times(2)).execute(sendMessageArgumentCaptor.capture());
//        var expectedParams = expectedMessage.getParameters();
//        var actualParams = sendMessageArgumentCaptor.getAllValues().get(1).getParameters();
//        assertEquals(expectedParams, actualParams);
//    }
//
//    @Test
//    void dataIngestSessionFailure() {
//        int step = TgBotServiceImpl.LeaveContactSteps.EMAIL.getStep();
//        long chatId = 1;
//        SendMessage expectedMessage = new SendMessage(chatId, "Failed to process data " + TgBotServiceImpl.LeaveContactSteps.values()[step - 1]);
//        ArgumentCaptor<SendMessage> sendMessageArgumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
//
//        sut.dataIngestSessionFailure(chatId, step);
//
//        verify(telegramBot, times(1)).execute(sendMessageArgumentCaptor.capture());
//        var expectedParams = expectedMessage.getParameters();
//        var actualParams = sendMessageArgumentCaptor.getAllValues().get(0).getParameters();
//        assertEquals(expectedParams, actualParams);
//    }
//
//    @Test
//    void sendErrorReport() {
//    }
//}