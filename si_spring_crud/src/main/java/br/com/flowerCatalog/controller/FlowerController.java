package br.com.flowerCatalog.controller;

import br.com.flowerCatalog.service.FlowerService;
import br.com.flowerCatalog.model.FlowerModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
@Api(value = "Flower Controller")
public class FlowerController {

    @Autowired
    private FlowerService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
    @ApiOperation(value = "Get a Person by id", produces = "JSON", response = FlowerModel.class)
    public FlowerModel findById(
            @ApiParam(name = "id", required = true, type = "Integer")
            @PathVariable("id") long id)
    {
        var model = service.findById(id);
        if(model.isPresent()){
            buildEntityLinks(model.get());
        }
        return model.get();
    }

    @PostMapping(produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" },
    consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE, "application/x-yaml" })
    public FlowerModel save(@RequestBody FlowerModel model){
        return service.save(model);
    }

    @GetMapping
    public ResponseEntity<PagedModel<FlowerModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<FlowerModel> assembler
    ){
        //..the direction of sort
        var sortDirection =
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        //..a PageAble object is an object containing the list of resources
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        //..a Page containing the resource models
        Page<FlowerModel> pageModel= service.findAll(pageable);

        //..create the HATEOAS links
        for(FlowerModel flower : pageModel){
            buildEntityLinks(flower);
        }
        //return the ResponseEntity
        return new ResponseEntity(assembler.toModel(pageModel), HttpStatus.OK);

    }

    @PutMapping
    public FlowerModel update(@RequestBody FlowerModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/name/{name}")
    public List<FlowerModel> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    //HATEOAS implementation
    public void buildEntityLinks(FlowerModel model){
        //..link to self
        model.add(
               WebMvcLinkBuilder.linkTo( //..link to a method...
                       WebMvcLinkBuilder.methodOn(this.getClass())
                               .findById(model.getId())
               ).withSelfRel()
        );

        Link link = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(FamilyController.class)
                        .findById(model.getFamily().getId())
        ).withSelfRel();
        model.getFamily().add(link);
    }

}
