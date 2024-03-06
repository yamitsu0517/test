package com.example.demo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Repository
public class EventDao implements BaseDao<Event> {
    private static Log log = LogFactory.getLog (EventDao.class);
    
    @Autowired
    EventRepository repository;
    
    @Override
    public List<Event> findAll () {
        return repository.findAll ();
    }
    
    @Override
    public Event findById (Integer id) throws DataNotFoundException {
        return repository.findById (id).orElseThrow ( () -> new DataNotFoundException ());
    }
    
    @Override
    public void save (Event event) {
        this.repository.save (event);
    }
    
    @Override
    public void deleteById (Integer id) {
        try {
            Event event = this.findById (id);
            this.repository.deleteById (event.getId ());
        } catch (DataNotFoundException e) {
            log.error ("no data");
        }
    }
}
