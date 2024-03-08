package funcoes

import kotlin.math.sqrt
import kotlin.math.PI

// Função que irá calcular a área do retângulo
fun areaRetangulo(base: Double, alt: Double): Double {
    return (base * alt)
}

//Função que irá calcular a área de todos os triângulos (Equilátero, Isóceles e Escaleno)
fun areaTrianguloEquilatero(lado: Double): Double {
    return (lado * lado * sqrt(3.0)) / 4
}
fun areaTrianguloIsoceles(base: Double, alt: Double): Double {
    return (base * alt)/2
}
fun areaTrianguloEscaleno(base: Double, alt: Double): Double {
    return (base * alt)/2
}

//Função que irá calcular a circuferência
fun areaCircuferencia(raio: Double): Double {
    return ((2 * PI) * raio)
}

//Função que irá calcular o trapézio
fun areaTrapezio(basemaior: Double, basemenor: Double, alt: Double): Double {
    return (basemaior+basemenor*alt)/2
}

//Função que irá calcular o losango
fun areaLosango(diagonalmaior: Double, diagonalmenor: Double): Double {
    return (diagonalmaior * diagonalmenor)/2
}

//Aqui estão os println(s) do que será visto na tela pelo usuário
fun main(args: Array<String>) {
    while (true) {
        println(" Bem vindo (a) a Calculadora de Áreas")
        println("Por favor, selecione a forma geométrica que deseja calcular a área:")
        println("1: Retângulo")
        println("2: Triângulo Equilátero")
        println("3: Triângulo Isósceles")
        println("4: Circunferência")
        println("5: Losango")
        println("6: Trapézio")
        print("Digite o número conforme a forma geométrica desejada: ")
        val forma = readLine()?.toIntOrNull()

//Aqui estão os println(s) do que será pedido ao usuário selecionar uma opção
        when (forma) {
            1 -> {
                print("Digite o valor da base: ")
                var base = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da altura: ")
                var alt = readLine()?.toDoubleOrNull() ?: return
                println("A área do Retângulo é: ${areaRetangulo(base, alt)}")
            }
            2 -> {
                print("Digite o valor do lado: ")
                var lado = readLine()?.toDoubleOrNull() ?: return
                println("A área do Triângulo Equilátero é: ${areaTrianguloEquilatero(lado)}")
            }
            3 -> {
                print("Digite o valor da base: ")
                var base = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da altura: ")
                var alt = readLine()?.toDoubleOrNull() ?: return
                println("A área do Triângulo Isósceles é: ${areaTrianguloIsoceles(base, alt)}")
            }
            3 -> {
                print("Digite o valor da base: ")
                var base = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da altura: ")
                var alt = readLine()?.toDoubleOrNull() ?: return
                println("A área do Triângulo Escaleno é: ${areaTrianguloEscaleno(base, alt)}")
            }
            4 -> {
                print("Digite o valor do raio: ")
                var raio = readLine()?.toDoubleOrNull() ?: return
                println("A área da Circuferencia  é: ${areaCircuferencia(raio)}")
            }
            5 -> {
                print("Digite o valor da base maior: ")
                var basemaior = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da base menor: ")
                var basemenor = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da altura: ")
                var alt = readLine()?.toDoubleOrNull() ?: return
                println("A área do Trapezio é: ${areaTrapezio(basemaior, basemenor, alt)}")
            }
            6 -> {
                print("Digite o valor da diagonal maior: ")
                var diagonalmaior = readLine()?.toDoubleOrNull() ?: return
                print("Digite o valor da diagonal menor: ")
                var diagonalmenor = readLine()?.toDoubleOrNull() ?: return
                println("A área do Losango é: ${areaLosango(diagonalmaior, diagonalmenor)}")
            }

//Aqui está a função else para caso ocorra do usuário selecionar uma opção inexistente
            else -> {
                println("A opção digitada não corresponde. Digite um outro valor.")
            }
        }

//Aqui está a função para o usuário reiniciar ou encerrar o programa
        println("Você deseja reiniciar o programa? (S/N)")
        val resposta = readLine()?.toUpperCase()
        if (resposta != "S") {
            break
        }
    }
    println("O programa foi encerrado. Até mais!")
}