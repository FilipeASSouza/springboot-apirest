## Para saber mais: anotação @JsonAlias

PRÓXIMA ATIVIDADE

Aprendemos que os nomes dos campos enviados no JSON para a API devem ser idênticos aos nomes dos atributos das classes DTO, pois assim o Spring consegue preencher corretamente as informações recebidas.

Entretanto, pode acontecer de um campo ser enviado no JSON com um nome diferente do atributo definido na classe DTO. Por exemplo, imagine que o seguinte JSON seja enviado para a API:

{
    “produto_id” : 12,
    “data_da_compra” : “01/01/2022”
}COPIAR CÓDIGO
E a classe DTO criada para receber tais informações seja definida da seguinte maneira:

public record DadosCompra(
    Long idProduto,
    LocalDate dataCompra
){}COPIAR CÓDIGO
Se isso ocorrer, teremos problemas, pois o Spring vai instanciar um objeto do tipo DadosCompra, mas seus atributos não serão preenchidos e ficarão como null em razão de seus nomes serem diferentes dos nomes dos campos recebidos no JSON.

## Temos duas possíveis soluções para essa situação:

1) Renomear os atributos no DTO para terem o mesmo nome dos campos no JSON;

2) Solicitar que a aplicação cliente, que está disparando requisições para a API, altere os nomes dos campos no JSON enviado.

A primeira alternativa citada anteriormente não é recomendada, pois os nomes dos campos no JSON não estão de acordo com o padrão de nomenclatura de atributos utilizado na linguagem Java.

A segunda alternativa seria a mais indicada, porém, nem sempre será possível “obrigar” os clientes da API a alterarem o padrão de nomenclatura utilizado nos nomes dos campos no JSON.

Para essa situação existe ainda uma terceira alternativa, na qual nenhum dos lados (cliente e API) precisam alterar os nomes dos campos/atributos. Basta, para isso, utilizar a anotação @JsonAlias:

public record DadosCompra(
    @JsonAlias(“produto_id”) Long idProduto,
    @JsonAlias(“data_da_compra”) LocalDate dataCompra
){}COPIAR CÓDIGO

A anotação @JsonAlias serve para mapear “apelidos” alternativos para os campos que serão recebidos do JSON, sendo possível atribuir múltiplos alias:

public record DadosCompra(
    @JsonAlias({“produto_id”, “id_produto”}) Long idProduto,
    @JsonAlias({“data_da_compra”, “data_compra”}) LocalDate dataCompra
){}COPIAR CÓDIGO

Dessa forma resolvemos o problema, pois o Spring, ao receber o JSON na requisição, vai procurar os campos considerando todos os alias declarados na anotação @JsonAlias.