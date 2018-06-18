package com.unifil.br.lab01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unifil.br.lab01.entity.Filme;
import com.unifil.br.lab01.repository.FilmeRepository;

@RestController
public class FilmeController {
	
	@Autowired
	FilmeRepository filmeRepository;
	
	public FilmeController(FilmeRepository filme) {
		this.filmeRepository = filme;
	}
	
	@RequestMapping(value="/pessoaDb", method = RequestMethod.GET)
    public List<Filme> lista() {       
        return this.filmeRepository.findAll(); 
    }
	
	@RequestMapping(value="/pessoaDb/{id}", method = RequestMethod.GET)
    public Filme listaUm(@PathVariable long id) {       
        return this.filmeRepository.findOne(id); 
    }
	
	@PostMapping("/pessoaDb")
    public Filme create(@ModelAttribute("filme") Filme filme) {
        this.filmeRepository.save(filme);
        return filme;
        //return "redirect:/pessoaDb";
    }
	
	@PutMapping("/pessoaDb/{id}")
	public Filme update(@PathVariable long id, String name) {
		Filme temp = this.filmeRepository.findOne(id);
		if (temp != null) { 
			temp.setTitulo(name);
			this.filmeRepository.save(temp);
		}else {
			System.out.println("Filme não existe");
		}
		return temp;
	}
	
	@DeleteMapping("/pessoaDb/{id}")
	public Filme delete(@PathVariable long id) {
		this.filmeRepository.delete(id);
		return this.filmeRepository.findOne(id);
	}
		
}

