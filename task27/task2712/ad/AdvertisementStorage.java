package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {

	private static AdvertisementStorage instance;
	private final List<Advertisement> videos = new ArrayList<>();

	public List<Advertisement> list(){
		return videos;
	}

	public void add(Advertisement advertisement){
		videos.add(advertisement);
	}

	private AdvertisementStorage() {
		Object someContent = new Object();
		add(new Advertisement(someContent, "First Video", 5000, 100, (3 * 60))); // 50
		add(new Advertisement(someContent, "Second Video", 100, 10, (15 * 60))); // 10
		add(new Advertisement(someContent, "Third Video", 400, 2, (10 * 60))); // 20
		add(new Advertisement(someContent, "Четвертое Video", 300, 1, (10 * 60))); //267
		add(new Advertisement(someContent, "Апять какое-то Video", 2000, 5, (25 * 60))); //400
		add(new Advertisement(someContent, "Шестое Video", 2000, 1, (15 * 60))); //400
	}

	public static AdvertisementStorage getInstance(){
		if(instance == null){
			instance = new AdvertisementStorage();
		}
		return instance;
	}
}
