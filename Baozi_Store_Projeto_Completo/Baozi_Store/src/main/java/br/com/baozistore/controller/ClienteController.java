package br.com.baozistore.controller;

import br.com.baozistore.model.Cliente;
import br.com.baozistore.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository repository;
    public ClienteController(ClienteRepository repository) { this.repository = repository; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody Cliente cliente) { return repository.save(cliente); }
    @GetMapping public List<Cliente> listar() { return repository.findAll(); }
    @GetMapping("/{id}") public Cliente buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
    @PutMapping("/{id}") public Cliente atualizar(@PathVariable Long id, @Valid @RequestBody Cliente dados) {
        Cliente atual = buscar(id); atual.setNome(dados.getNome()); atual.setClienteDesde(dados.getClienteDesde());
        return repository.save(atual);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { repository.delete(buscar(id)); }
}
