package account;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import commons.Constants;
import okhttp3.OkHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;

public class SendMessageToTelegram {
    @Test
    public void sendBot() {
        OkHttpClient client = new OkHttpClient();

        TelegramBot bot = new TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

        long chatId = Constants.FIVE88_FAIL_ROOM_ID;

        //SendResponse response = bot.execute(new SendMessage(chatId, "Hello!"));

        SendMessage request = new SendMessage(chatId, "HelloBastian");
//                .parseMode(ParseMode.HTML)
//                .disableWebPagePreview(true)
//                .disableNotification(false)
//                .replyToMessageId(1)
//                .replyMarkup(new ForceReply());

        // sync
        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        Message message = sendResponse.message();

        // async
        bot.execute(request, new Callback<SendMessage, SendResponse>() {
            @Override
            public void onResponse(SendMessage request, SendResponse response) {}

            @Override
            public void onFailure(SendMessage request, IOException e) {}
        });
    }

}
