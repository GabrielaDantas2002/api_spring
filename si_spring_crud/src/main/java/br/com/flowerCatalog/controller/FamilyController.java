package br.com.flowerCatalog.controller;

import br.com.flowerCatalog.model.FamilyModel;
import br.com.flowerCatalog.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyController {

    @Autowired
    private FamilyService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public FamilyModel findById(@PathVariable("id") long id){
        return service.findById(id).get();
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<FamilyModel> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/find/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<FamilyModel> findByName(@PathVariable("familyName") String familyName){
        return service.findByName(familyName);
    }

    @PostMapping
    public FamilyModel save(@RequestBody FamilyModel model){
        return service.save(model);
    }

    @PutMapping
    public FamilyModel update(@RequestBody FamilyModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
