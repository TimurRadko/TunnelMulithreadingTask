package com.epam.multithreading.tunnel.data;

import com.epam.multithreading.tunnel.exception.TrainCreationException;
import com.epam.multithreading.tunnel.logic.Train;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JsonTrainCreatorTest {
    private static final String FILE_NAME = "src/test/resources/trains-test-valid.json";
    private static final String INVALID_FILE_NAME = "src/resources/trains-test-valid.json";
    private static final String INVALID_JSON = "src/resources/trains-test-invalid.json";
    private static final Train FIRST_TRAIN = new Train(1, false);

    @Test
    public void testCreateTrainsShouldCreateTrainsWhenFileExists() throws TrainCreationException {
        JsonTrainCreator creator = new JsonTrainCreator();
        List<Train> actualTrain = creator.createTrains(FILE_NAME);
        Assert.assertEquals(FIRST_TRAIN, actualTrain.get(0));
    }

    @Test(expected = TrainCreationException.class)
    public void testCreateTrainsShouldThrowTrainCreationExceptionWhenFileNotExists() throws TrainCreationException {
        JsonTrainCreator creator = new JsonTrainCreator();
        creator.createTrains(INVALID_FILE_NAME);
    }

    @Test(expected = TrainCreationException.class)
    public void testCreateTrainsShouldThrowTrainCreationExceptionWhenJsonInvalid() throws TrainCreationException {
        JsonTrainCreator creator = new JsonTrainCreator();
        creator.createTrains(INVALID_JSON);
    }
}
