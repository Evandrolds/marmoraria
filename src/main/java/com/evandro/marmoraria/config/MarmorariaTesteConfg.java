package com.evandro.marmoraria.config;

import com.evandro.marmoraria.entities.*;
import com.evandro.marmoraria.entities.enumeracao.StatusDoPedido;
import com.evandro.marmoraria.repositories.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Evandro
 */
@Configuration
@Profile("teste")
public class MarmorariaTesteConfg implements CommandLineRunner {

    @Autowired
    private ClienteRepositoty ClienteRepository;
    @Autowired
    private PedidoRepository PedidoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository ProdutoRepository;

    @Autowired
    private ItemDoPedidoRepository ItemDopedidoRepository;

    @Override
    public void run(String... args) throws Exception {

        LocalDateTime data1 = LocalDateTime.now();
        LocalDateTime data2 = LocalDateTime.now();
        LocalDateTime data3 = LocalDateTime.now();
        LocalDateTime data4 = LocalDateTime.now();

        Categoria cat1 = new Categoria(null, "Pedra");
        Categoria cat2 = new Categoria(null, "Ferramentas");
        Categoria cat3 = new Categoria(null, "Produtos");

        NFCs nota1 = new NFCs(null, "7897545", "Mamoraria", "45755.4544-96/0001", 2, 478.4);
        NFCs nota2 = new NFCs(null, "7897545", "Deposito", "45755.4544-96/0001", 3, 674.5);
        NFCs nota3 = new NFCs(null, "7897545", "Leroy Merlin", "45755.4544-96/0001", 2, 562.5);

        Produto prod1 = new Produto(null, "Ganito", "Preoto Sao Gabriel", 478.4);
        Produto prod2 = new Produto(null, "Marmore", "Branco Carrara", 674.5);
        Produto prod3 = new Produto(null, "Cola", "Msssa plastica", 15.5);
        Produto prod4 = new Produto(null, "espatula", "ferrsmenta", 26.7);
        Produto prod5 = new Produto(null, "Serra Marmore", "Makita", 345.5);
        Produto prod6 = new Produto(null, "cera", "Polimento", 12.5);
        
        
        ProdutoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4,prod5,prod6));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        
        prod1.getCategorias().add(cat1);
        prod2.getCategorias().add(cat1);
        prod3.getCategorias().add(cat3);
        prod4.getCategorias().add(cat2);
        prod5.getCategorias().add(cat2);
        prod6.getCategorias().add(cat3);
        ProdutoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4,prod5,prod6));
        
        
        Cliente c1 = new Cliente(null, "Maria", "321.654.987-12", "11 9 8754-6321", "maria@hotmail.com");
        Cliente c2 = new Cliente(null, "Jose", "365.654.321-45", "11 9 8784-3215", "jose@hotmail.com");
        Cliente c3 = new Cliente(null, "Julho", "547.654.123-36", "11 9 8354-3621", "julho@hotmail.com");

        Pedido p1 = new Pedido(null, data1, StatusDoPedido.ENVIADO, c1);
        Pedido p2 = new Pedido(null, data2, StatusDoPedido.PAGO, c2);
        Pedido p3 = new Pedido(null, data3, StatusDoPedido.AGUARDANDO_PAGAMENTO, c1);
        Pedido p4 = new Pedido(null, data4, StatusDoPedido.CANSELADO, c3);

        
        ClienteRepository.saveAll(Arrays.asList(c1, c2,c3));
        PedidoRepository.saveAll(Arrays.asList(p1, p2, p3,p4));

        ItemDoPedido item1 = new ItemDoPedido(p1, prod1, 2, prod1.getPreco());
        ItemDoPedido item2 = new ItemDoPedido(p1, prod2, 1, prod2.getPreco());
        ItemDoPedido item3 = new ItemDoPedido(p2, prod3, 4, prod3.getPreco());
        ItemDoPedido item4 = new ItemDoPedido(p3, prod5, 6, prod5.getPreco());
        ItemDoPedido item5 = new ItemDoPedido(p3, prod6, 2, prod6.getPreco());
        ItemDoPedido item6 = new ItemDoPedido(p2, prod4, 2, prod4.getPreco());
        
        
        ItemDopedidoRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5,item6));
        
        Pagamento pa = new Pagamento(null, data2.plusHours(2L), p1);
        Pagamento pa2 = new Pagamento(null, data1.plusHours(7L), p2);
        Pagamento pa3 = new Pagamento(null, data3.plusDays(1L), p3);
       
        // Aqui eu fiz uma associaçao de mão dupla em memoria, add um pagamento em um pedido
        // quando se tem um relacionamento entre dois objs que um não existem sem o outro,
        // mas o outro pode ixistir sem depender do outro 
        p1.setPagamento(pa);
        p2.setPagamento(pa2);
        p3.setPagamento(pa3);
        PedidoRepository.saveAll(Arrays.asList(p1,p2,p3));
    }

}
