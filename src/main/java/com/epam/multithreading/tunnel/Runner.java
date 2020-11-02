package com.epam.multithreading.tunnel;

import com.epam.multithreading.tunnel.data.JsonTrainCreator;
import com.epam.multithreading.tunnel.data.TrainCreator;
import com.epam.multithreading.tunnel.exception.TrainCreationException;
import com.epam.multithreading.tunnel.logic.Train;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final String FILE_NAME = "data/trains.json";

    public static void main(String[] args) throws TrainCreationException {
        TrainCreator creator = new JsonTrainCreator();
        List<Train> trains = creator.createTrains(FILE_NAME);
        int size = trains.size();
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        trains.forEach(executorService::submit);
        executorService.shutdown();
    }
}
