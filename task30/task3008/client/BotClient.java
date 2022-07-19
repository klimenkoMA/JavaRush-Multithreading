package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client{

	@Override
	protected String getUserName() {
		return "date_bot_" + (int) (Math.random() * 100);
	}

	@Override
	protected boolean shouldSendTextFromConsole() {
		return false;
	}

	@Override
	protected SocketThread getSocketThread() {
		return new BotSocketThread();
	}

	public static void main(String[] args) throws IOException {
		new BotClient().run();
	}

	public class BotSocketThread extends SocketThread{

		@Override
		protected void clientMainLoop() throws IOException, ClassNotFoundException {
			sendTextMessage("Привет чатику. Я бот. Понимаю команды: " +
					"дата, день, месяц, год, время, час, минуты, секунды.");
			super.clientMainLoop();
		}

		@Override
		protected void processIncomingMessage(String message) {
			ConsoleHelper.writeMessage(message);
			String[] spl = message.split(": ");
			if(spl.length == 2) {
				String senderName = spl[0];
				String text = spl[1];
				SimpleDateFormat dateFormat;
				String result;
				switch(text){
					case "дата":
						dateFormat = new SimpleDateFormat("d.MM.yyyy");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "день":
						dateFormat = new SimpleDateFormat("d");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "месяц":
						dateFormat = new SimpleDateFormat("MMMM");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "год":
						dateFormat = new SimpleDateFormat("yyyy");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "время":
						dateFormat = new SimpleDateFormat("H:mm:ss");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "час":
						dateFormat = new SimpleDateFormat("H");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "минуты":
						dateFormat = new SimpleDateFormat("m");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
					case "секунды":
						dateFormat = new SimpleDateFormat("s");
						result = dateFormat.format(Calendar.getInstance().getTime());
						sendTextMessage(String.format("Информация для %s: %s", senderName, result));
						break;
				}


			}
		}
	}
}
