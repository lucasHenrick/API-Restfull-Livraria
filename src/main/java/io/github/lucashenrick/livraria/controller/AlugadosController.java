package io.github.lucashenrick.livraria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucashenrick.livraria.entity.Alugados;
import io.github.lucashenrick.livraria.repository.AlugadosRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AlugadosController {
	@Autowired
	private AlugadosRepository alugadosRepository;
	 @RequestMapping(value = "/alugados", method = RequestMethod.GET)
	    public List<Alugados> Get() {
	        return alugadosRepository.findAll();
	    }
	 @RequestMapping(value = "/alugados/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Alugados> GetById(@PathVariable(value = "id") long id)
	    {
	        Optional<Alugados> pessoa = alugadosRepository.findById(id);
	        if(pessoa.isPresent())
	            return new ResponseEntity<Alugados>(pessoa.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 @RequestMapping(value = "/alugados", method =  RequestMethod.POST)
	    public Alugados Post( @RequestBody Alugados cliente)
	    {
	        return alugadosRepository.save(cliente);
	    }
	 @RequestMapping(value = "/alugados/{id}", method =  RequestMethod.PUT)
	    public ResponseEntity<Alugados> Put(@PathVariable(value = "id") long id,@RequestBody Alugados atualizacao)
	    {
	        Optional<Alugados> anterior = alugadosRepository.findById(id);
	        if(anterior.isPresent()){
	        	Alugados cliente = anterior.get();
	            cliente.setNomeCliente(atualizacao.getNomeCliente());
	            cliente.setNomeLivro(atualizacao.getNomeLivro());
	            cliente.setValorLivro(atualizacao.getValorLivro());
	            alugadosRepository.save(cliente);
	            return new ResponseEntity<Alugados>(cliente, HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	
	 @RequestMapping(value = "/alugados/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	    {
	        Optional<Alugados> pessoa = alugadosRepository.findById(id);
	        if(pessoa.isPresent()){
	        	alugadosRepository.delete(pessoa.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
}
