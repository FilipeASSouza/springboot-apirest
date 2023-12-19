## Ainda com erro 403? ##
## PRÓXIMA ATIVIDADE ##

## Atenção! ##

A utilização do Spring Security para implementar o processo de autenticação e autorização via JWT exige bastante mudanças no código, com a criação de novas classes e alteração de algumas já existentes no projeto. Tais mudanças devem ser feitas com muita atenção, para que o processo de autenticação e autorização na API funcione corretamente.

É bem comum receber erro 403 nas requisições disparadas no Insomnia, mesmo que você tenha implementado todo o código que foi demonstrado ao longo das aulas. Tal erro vai ocorrer somente no caso de você ter cometido algum descuido ao realizar as mudanças no projeto. Entretanto, existem diversas possibilidades que podem causar o erro 403 e veremos a seguir quais podem estar causando tal erro.

## 1) Erro ao recuperar o token JWT ##

Na classe SecurityFilter foi criado o método recuperarToken:

private String recuperarToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
        return authorizationHeader.replace("Bearer ", "");
    }

    return null;
}COPIAR CÓDIGO
Na linha do return, dentro do if, utilizamos o método replace da classe String do Java para apagar a palavra Bearer. Repare que existe um espaço em branco após a palavra Bearer. Um erro comum é esquecer de colocar esse espaço em branco e deixar o código assim:

return authorizationHeader.replace("Bearer", "");COPIAR CÓDIGO
Verifique se você cometeu esse erro no seu código! Uma dica é utilizar também o método trim para apagar os espaços em branco da String:

return authorizationHeader.replace("Bearer ", "").trim();COPIAR CÓDIGO

## 2) Issuer diferente ao gerar o token ##

Na classe TokenService foram criados os métodos gerarToken e getSubject:

public String gerarToken(Usuario usuario) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("API Voll.med")
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    } catch (JWTCreationException exception){
        throw new RuntimeException("erro ao gerar token jwt", exception);
    }
}

public String getSubject(String tokenJWT) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("API Voll.med")
                .build()
                .verify(tokenJWT)
                .getSubject();
    } catch (JWTVerificationException exception) {
        throw new RuntimeException("Token JWT inválido ou expirado!");
    }
}COPIAR CÓDIGO
Repare que nos dois métodos é feita uma chamada ao método withIssuer, da classe JWT:

.withIssuer("API Voll.med")COPIAR CÓDIGO
Tanto no método gerarToken quanto no getSubject o issuer deve ser exatamente o mesmo. Um erro comum é digitar o issuer diferente em cada método, por exemplo, em um método com letra maiúscula e no outro com letra minúscula.

Verifique se você cometeu esse erro no seu código! Uma dica é converter essa String do issuer em uma constante da classe:

private static final String ISSUER = "API Voll.med";

public String gerarToken(Usuario usuario) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getLogin())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
    } catch (JWTCreationException exception){
        throw new RuntimeException("erro ao gerar token jwt", exception);
    }
}

public String getSubject(String tokenJWT) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer(ISSUER)
                .build()
                .verify(tokenJWT)
                .getSubject();
    } catch (JWTVerificationException exception) {
        throw new RuntimeException("Token JWT inválido ou expirado!");
    }
}COPIAR CÓDIGO
Também é possível deixar essa String declarada no arquivo application.properties e injetá-la em um atributo na classe, similar ao que foi feito com o atributo secret.

## 3) Salvar a senha do usuário em texto aberto no banco de dados ##

Na classe SecurityConfigurations ensinamos ao Spring que nossa API vai utilizar o BCrypt como algoritmo de hashing de senhas:

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}COPIAR CÓDIGO
Com isso, ao inserir um usuário na tabela do banco de dados, sua senha deve estar no formato BCrypt e não em texto aberto:

mysql> select * from usuarios;
+----+--------------------+--------------------------------------------------------------+
| id | login              | senha                                                        |
+----+--------------------+--------------------------------------------------------------+
|  1 | ana.souza@voll.med | $2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5. |
+----+--------------------+--------------------------------------------------------------+
1 row in set (0,00 sec)COPIAR CÓDIGO
Verifique se a senha do usuário que você inseriu na sua tabela de usuários está no formato BCrypt! Um erro comum é inserir a senha em texto aberto. Por exemplo:

mysql> select * from usuarios;
+----+--------------------+--------+
| id | login              | senha  |
+----+--------------------+--------+
|  1 | ana.souza@voll.med | 123456 |
+----+--------------------+--------+
1 row in set (0,00 sec)COPIAR CÓDIGO
Se esse for o seu caso, execute o seguinte comando sql para atualizar a senha:

update usuarios set senha = '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.';COPIAR CÓDIGO
Obs: No json enviado pelo Insomnia, na requisição de efetuar login, a senha deve ser enviada em texto aberto mesmo, pois a conversão para BCrypt, e também checagem se ela está correta, é feita pelo próprio Spring.

No caso do erro 403 ainda persistir, alguma exception pode estar sendo lançada mas não sendo capturada pela classe TratadorDeErros que foi criada no projeto. Isso acontece porque o Spring Security intercepta as exceptions referentes ao processo de autenticação/autorização, antes da classe TratadorDeErros ser chamada.

Você pode alterar a classe AutenticacaoController colocando um try catch no método efetuarLogin, para conseguir ver no console qual exception está ocorrendo:

@PostMapping
public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
    try {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}COPIAR CÓDIGO
Outra dica é também imprimir no console o token que está chegando na API, para você ter a certeza de que ele está chegando corretamente. Para isso, altere o método getSubject, da classe TokenService, modificando a linha que lança a RuntimeException dentro do bloco catch:

public String getSubject(String tokenJWT) {
    try {
        var algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer(ISSUER)
                .build()
                .verify(tokenJWT)
                .getSubject();
    } catch (JWTVerificationException exception) {
        throw new RuntimeException("Token JWT inválido ou expirado: " +tokenJWT);
    }
}COPIAR CÓDIGO

Agora será mais fácil identificar qual exception de fato está ocorrendo na API, causando o erro 403 nas requisições.