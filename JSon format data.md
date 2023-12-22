## Para saber mais: formatação de datas

PRÓXIMA ATIVIDADE

Como foi demonstrado no vídeo anterior, o Spring tem um padrão de formatação para campos do tipo data quando esses são mapeados em atributos do tipo LocalDateTime. Entretanto, é possível personalizar tal padrão para utilizar outras formatações de nossa preferência.

Por exemplo, imagine que precisamos receber a data/hora da consulta no seguinte formato: dd/mm/yyyy hh:mm. Para que isso seja possível, precisamos indicar ao Spring que esse será o formato ao qual a data/hora será recebida na API, sendo que isso pode ser feito diretamente no DTO, com a utilização da anotação @JsonFormat:

@NotNull
@Future
@JsonFormat(pattern = "dd/MM/yyyy HH:mm")

## LocalDateTime dataCOPIAR CÓDIGO

No atributo pattern indicamos o padrão de formatação esperado, seguindo as regras definidas pelo padrão de datas do Java. Você pode encontrar mais detalhes nesta página do JavaDoc.

Essa anotação também pode ser utilizada nas classes DTO que representam as informações que a API devolve, para que assim o JSON devolvido seja formatado de acordo com o pattern configurado. Além disso, ela não se restringe apenas à classe LocalDateTime, podendo também ser utilizada em atributos do tipo LocalDate e LocalTime.