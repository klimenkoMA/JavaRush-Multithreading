package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

public class FakeModel implements Model{

	private ModelData modelData = new ModelData();
	@Override
	public ModelData getModelData() {
		return modelData;
	}

	@Override
	public void loadUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User("Vaska", 15, 1));
		list.add(new User("Kol", 3, 1));
		list.add(new User("BLM", 4, 1));
		list.add(new User("Trend", 151, 2));
		list.add(new User("Laska", 105, 10));
		modelData.setUsers(list);
	}

	@Override
	public void loadDeletedUsers() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void loadUserById(long id) {
		throw  new UnsupportedOperationException();
	}

	@Override
	public void deleteUserById(long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void changeUserData(String name, long id, int level) {
		throw  new UnsupportedOperationException();
	}
}
