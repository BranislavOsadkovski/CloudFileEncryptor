package com.example.DAO;

import com.example.entities.Dummy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DummyDAO extends CrudRepository<Dummy,Integer> {

    public List<Dummy> findByName(String name);

    public Dummy findById(int id);


}
