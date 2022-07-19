package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {

	protected Connection connection;
	private volatile boolean clientConnected = false;

	public static void main(String[] args) throws IOException {
		new Client().run();
	}

	protected String getServerAddress() throws IOException {
		ConsoleHelper.writeMessage("Пожалуйста введите адрес сервера: ");
		return ConsoleHelper.readString();
	}

	protected int getServerPort() throws IOException {
		ConsoleHelper.writeMessage("Введите пожалуйста порт сервера: ");
		return ConsoleHelper.readInt();
	}

	protected String getUserName() throws IOException {
		ConsoleHelper.writeMessage("Введите пожалуйста имя пользователя: ");
		return ConsoleHelper.readString();
	}

	protected boolean shouldSendTextFromConsole() {
		return true;
	}

	protected SocketThread getSocketThread() {
		return new SocketThread();
	}

	protected void sendTextMessage(String text) {
		try {
			Message message = new Message(MessageType.TEXT, text);
			connection.send(message);
		} catch(IOException e) {
			ConsoleHelper.writeMessage("Что-то пошло не так... " + e.getMessage());
			clientConnected = false;
		}
	}

	public void run() throws IOException {
		SocketThread socketThread = getSocketThread();
		socketThread.setDaemon(true);
		socketThread.start();
		synchronized(this) {
			try {
				wait();
			} catch(InterruptedException e) {
				ConsoleHelper.writeMessage("Проблема с основным потоком, завершаю работу");
				try {
					connection.close();
				} catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		if(clientConnected) {
			ConsoleHelper.writeMessage("Соединение установлено.\nДля выхода наберите" +
					"команду 'exit'.");
		} else {
			ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
		}
		String s = ConsoleHelper.readString();
		while(clientConnected) {
			if(!s.equals("exit")) {
				if(shouldSendTextFromConsole()) {
					sendTextMessage(s);
				}
				s = ConsoleHelper.readString();
			} else {
				clientConnected = false;
				break;
			}
		}
	}

	public class SocketThread extends Thread {

		@Override
		public void run() {
			try {
				String address = getServerAddress();
				int port = getServerPort();
				Client.this.connection = new Connection(new Socket(address, port));
				clientHandshake();
				clientMainLoop();
			} catch(IOException | ClassNotFoundException e) {
				notifyConnectionStatusChanged(false);
			}
		}

		protected void clientMainLoop() throws IOException, ClassNotFoundException {
			while(true) {
				Message message = Client.this.connection.receive();
				String data = message.getData();
				if(message.getType() == null) {
					throw new IOException("Unexpected MessageType");
				}
				switch(message.getType()) {
					case TEXT:
						processIncomingMessage(data);
						break;
					case USER_ADDED:
						informAboutAddingNewUser(data);
						break;
					case USER_REMOVED:
						informAboutDeletingNewUser(data);
						break;
					default:
						throw new IOException("Unexpected MessageType");
				}
			}
		}

		protected void clientHandshake() throws IOException, ClassNotFoundException {

			while(!clientConnected) {
				Message message = Client.this.connection.receive();
				if(message.getType() == null) {
					throw new IOException("Unexpected MessageType");
				}
				switch(message.getType()) {
					case NAME_REQUEST:
						Client.this.connection.send(new Message(MessageType.USER_NAME, getUserName()));
						break;
					case NAME_ACCEPTED:
						notifyConnectionStatusChanged(true);
						return;
					default:
						throw new IOException("Unexpected MessageType");
				}
			}
		}

		protected void processIncomingMessage(String message) {
			ConsoleHelper.writeMessage(message);
		}

		protected void informAboutAddingNewUser(String userName) {
			ConsoleHelper.writeMessage(userName + " присоединился к чату");
		}

		protected void informAboutDeletingNewUser(String userName) {
			ConsoleHelper.writeMessage(userName + " покинул чат");
		}

		protected void notifyConnectionStatusChanged(boolean clientConnected) {
			Client.this.clientConnected = clientConnected;
			synchronized(Client.this) {
				Client.this.notify();
			}
		}

	}
}
