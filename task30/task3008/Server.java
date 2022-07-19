package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

	private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

	public static void sendBroadcastMessage(Message message) {
		for(Map.Entry<String, Connection> pair : connectionMap.entrySet()
		) {
			String user = pair.getKey();
			Connection connection = pair.getValue();
			try {
				connection.send(message);
			} catch(IOException e) {
				System.out.println("Сообщение не отправлено, ошибка: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws IOException {
		ConsoleHelper.writeMessage("Введите порт сервера: ");
		try(ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
			ConsoleHelper.writeMessage("Сервер запущен");
			while(true) {
				Handler handler = new Handler(serverSocket.accept());
				handler.start();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static class Handler extends Thread {
		private Socket socket;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
			while(true) {
				connection.send(new Message(MessageType.NAME_REQUEST));
				Message message = connection.receive();
				if(Objects.equals(message.getData(), "") || message.getType() != MessageType.USER_NAME ||
						connectionMap.containsKey(message.getData())) {
					continue;
				}
				connectionMap.put(message.getData(), connection);
				connection.send(new Message(MessageType.NAME_ACCEPTED));
				return message.getData();
			}
		}

		private void notifyUsers(Connection connection, String userName) throws IOException {
			for(Map.Entry<String, Connection> pair : connectionMap.entrySet()
			) {
				String name = pair.getKey();
				if(!userName.equals(name)) {
					connection.send(new Message(MessageType.USER_ADDED, name));
				}
			}
		}

		private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
			while(true) {
				Message message = connection.receive();
				if(message.getType() == MessageType.TEXT) {
					sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " +
							message.getData()));
				} else {
					ConsoleHelper.writeMessage("Something wrong");
				}
			}
		}

		@Override
		public void run() {
			ConsoleHelper.writeMessage("Connection done at address: " + socket.getRemoteSocketAddress());

			try(Connection connection = new Connection(socket)) {
				String userName = serverHandshake(connection);
				sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
				notifyUsers(connection, userName);
				serverMainLoop(connection, userName);
				if(userName != null) {
					connectionMap.remove(userName);
					sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
				}
				ConsoleHelper.writeMessage("Remote connection CLOSE!");
			} catch(IOException | ClassNotFoundException e) {
				ConsoleHelper.writeMessage("Произошла ошибка при обмене данными" +
						"с удаленным сервером");
			}
		}
	}
}
