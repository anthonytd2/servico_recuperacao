# Serviço de Recuperação - Consumidor SQS

Este projeto é um microsserviço feito em Spring Boot focado em consumir mensagens de uma fila SQS da AWS e aplicar uma regra de desconto para pedidos.

## Justificativa da Arquitetura

Para estruturar o projeto, decidi usar uma divisão simples por camadas junto com o padrão de Adapter. O objetivo principal foi separar o que é conexão com a nuvem do que é a regra do sistema de verdade, deixando o código mais organizado e cada classe com uma responsabilidade única.

Eu dividi as pastas e o fluxo da seguinte forma:

- **Adapter (`pacote listener`):** Criei a classe `SqsPedidoAdapter` para ser o meu ponto de contato com a AWS. O único trabalho dela é ficar escutando a fila, pegar a mensagem quando ela chega e repassar pra frente.
- **Moldes (`pacote dto`):** Criei o `EventoPagamentoDTO` e o `DadosPedidoDTO` com os atributos exatos do JSON da atividade. Isso facilita pro Spring converter a mensagem de texto bruto em um objeto Java que eu consiga manipular mais fácil.
- **Regra de Negócio (`pacote service`):** A lógica de verificar se o valor passou de R$ 1000,00 e aplicar os 10% de desconto ficou 100% isolada dentro do `PedidoService`.

O principal motivo de eu ter escolhido organizar dessa maneira é que o meu Service não faz ideia de que a mensagem veio de uma fila SQS. Se um dia for preciso trocar a AWS por um RabbitMQ ou Kafka, eu não preciso mexer em nenhuma linha da minha regra de negócio, precisaria apenas criar um adapter novo. Isso deixa o código muito mais fácil de dar manutenção.
