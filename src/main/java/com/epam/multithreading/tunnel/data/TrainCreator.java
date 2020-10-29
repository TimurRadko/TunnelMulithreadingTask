package com.epam.multithreading.tunnel.data;

import com.epam.multithreading.tunnel.exception.TrainCreationException;
import com.epam.multithreading.tunnel.logic.Train;

import java.util.List;

public interface TrainCreator {
    List<Train> createTrains(String fileName) throws TrainCreationException;
}
