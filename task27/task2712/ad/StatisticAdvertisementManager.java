package com.javarush.task.task27.task2712.ad;

import java.util.List;

public class StatisticAdvertisementManager {

	private static StatisticAdvertisementManager instance;
	private AdvertisementStorage storage = AdvertisementStorage.getInstance();


	private StatisticAdvertisementManager() {
	}

	private static class StatisticAdvertisementManagerInstance{

		private static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
	}

	public static StatisticAdvertisementManager getInstance(){
		if(instance == null){
			instance = StatisticAdvertisementManagerInstance.instance;
		}
		return instance;
	}

	public List<Advertisement> getVideos(){
		return storage.list();
	}

}
