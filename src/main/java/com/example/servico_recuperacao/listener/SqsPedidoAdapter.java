package com.example.servico_recuperacao.listener;

import org.springframework.stereotype.Component;

import com.example.servico_recuperacao.dto.EventoPagamentoDTO;
import com.example.servico_recuperacao.service.PedidoService;

import io.awspring.cloud.sqs.annotation.SqsListener;

@Component
public class SqsPedidoAdapter {

    private final PedidoService pedidoService;

    public SqsPedidoAdapter(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    
    @SqsListener("${queue.order-events}")
    public void listen(EventoPagamentoDTO dto) {
        System.out.println("\n========================================");
        System.out.println("Mensagem recebida do pedido: " + dto.getDados().getPedidoId());
        
        // Manda o pedido para o Service calcular o desconto
        pedidoService.processarPedido(dto);
        System.out.println("========================================\n");
    }
}