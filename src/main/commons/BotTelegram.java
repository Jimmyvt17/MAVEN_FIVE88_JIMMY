package commons;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import okhttp3.OkHttpClient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BotTelegram {
    OkHttpClient client = new OkHttpClient();
    TelegramBot bot = new TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

    public String getUpdate() {
        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

        return updates.toString();

    }

    public void createWebhook(String url, String path) {
        SetWebhook request = new SetWebhook()
                .url(url)
                .certificate(new byte[]{})
                .certificate(new File(path));

        BaseResponse response = bot.execute(request);
        boolean ok = response.isOk();

//        Update update1 = BotUtils.parseUpdate("request"); // from String
//        Update update2 = BotUtils.parseUpdate(reader); // or from java.io.Reader
//
//        Message message = update1.message();

    }

    public void botListener() {
        OkHttpClient client = new OkHttpClient();

        TelegramBot bot = new com.pengrad.telegrambot.TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {

                // process updates

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });

    }

    public void botStopListen() {
        OkHttpClient client = new OkHttpClient();

        TelegramBot bot = new com.pengrad.telegrambot.TelegramBot.Builder(Constants.FIVE88BOT).okHttpClient(client).build();

        bot.removeGetUpdatesListener();

    }

    public void sendBot(TelegramBot bot, String text) {
        long chatId = Constants.FIVE88_FAIL_ROOM_ID;
        SendMessage request = new SendMessage(chatId, text)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
//                .replyToMessageId(1)
//                .replyMarkup(new ForceReply());

        SendResponse sendResponse = bot.execute(request);
        boolean ok = sendResponse.isOk();
        Message message = sendResponse.message();

//       if (text.contains("TSport")) {
//            long chatId = Constants.SPORTBOOK_ROOM_ID;
//            SendMessage request = new SendMessage(chatId, text);
//            // sync
//            SendResponse sendResponse = bot.execute(request);
//        } else {
//            long chatId = Constants.FIVE88_FAIL_ROOM_ID;
//            SendMessage request = new SendMessage(chatId, text);
//            // sync
//            SendResponse sendResponse = bot.execute(request);
//        }

    }

    public void sendPhoto() throws IOException {
        long chatId = Constants.FIVE88_FAIL_ROOM_ID;

        String path1 = "/Users/jimmyvuong/Desktop/asdb.png";
        String path2 = "/Users/jimmyvuong/Desktop/burp.cer";

//        byte[] fileContent1 = FileUtils.readFileToByteArray(new File(path1));
//        SendPhoto request1 = new SendPhoto(chatId, fileContent1);
//        SendResponse sendResponse1 = bot.execute(request1);

        byte[] fileContent2 = FileUtils.readFileToByteArray(new File(path2));
        SendDocument request2 =  new SendDocument(chatId, fileContent2);
        SendResponse sendResponse2 = bot.execute(request2);


    }



}
