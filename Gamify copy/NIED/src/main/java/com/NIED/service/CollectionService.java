package com.NIED.service;

import com.NIED.model.Collection;
import com.NIED.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    public Optional<Collection> getCollectionByDate(Date date) {
        return collectionRepository.findByDate(date);
    }
}