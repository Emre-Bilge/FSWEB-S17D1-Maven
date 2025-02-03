package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

private Map<Integer, Animal> animals = new HashMap<>();

/*
@Value("{course.name}")
    private String courseName;

@Value("{project.developer.fullname}")
    private String projectDeveloperFullName;
*/

@GetMapping()
    public List<Animal> findAnimal(){
    return animals.values().stream().toList();
}

@GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id){
   if(animals.containsKey(id)){
       return animals.get(id);
   }
   else {
       throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
   }
}

    @PostMapping()
    public String save(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);  // id'yi anahtar olarak kullanıp, Animal nesnesini değer olarak ekliyoruz
        return "Animal added: " + animal.getName();
    }

@PutMapping("/{id}")
    public String update(@PathVariable int id ,@RequestBody Animal animal ){
if(animals.containsKey(id)){
    animals.put(id,animal);
   return "animal eklendi " + id ;
}
return "animal bulunamadı" ;
}

@DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id){
Animal animal = animals.get(id) ;
animals.remove(animal);
return animal ;
}

}
