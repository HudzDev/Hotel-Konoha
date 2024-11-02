fun main() {
    login()
}

fun login() {

    val hotelNome = "Konoha"
    println("\nBem-vindo ao Hotel $hotelNome!")

    print("\nPor favor, insira seu nome: ")
    val nome = readln()

    print("\nPor favor, insira sua senha: ")
    val senha = readln()

    if (senha == "2678") {
        println("\nBem-vindo ao Hotel $hotelNome, $nome. É um imenso prazer ter você por aqui!")
        menuPrincipal(nome)
    } else {
        println("\nSenha incorreta, tente novamente.")
        login()
    }
}

fun menuPrincipal(nomeUsuario: String) {

    while (true) {
    println(
    """
    Escolha uma opção:
    1 - Cadastrar Quartos
    2 - Cadastrar Hóspedes
    3 - Abastecimento de Automóveis
    4 - Sair do Hotel
    5 - Verificar Reservas
    6 - Gerenciar Hóspedes
    7 - Gerenciar Eventos
    8 - Orçamento para Manutenção de Ar-Condicionados
    """.trimIndent() )

    val opcao = readln().toIntOrNull()

    when (opcao) {
    1 -> cadastrarQuartos(nomeUsuario)
    2 -> cadastrarHospedes(nomeUsuario)
    3 -> abastecerCarro(nomeUsuario)
    4 -> {
    println("\nObrigado por utilizar o sistema, até mais!")
    return }
    5 -> mostrarQuartos()
    6 -> gerenciarHospedes()
    7 -> gerenciarEventos()
    8 -> orcamentoManutencaoArCondicionado(nomeUsuario)
    else -> println("\nOpção inválida.")
  }
 }
}

val quartos = Array(20) { "livre" }

val hospedes = mutableListOf<Pair<String, Int>>()

fun cadastrarQuartos(nomeUsuario: String) {

    var valorDiaria: Double
    var dias: Int
    var total: Double = 0.0

    while (true) {
    print("\nQual o valor padrão da diária? ")
    valorDiaria = readln().toDoubleOrNull() ?: continue
    if (valorDiaria < 0) {
    println("\nValor inválido, $nomeUsuario.")
    continue }

    print("\nQuantas diárias serão necessárias? ")
    dias = readln().toIntOrNull() ?: continue
    if (dias < 1 || dias > 30) {
    println("\nQuantidade de dias inválida, $nomeUsuario.")
    continue }

    total = valorDiaria * dias
    println("\nO valor de $dias dias de hospedagem é de R$$total.")
    break
    }

    print("\nInsira o nome do hóspede: ")
    val hospede = readln()

    while (true) {

    print("\nQual o quarto para reserva? (1 - 20)? ")
    val numeroQuarto = readln().toIntOrNull() ?: continue

    if (numeroQuarto in 1..20) {
    if (quartos[numeroQuarto - 1] == "ocupado") {
    println("\nQuarto está ocupado. Escolha outro.")
    mostrarQuartos()
    } else {
    println("\nQuarto Livre.")
    print("\n$nomeUsuario, você confirma a hospedagem para $hospede por $dias dias para o quarto $numeroQuarto por R$$total? S/N: ")
        
    val confirmacao = readln().uppercase()

    if (confirmacao == "S") {
    quartos[numeroQuarto - 1] = "ocupado"
    hospedes.add(Pair(hospede, numeroQuarto))
    println("\n$nomeUsuario, reserva efetuada para $hospede.")
    } else {
    println("\nReserva cancelada.") }
                
    mostrarQuartos()
    break
    }
    } else {
    println("\nNúmero do quarto inválido. Escolha um número entre 1 e 20.")
  }
 }
}

fun mostrarQuartos() {

    println("\nQuartos:")
    for (i in quartos.indices) {
    println("${i + 1} - ${quartos[i]};")
 }
}

fun cadastrarHospedes(nomeUsuario: String) {

    print("\nQual o nome do hóspede? ")
    val hospede = readln()
    println("\nHóspede $hospede cadastrado com sucesso!")
    hospedes.add(Pair(hospede, -1))
}

fun gerenciarHospedes() {

    println("\nLista de hóspedes:")
    if (hospedes.isEmpty()) {
    println("\nNenhum hóspede cadastrado.")
    } else {
    hospedes.forEach { (nome, quarto) ->
    if (quarto != -1) {
    println("$nome - Quarto: $quarto")
    } else {
    println("$nome - Não está alocado em um quarto.") }
  }
 }
}

fun gerenciarEventos() {

    println("\nQuantidade de Convidados")
    print("\nQual o número de convidados para o seu evento? ")
    val convidados = readln().toIntOrNull() ?: return

    if (convidados > 350 || convidados <= 0) {
        println("\nNúmero de convidados inválido.")
        return
    }

    if (convidados <= 150) {
        println("\nUse o auditório Laranja.")
    } else {
        println("\nUse o auditório Colorado.")
    }

    println("\nAgora vamos ver a agenda do evento.")
    parte2Agenda(convidados)
}

fun parte2Agenda(convidados: Int) {

    print("\nQual o dia do seu evento? ")
    val dia = readln()
    print("\nQual o horário do seu evento? ")
    val hora = readln().toIntOrNull()

    if (hora == null || (dia in listOf("sabado", "domingo") && hora >= 15) || (hora < 7 || hora > 23)) {
        println("\nHorário inválido ou auditório indisponível.")
        return
    }

    print("\nQual o nome da empresa? ")
    val empresa = readln()
    println("\nAuditório reservado para $empresa: $dia às ${hora}hs.")
    parte3Garcons(empresa, hora, convidados)
}

fun parte3Garcons(empresa: String, hora: Int, convidados: Int) {

    val garconsNecessarios = (convidados / 50) + 1
    val custoGarcon = 150.0
    val duracao = 4
    parte4Buffet(convidados, custoGarcon, garconsNecessarios, empresa, hora, duracao)
}

fun parte4Buffet(convidados: Int, custoGarcon: Double, garconsNecessarios: Int, empresa: String, hora: Int, duracao: Int) {

    val custoTotalGarcons = custoGarcon * garconsNecessarios * duracao
    println("\nO custo total dos garçons será de R$$custoTotalGarcons.")

    println("\nGostaria de efetuar a reserva? S/N ")
    val reserva = readln().uppercase()
    if (reserva == "S") {
        println("\nReserva do evento confirmada para $empresa com $convidados convidados.")
    } else {
        println("\nReserva do evento cancelada.")
    }
}

fun abastecerCarro(nomeUsuario: String) {

    print("\nQual o valor do álcool no posto Wayne Oil? ")
    val alcoolWayne = readln().toDoubleOrNull() ?: return
    print("\nQual o valor da gasolina no posto Wayne Oil? ")
    val gasolinaWayne = readln().toDoubleOrNull() ?: return
    print("\nQual o valor do álcool no posto Stark Petrol? ")
    val alcoolStark = readln().toDoubleOrNull() ?: return
    print("\nQual o valor da gasolina no posto Stark Petrol? ")
    val gasolinaStark = readln().toDoubleOrNull() ?: return

    val tanqueLitros = 42
    val custoAlcoolWayne = alcoolWayne * tanqueLitros
    val custoGasolinaWayne = gasolinaWayne * tanqueLitros
    val custoAlcoolStark = alcoolStark * tanqueLitros
    val custoGasolinaStark = gasolinaStark * tanqueLitros

    val melhorCombustivelWayne = if (alcoolWayne <= gasolinaWayne * 0.7) {
        "álcool"
    } else {
        "gasolina"
    }

    val melhorCombustivelStark = if (alcoolStark <= gasolinaStark * 0.7) {
        "álcool"
    } else {
        "gasolina"
    }

    val resultadoWayne = if (melhorCombustivelWayne == "álcool") {
        "\né mais barato abastecer com álcool no posto Wayne Oil."
    } else {
        "\né mais barato abastecer com gasolina no posto Wayne Oil."
    }

    val resultadoStark = if (melhorCombustivelStark == "álcool") {
        "\né mais barato abastecer com álcool no posto Stark Petrol."
    } else {
        "\né mais barato abastecer com gasolina no posto Stark Petrol."
    }

    val menorCusto = if (custoAlcoolWayne < custoGasolinaWayne && custoAlcoolStark < custoGasolinaStark) {
        "Posto Wayne Oil."
    } else if (custoAlcoolWayne > custoGasolinaWayne && custoAlcoolStark > custoGasolinaStark) {
        "Posto Stark Petrol."
    } else {
        "\nOs custos são equivalentes."
    }

    println("\nNo posto Wayne Oil, $resultadoWayne")
    println("\nNo posto Stark Petrol, $resultadoStark")
    println("\nO menor custo de abastecimento é no: $menorCusto")
}

fun orcamentoManutencaoArCondicionado(nomeUsuario: String) {
    print("\nQuantos ar-condicionados precisam de manutenção? ")
    val quantidade = readln().toIntOrNull() ?: return

    print("\nQual o valor cobrado pela empresa para a manutenção? ")
    val valorPorAr = readln().toDoubleOrNull() ?: return

    val custoTotal = quantidade * valorPorAr
    println("\nO custo total para a manutenção de $quantidade ar-condicionados é de R$$custoTotal.")
}
