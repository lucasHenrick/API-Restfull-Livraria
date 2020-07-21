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
import io.github.lucashenrick.livraria.entity.Reserva;
import io.github.lucashenrick.livraria.repository.ReservaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ReservaController {
	@Autowired
	private ReservaRepository reservaRepository;
	
	 @RequestMapping(value = "/reserva", method = RequestMethod.GET)
	    public List<Reserva> Get() {
	        return reservaRepository.findAll();
	    }
	 @RequestMapping(value = "/reserva/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Reserva> GetById(@PathVariable(value = "id") long id)
	    {
	        Optional<Reserva> pessoa = reservaRepository.findById(id);
	        if(pessoa.isPresent())
	            return new ResponseEntity<Reserva>(pessoa.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 @RequestMapping(value = "/reserva", method =  RequestMethod.POST)
	    public Reserva Post( @RequestBody Reserva reserva)
	    {
	        return reservaRepository.save(reserva);
	    }
	 @RequestMapping(value = "/reserva/{id}", method =  RequestMethod.PUT)
	    public ResponseEntity<Reserva> Put(@PathVariable(value = "id") long id,@RequestBody Reserva atualizacao)
	    {
	        Optional<Reserva> anterior = reservaRepository.findById(id);
	        if(anterior.isPresent()){
	        	Reserva reserva = anterior.get();
	            reserva.setNomeLivro(atualizacao.getNomeLivro());
	            reserva.setNomeCliente(atualizacao.getNomeCliente());
	            reserva.setValorLivro(atualizacao.getValorLivro());
	            reserva.setData(atualizacao.getData());
	            reservaRepository.save(reserva);
	            return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	
	 @RequestMapping(value = "/reserva/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	    {
	        Optional<Reserva> pessoa = reservaRepository.findById(id);
	        if(pessoa.isPresent()){
	        	reservaRepository.delete(pessoa.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
}
