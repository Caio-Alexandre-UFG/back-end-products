package br.ufg.inf.espec.backend.aula_spring;

import br.ufg.inf.espec.backend.aula_spring.model.Produto;
import br.ufg.inf.espec.backend.aula_spring.repository.ProdutoRepository;
import br.ufg.inf.espec.backend.aula_spring.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository; // Simula o comportamento do repositório de produtos.

    @InjectMocks
    private ProdutoService service; // Injeta o mock do repositório na camada de serviço.

    @BeforeEach
    public void setup() {
        // Inicializa os mocks e injeta dependências antes de cada teste.
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduto() {
        // Dados de entrada simulados para o teste.
        String nome = "Teste";
        double preco = 100.0;

        // Configura o comportamento do mock: simula o salvamento do produto.
        when(produtoRepository.save(any(Produto.class))).thenAnswer(invocation -> {
            Produto produto = invocation.getArgument(0);
            produto.setId(1L); // Simula a geração de ID pelo banco.
            return produto;
        });

        // Cria um novo produto e chama o método a ser testado.
        Produto produto = new Produto(null, nome, preco);
        Produto produtoSalvo = service.salvarProduto(produto);

        // Verifica se o produto foi salvo corretamente.
        assertNotNull(produtoSalvo, "O produto salvo não deve ser nulo.");
        assertNotNull(produtoSalvo.getId(), "O ID do produto salvo não deve ser nulo.");
        assertEquals(nome, produtoSalvo.getNome(), "O nome do produto salvo deve ser o mesmo que o informado.");
        assertEquals(preco, produtoSalvo.getPreco(), "O preço do produto salvo deve ser o mesmo que o informado.");
    }
}
