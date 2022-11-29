package br.com.flowerCatalog.service;

import br.com.flowerCatalog.model.FamilyModel;
import br.com.flowerCatalog.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository repository;

    public Optional<FamilyModel> findById(long id){
        return repository.findById(id);
    }

    public List<FamilyModel> findAll(){
        return repository.findAll();
    }

    public List<FamilyModel> findByName(String name){
        return repository.findByFamilyNameContainsIgnoreCase(name);
    }

    public FamilyModel save(FamilyModel model){
        return repository.save(model);
    }

    public FamilyModel update(FamilyModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setFamilyName(model.getFamilyName());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }


}
