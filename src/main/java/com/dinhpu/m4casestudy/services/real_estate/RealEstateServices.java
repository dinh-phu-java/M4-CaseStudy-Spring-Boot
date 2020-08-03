package com.dinhpu.m4casestudy.services.real_estate;

import com.dinhpu.m4casestudy.dao.real_estate.RealEstateDAO;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateServices implements IRealEstateServices{
    @Autowired
    private RealEstateDAO realEstateDAO;

    @Override
    public Page<RealEstate> findAll(int pageNo,int pageSize,Optional<String> sortBy) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize, Sort.Direction.DESC,sortBy.orElse("id"));
        return realEstateDAO.findAll(pageable);
    }

    @Override
    public RealEstate findById(Long id) {
        Optional<RealEstate> optional=realEstateDAO.findById(id);
        RealEstate realEstate=null;
        if (optional.isPresent()){
            realEstate=optional.get();
        }
        return realEstate;
    }

    @Override
    public RealEstate save(RealEstate model) {
        return realEstateDAO.save(model);
    }

    @Override
    public RealEstate remove(Long id) {
        RealEstate realEstate=findById(id);

        if (realEstate!=null){
            realEstateDAO.deleteById(id);
        }
        //else nen throw ra loi
        return realEstate;
    }

    @Override
    public List<RealEstate> findAll() {
        return realEstateDAO.findAll();
    }

    @Override
    public Page<RealEstate> findAllRealEstateByUserId(int id, Pageable pageable) {
        return realEstateDAO.findAllRealEstateByUserId(id,pageable);
    }

    @Override
    public List<RealEstate> findAllByUser(User loginUser) {
        return realEstateDAO.findAllByUser(loginUser);
    }

    @Override
    public List<RealEstate> findAllByAdvertise(boolean ad,Pageable pageable) {
        return realEstateDAO.findAllByAdvertise(ad,pageable);
    }

    @Override
    public List<RealEstate> findAllRecent() {
        Optional<String> sortBy=Optional.of("id");
        Pageable pageable=PageRequest.of(0,8, Sort.Direction.DESC,sortBy.orElse("id"));
        return realEstateDAO.findAllRecent(pageable);
    }

    @Override
    public Page<RealEstate> searchQuery(String province,String price,String realEstateType,String category,String direction, Pageable pageable) {
        return realEstateDAO.searchQuery(province,price,realEstateType,category,direction,pageable);
    }

    @Override
    public Page<RealEstate> findAllByAdvertise(Pageable pageable) {
        return realEstateDAO.findAllByAdvertise(pageable);
    }

    @Override
    @Transactional
    public void removeAdvertise(Long id) {
        realEstateDAO.removeAdvertise(id);
    }

    @Override
    @Transactional
    public void updateAdvertise(Long id) {
        realEstateDAO.updateAdvertise(id);
    }

    @Override
    public Page<RealEstate> findAllBySelectUser(int id,Pageable pageable) {
        return realEstateDAO.findAllBySelectUser(id,pageable);
    }


}
