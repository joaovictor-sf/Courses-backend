# Há fazer
Lista do que fazer no back-end do projeto de cursos
## Banco
- [x] Criar o banco de dados
- [x] Vincular o banco de dados ao projeto
- [x] Criar tabela users
- [x] Criar tabela courses
- [ ] Vincular a tabela users com a tabela courses e vice-versa 1
## Sistema de Login
- [x] Criar as entidades
- [x] Sistema de registro
- [x] Sistema de login
- [ ] Sistema de logout
## Sistema de cursos
- [x] Criar as entidades
- [x] Sistema de adicionar cursos
- [x] Sistema de remover cursos
- [x] Sistema de editar cursos(No fron-end, ao selecionar o curso que o usuario deseje editar, os dados atuais do curso serão mostrados como se fosse um placeholder, e o usuário poderá editar os dados e salvar as alterações)
- [x] Sistema de listar cursos disponíveis
- [ ] Sistema de listar cursos do usuário
- [x] Sistema de listar cursos não disponíveis
- [x] Sistema de validar cursos
## Geral
- [ ] Vincluar o front-end com o back-end
- [ ] Tela de perfil do usuário **(Não é necessário)**


### Observações
O nome das variaveis no json são iguais ao nome das variaveis das classes dto.

# Documentação

## Autenticação
- url: /api/auth
- login: /login
- registro: /register

### Login
- Get
- Pede: String login, String password
- Retorna: String token e a role do usuário

### Registro
- Post
- Pede: String matricula, String password, String name, UserRole role
- A senha é criptografada com o algoritmo BCrypt
- Retorna: Nada, alem do status code 200

## Cursos
- url: /api/course

### Pegar curso
- Get
- url: /api/course/{id}
- Pede: id
- Retorna: id, name, description, imageUrl, videoUrl, userMatricula, null

### Lista de cursos disponíveis
- Get
- Pede: Nada, alem do token de autenticação
- Retorna: id, name, description, imageUrl, videoUrl, userMatricula, null

### Adicionar curso
- Post
- Pede: String name, String description, String imageUrl, String videoUrl, String userMatricula, UserRole role
- Retorna: Nada, alem do status code 200

### Update curso
- Put
- Pede: String name, String description, String imageUrl, String videoUrl, String userMatricula
- Retorna: id, name, description, imageUrl, videoUrl, userMatricula, null

### Invalidar curso
- Delete
- url: /api/course/delete/{id}
- Pede: id
- Retorna: Nada, alem do status code 200

### Pegar todos os cursos de um usuário
- Get
- url: /api/user/{matricula}
- Pede: matricula
- Retorna: id, name, description, imageUrl, videoUrl, userMatricula, null
- Observação: O metodo esta no UserController

## Admin
- url: /api/adm

### Lista de cursos não disponíveis
- Get
- Pede: Login de um usuário com a role de admin
- Retorna: id, name, description, imageUrl, videoUrl, userMatricula, null

### Validar curso
- Put
- url: /api/adm/{id}
- Pede: id e login de um usuário com a role de admin
- Retorna: Nada, alem do status code 200

### True Delete
- Delete
- url: /api/adm/{id}
- Pede: id e login de um usuário com a role de admin
- Retorna: Nada, alem do status code 200