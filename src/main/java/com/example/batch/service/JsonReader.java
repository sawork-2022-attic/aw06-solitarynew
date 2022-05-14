package com.example.batch.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

public class JsonReader implements StepExecutionListener, ItemReader<JsonNode> {

    private final String FILE_NAME = "data/meta_All_Beauty.json";
//    private final String FILE_NAME = "data/meta_Appliances.json";

    private BufferedReader reader;

    private ObjectMapper objectMapper;

    private void initReader() throws FileNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource(FILE_NAME).getFile());
        reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        objectMapper = new ObjectMapper();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        return null;
    }

    @Override
    public JsonNode read() throws Exception {
        if (reader == null) {
            initReader();
        }
        String line = reader.readLine();
        return line != null ? objectMapper.readTree(line) : null;
    }
}
