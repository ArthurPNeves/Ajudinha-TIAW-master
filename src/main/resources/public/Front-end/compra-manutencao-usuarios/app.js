// declara um conjunto inicial de administradores
var db_usu_inicial = {
    "data": [
        {
            "nome": "Maria dos Santos",
            "email": "mariasantos@hotmail.com",
            "telefone": "3133221100",
            "senha": "123456",
            "endereco": "Rua São João",
            "numend": "34",
            "compend": "ap 201",
            "cidade": "Belo Horizonte",
            "estado": "MG",
            "cep": "33222-000"
        },
        {
            "nome": "João Silva",
            "email": "joaosilva@msn.com.br",
            "telefone": "11999887766",
            "senha": "abcdef",
            "endereco": "Avenida do Contorno",
            "numend": "1005",
            "compend": "",
            "cidade": "São Paulo",
            "estado": "SP",
            "cep": "34152-321"
        },
        {
            "nome": "Carlos Pereira",
            "email": "carlosp@facebook.com",
            "telefone": "31998564555",
            "senha": "asdfqwer",
            "endereco": "Rua Ouro Preto",
            "numend": "362",
            "compend": "ap 801 bloco 2",
            "cidade": "Manhuaçu",
            "estado": "MG",
            "cep": "36642-165"
        }
    ]
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_usu'));
if (!db) {
    db = db_usu_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}


function insertUsuario(usu) {
    console.log(usu)
    const options = {method: 'POST', mode: 'no-cors', include: 'same-origin', body: JSON.stringify(usu)};
    fetch('/api/logon', options)
        .then(res => res.json().then(body => console.log(body)))
        .catch(err => console.error(err) );
}


function logoutUser () {
    usuarioCorrente = {};
    sessionStorage.setItem ('usuarioCorrente', JSON.stringify (usuarioCorrente));
    window.location = LOGIN_URL;
}

colocarSair();

function colocarSair(){
    let tipo = JSON.parse(sessionStorage.getItem('usuarioCorrente')).tipo;
    let nomeUsu = "";

    let textoNome;
    
    if (tipo == "user")
    {
        nomeUsu = JSON.parse(sessionStorage.getItem('usuarioCorrente')).nome;
    } else if (tipo == "adm")
    {
        nomeUsu = JSON.parse(sessionStorage.getItem('usuarioCorrente')).usuario;
    }
    
    textoNome = `<p>${nomeUsu} | <a onclick="logoutUser()" href="/inicio-login/login.html">Sair</a></p>`;

    document.querySelector('#insertSair').innerHTML = textoNome;
}
