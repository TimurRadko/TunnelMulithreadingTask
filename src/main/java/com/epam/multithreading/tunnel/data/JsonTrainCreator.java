package com.epam.multithreading.tunnel.data;

import com.epam.multithreading.tunnel.exception.TrainCreationException;
import com.epam.multithreading.tunnel.logic.Train;
import com.epam.multithreading.tunnel.logic.Trains;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTrainCreator implements TrainCreator {

    @Override
    public List<Train> createTrains(String fileName) throws TrainCreationException {
        ObjectMapper mapper = new ObjectMapper();
        Trains trains;
        try {
            File file = new File(fileName);
            trains = mapper.readValue(file, Trains.class);
        } catch (IOException e) {
            throw new TrainCreationException(String.format("Creation of trains has ended with error: %s", e.getMessage()));
        }
        return getTrainsList(trains);
    }

    private List<Train> getTrainsList(Trains trains) {
        List<Train> trainList = new ArrayList<>();
        for (int i = 0; i < trains.getSize(); i++) {
            Train train = trains.getTrain(i);
            trainList.add(train);
        }
        return trainList;
    }
}
