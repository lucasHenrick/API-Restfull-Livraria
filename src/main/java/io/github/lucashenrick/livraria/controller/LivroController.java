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

import io.github.lucashenrick.livraria.entity.Livro;
import io.github.lucashenrick.livraria.repository.LivroRepository;

@CrossOrigin(origins = "https://livraria-emprestimo.firebaseapp.com")
@RestController
public class LivroController {
	@Autowired
	private LivroRepository livroRepository;
	
	 @RequestMapping(value = "/livro", method = RequestMethod.GET)
	    public List<Livro> Get() {
	        return livroRepository.findAll();
	    }
	 @RequestMapping(value = "/livro/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Livro> GetById(@PathVariable(value = "id") long id)
	    {
	        Optional<Livro> pessoa = livroRepository.findById(id);
	        if(pessoa.isPresent())
	            return new ResponseEntity<Livro>(pessoa.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 @RequestMapping(value = "/livro", method =  RequestMethod.POST)
	    public Livro Post( @RequestBody Livro livro)
	    {
	        return livroRepository.save(livro);
	    }
	 @RequestMapping(value = "/livro/{id}", method =  RequestMethod.PUT)
	    public ResponseEntity<Livro> Put(@PathVariable(value = "id") long id,@RequestBody Livro atualizacao)
	    {
	        Optional<Livro> anterior = livroRepository.findById(id);
	        if(anterior.isPresent()){
	            Livro livro = anterior.get();
	            livro.setNome(atualizacao.getNome());
	            livro.setValor(atualizacao.getValor());
	            livroRepository.save(livro);
	            return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	
	 @RequestMapping(value = "/livro/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	    {
	        Optional<Livro> pessoa = livroRepository.findById(id);
	        if(pessoa.isPresent()){
	            livroRepository.delete(pessoa.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
}
