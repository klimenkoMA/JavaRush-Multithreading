package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementManager {
	private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
	private int timeSeconds;
	//recursion
	private long maxAmount;
	private List<Advertisement> optimalVideoSet;
	private int totalTimeSecondsLeft;

	public AdvertisementManager(int timeSeconds) {
		this.timeSeconds = timeSeconds;
	}

	public void processVideos() {
		this.totalTimeSecondsLeft = Integer.MAX_VALUE;
		obtainOptimalVideoSet(new ArrayList<>(), timeSeconds, 0L);

		displayAdvertisement();
	}

	private void obtainOptimalVideoSet(List<Advertisement> totalList, int currentTimeSecondsLeft, long currentAmount) {
		if(currentTimeSecondsLeft < 0) {
			return;
		} else if(currentAmount > maxAmount
				|| currentAmount == maxAmount && (totalTimeSecondsLeft > currentTimeSecondsLeft
				|| totalTimeSecondsLeft == currentTimeSecondsLeft && totalList.size() < optimalVideoSet.size())) {
			this.totalTimeSecondsLeft = currentTimeSecondsLeft;
			this.optimalVideoSet = totalList;
			this.maxAmount = currentAmount;
			if(currentTimeSecondsLeft == 0) {
				return;
			}
		}

		ArrayList<Advertisement> tmp = getActualAdvertisements();
		tmp.removeAll(totalList);
		for(Advertisement ad : tmp) {
			if(!ad.isActive())
				continue;
			ArrayList<Advertisement> currentList = new ArrayList<>(totalList);
			currentList.add(ad);
			obtainOptimalVideoSet(currentList, currentTimeSecondsLeft - ad.getDuration(),
					currentAmount + ad.getAmountPerOneDisplaying());
		}
	}

	private ArrayList<Advertisement> getActualAdvertisements() {
		ArrayList<Advertisement> advertisements = new ArrayList<>();
		for(Advertisement ad : storage.list()) {
			if(ad.isActive()) {
				advertisements.add(ad);
			}
		}
		return advertisements;
	}

	private void displayAdvertisement() {

		StatisticManager statisticManager = StatisticManager.getInstance();
		if(optimalVideoSet == null || optimalVideoSet.isEmpty()) {
			statisticManager.register(new NoAvailableVideoEventDataRow(timeSeconds));
			throw new NoVideoAvailableException();
		}

		optimalVideoSet.sort((o1, o2) -> {
			long l = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
			return (int) (l != 0 ? l : o2.getDuration() - o1.getDuration());
		});


		statisticManager.register(new VideoSelectedEventDataRow(optimalVideoSet, maxAmount
        , timeSeconds));

		for(Advertisement ad : optimalVideoSet) {
			displayInPlayer(ad);
			ad.revalidate();
		}
	}

	private void displayInPlayer(Advertisement advertisement) {
		System.out.println(advertisement.getName() + " is displaying... " + advertisement.getAmountPerOneDisplaying() +
				", " + (1000 * advertisement.getAmountPerOneDisplaying() / advertisement.getDuration()));
	}
}
