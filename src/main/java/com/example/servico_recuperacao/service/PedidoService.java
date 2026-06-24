package com.example.servico_recuperacao.service;

import org.springframework.stereotype.Service;

import com.example.servico_recuperacao.dto.EventoPagamentoDTO;

@Service
public class PedidoService {

    public void processarPedido(EventoPagamentoDTO evento) {
        
        String pedidoId = evento.getDados().getPedidoId();
        Double valorOriginal = evento.getDados().getValorPago();
        
        
        if (valorOriginal > 1000.00) {
            Double valorComDesconto = valorOriginal * 0.90; 
            System.out.println("Pedido " + pedidoId + " processado. Valor original: R$ " + valorOriginal + " | Valor com Desconto: R$ " + valorComDesconto);
        } else {
            System.out.println("Pedido " + pedidoId + " processado. Valor original: R$ " + valorOriginal + " | Sem desconto aplicado.");
        }
    }
}