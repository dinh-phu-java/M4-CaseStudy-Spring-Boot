package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.DirectionDAO;
import com.dinhpu.m4casestudy.model.real_estate.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectionServices implements IDirectionServices{
    @Autowired
    private DirectionDAO directionDAO;

    @Override
    public List<Direction> findAll() {
        return directionDAO.findAll();
    }

    @Override
    public Direction findById(Long id) {
        Optional<Direction> optional=directionDAO.findById(id);
        Direction direction= null;
        if (optional.isPresent()){
            direction=optional.get();
        }
        return direction;
    }

    @Override
    public Direction save(Direction model) {
        return null;
    }

    @Override
    public Direction remove(Long id) {
        return null;
    }
}
