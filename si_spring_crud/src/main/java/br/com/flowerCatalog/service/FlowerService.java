package br.com.flowerCatalog.service;

import br.com.flowerCatalog.repository.FlowerRepository;
import br.com.flowerCatalog.model.FlowerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerService {

    @Autowired
    private FlowerRepository repository;

    public Optional<FlowerModel> findById(long id){
        return repository.findById(id);
    }

    public Page<FlowerModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public FlowerModel save(FlowerModel model){
        return repository.save(model);
    }

    public FlowerModel update(FlowerModel model){
        Optional<FlowerModel> f = repository.findById(model.getId());
        if(f.isPresent()){
            f.get().setName(model.getName());
            f.get().setScientificName(model.getScientificName());
            f.get().setColor(model.getColor());
            //---------------------------------------
            return repository.save(f.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        Optional<FlowerModel> f = repository.findById(id);
        if(f.isPresent()){
            repository.delete(f.get());
        }
    }

    public List<FlowerModel> findByName(String name){
        return repository.findByNameContainsIgnoreCaseOrderByName(name);
    }

}
