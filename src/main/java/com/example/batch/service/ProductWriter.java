package com.example.batch.service;

import com.example.batch.mapper.ProductMapper;
import com.example.batch.model.Product;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

public class ProductWriter implements ItemWriter<Product>, StepExecutionListener {


    @Resource
    ProductMapper productMapper;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

    @Override
    public void write(List<? extends Product> list) throws Exception {
        for (Product product : list) {
            productMapper.insert(product);
        }
    }
}
