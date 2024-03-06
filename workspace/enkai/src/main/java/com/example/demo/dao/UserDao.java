package com.example.demo.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Repository
public class UserDao implements BaseDao<User> {
    private static Log log = LogFactory.getLog (UserDao.class);
    
    @Autowired
    UserRepository repository;
    
    @Override
    public List<User> findAll () {
        return repository.findAll ();
    }
    
    @Override
    public User findById (Integer id) throws DataNotFoundException {
        return this.repository.findById (id).orElseThrow ( () -> new DataNotFoundException ());
    }
    
    @Override
    public void save (User user) {
        this.repository.save (user);
    }
    
    @Override
    public void deleteById (Integer id) {
        try {
            User user = this.findById (id);
            this.repository.deleteById (user.getId ());
        } catch (DataNotFoundException e) {
            log.error ("no data");
        }
    }
    
    public User findByEmail (String email) throws DataNotFoundException {
        User user = this.repository.findByEmail (email);
        if (user == null) {
            log.error ("ログインユーザの情報がみつかりません。");
            throw new DataNotFoundException ();
        }
        return user;
    }
}
