package com.unifil.br.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unifil.br.lab01.entity.Genero;
import com.unifil.br.lab01.entity.Dropdown;
import com.unifil.br.lab01.entity.Filme;
import com.unifil.br.lab01.repository.GeneroRepository;
import com.unifil.br.lab01.repository.FilmeRepository;



@Controller
public class SiteController {
	
	
	@Autowired
	FilmeRepository filmeRepository;
	
	@Autowired
	GeneroRepository genRepository;
	
	public SiteController(FilmeRepository filmeRepository, GeneroRepository genRepository) {
		this.genRepository = genRepository;
		this.filmeRepository = filmeRepository;
	}
	
	
	@PostMapping("/filme")
	public String create(@ModelAttribute("filme") Filme filme, String genero) {
		Genero gen1 = new Genero();
		gen1.setId(Long.parseLong(genero));
		
		filme.setGenero(gen1);
		System.out.println(filme.getAno() + filme.getTitulo());
		this.filmeRepository.save(filme);
		return "redirect:/filme/lista";
	}
	
	@GetMapping("/filme/new")
	public String add(Map<String, Object> model) {
		Filme f = new Filme();
		List<Genero> lista = new ArrayList<Genero>();
		lista = this.genRepository.findAll();	
		
		model.put("filme", f);
		model.put("generos", lista);
		System.out.println(lista.size());
		return "filme/new";
	}
	
	@GetMapping("/filme/lista")
	public String listafilme(Map<String, Object> model) {
		model.put("filmes", this.filmeRepository.findAll());
		return "filme/index";
	}
	
	@RequestMapping(value="/filme/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Map<String, Object> model) {
		List<Dropdown> dd = new ArrayList<Dropdown>();
		
		String _selected = "";
		
		Filme f = new Filme();
		f = this.filmeRepository.findOne(id);
		
		List<Genero> lista = new ArrayList<Genero>();
		lista = this.genRepository.findAll();	
		for(ListIterator<Genero> iter = lista.listIterator(); iter.hasNext();) {
			Genero element = iter.next();
			_selected = "";
			if(element.getId() == f.getGenero().id) {
				_selected = "selected";
			}
			dd.add(new Dropdown(element.getId(), element.getName(), _selected));
			
		}
        
        model.put("filme", this.filmeRepository.findOne(id));
        model.put("generos", dd);
        return "filme/edit";
    }
	
	@PutMapping("/filme/{id}")
	public String update(@PathVariable long id, @ModelAttribute("filme") Filme filme, String genero) {	
		Filme temp = this.filmeRepository.findOne(id);
		Genero gen1 = new Genero();
		gen1.setId(Long.parseLong(genero));
		
		if (temp != null) { 
			temp.setTitulo(filme.getTitulo());
			temp.setGenero(gen1);
			this.filmeRepository.save(temp);
		}else {
			System.out.println("Filme não existe");
		}
		return "redirect:/filme/lista";
	}
	
	@DeleteMapping("/filme/{id}")
	public String delete(@PathVariable long id) {
		this.filmeRepository.delete(id);
		return "redirect:/filme/lista";
	}
}
