package br.com.baozistore.controller;

import br.com.baozistore.model.Pedido;
import br.com.baozistore.repository.PedidoRepository;
import br.com.baozistore.repository.ClienteRepository;
import br.com.baozistore.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository repository;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoController(PedidoRepository repository, ClienteRepository clientes, ProdutoRepository produtos) {
        this.repository = repository; this.clientes = clientes; this.produtos = produtos;
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@Valid @RequestBody Pedido pedido) {
        if (!clientes.existsById(pedido.getClienteId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente informado não existe");
        if (!produtos.existsById(pedido.getProdutoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto informado não existe");
        return repository.save(pedido);
    }
    @GetMapping public List<Pedido> listar() { return repository.findAll(); }
    @GetMapping("/{id}") public Pedido buscar(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }
    @PutMapping("/{id}") public Pedido atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dados) {
        Pedido atual = buscar(id);
        if (!clientes.existsById(dados.getClienteId()) || !produtos.existsById(dados.getProdutoId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente ou produto informado não existe");
        atual.setClienteId(dados.getClienteId()); atual.setProdutoId(dados.getProdutoId()); atual.setQuantidade(dados.getQuantidade());
        return repository.save(atual);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { repository.delete(buscar(id)); }
}
