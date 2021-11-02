package com.github.jeffersonklamas.cidadesapi.countries;


import com.github.jeffersonklamas.cidadesapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/countries")
public class CountryResource {

//  Após criar a variável local, criar uma interface Alt+Enter -CreaterInterface
//  Para acessar o repositório incluir o @Autowired ou criar um constructor para o CountryRepository
//  Assim irá injetar o autowored melhor pratica.

    private CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

//  Buscando todos os paises, criando a variável local como find
    @GetMapping
    public Page<Country> countries(Pageable page){
        return repository.findAll(page);
    }

//  Buscando um único país.
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Long id){
        Optional<Country> optional = repository.findById(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
