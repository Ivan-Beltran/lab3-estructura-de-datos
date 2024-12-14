// Método de ordenamiento bubbleSort
fun bubbleSort(array: IntArray) {
    for (i in 0 until array.size - 1) {
        var swapped = false
        for (j in 0 until array.size - 1 - i) {
            if (array[j] > array[j + 1]) {
                val temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
                swapped = true
            }
        }
        if (!swapped) break
    }
}

// Método de ordenamiento quickSort
fun quickSort(array: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(array, low, high)
        quickSort(array, low, pi - 1)
        quickSort(array, pi + 1, high)
    }
}

private fun partition(array: IntArray, low: Int, high: Int): Int {
    val pivot = array[high]
    var i = low - 1
    for (j in low until high) {
        if (array[j] <= pivot) {
            i++
            array[i] = array[j].also { array[j] = array[i] }
        }
    }
    array[i + 1] = array[high].also { array[high] = array[i + 1] }
    return i + 1
}

//funcion para obtener el factorial de un numero
fun numberFactorial(number:Int):Int{
    if(number==0) return 1
    return number* numberFactorial(number-1)
}

// Función para resolver el problema de las Torres de Hanoi
fun hanoi(n: Int, source: String, target: String, auxiliary: String) {
    fun hanoiRecursive(n: Int, source: String, target: String, auxiliary: String, stepCount: Int = 0): Int {
        if (n == 1) {
            println("Paso ${stepCount + 1}: Mover disco 1 de Torre $source a Torre $target")
            return stepCount + 1
        }
        val step1 = hanoiRecursive(n - 1, source, auxiliary, target, stepCount)
        println("Paso ${step1 + 1}: Mover disco $n de Torre $source a Torre $target")
        return hanoiRecursive(n - 1, auxiliary, target, source, step1 + 1)
    }
    hanoiRecursive(n, source, target, auxiliary)
}

// Función para mostrar el menú
fun showOptions() {
    println("\nSeleccione una opción:")
    println("1. Ordenar una lista usando Bubble Sort")
    println("2. Ordenar una lista usando Quick Sort")
    println("3. Calcular el factorial de un número")
    println("4. Resolver las Torres de Hanoi")
    println("5. Salir")
}

// Función que convierte el input en un array de enteros separados por coma
fun createArrayFromInput(arrayInput: String?): IntArray? {
    if (arrayInput.isNullOrBlank()) return null
    return try {
        arrayInput.split(",").mapNotNull { it.trim().toIntOrNull() }.toIntArray().takeIf { it.isNotEmpty() }
    } catch (e: Exception) {
        null
    }
}

// Función genérica para procesar un array con un algoritmo de ordenamiento
fun processSortingAlgorithm(sortFunction: (IntArray) -> Unit) {
    var arrayNumber: IntArray? = null
    do {
        println("Ingrese un array de números separados por una coma:")
        val arrayInput = readLine()
        arrayNumber = createArrayFromInput(arrayInput)

        if (arrayNumber != null) {
            println("Lista original filtrando solo números: [${arrayNumber.joinToString()}]")

            val startTime = System.nanoTime()
            sortFunction(arrayNumber)
            val endTime = System.nanoTime()

            println("Array ordenado: [${arrayNumber.joinToString()}]")
            println("Tiempo de ejecución : %.10f segundos".format((endTime - startTime) / 1_000_000_000.0))
        } else {
            println("Entrada inválida, por favor vuelva a intentar.")
        }
    } while (arrayNumber == null)

    println("\nPresione Enter para continuar...")
    readLine()
}

fun main() {
    var continueProgram = true
    while (continueProgram) {
        showOptions()
        when (readLine()) {
            "1" -> processSortingAlgorithm { bubbleSort(it) }
            "2" -> processSortingAlgorithm { quickSort(it, 0, it.size - 1) }
            "3" -> {

                do{
                    println("Ingrese un número:")
                     var number = readLine()?.toIntOrNull()
                    if (number != null && number >= 0) {
                        println("El factorial de $number es: ${numberFactorial(number)}")
                    } else {
                        println("error, el dato ingresado es invalido.")
                    }
                }
                while (number==null || number < 0)

                println("\nPresione Enter para continuar...")
                readLine()
            }
            "4" -> {
                do{
                    println("Ingrese la cantidad de niveles para las torres:")
                    val levels = readLine()?.toIntOrNull()
                    if (levels != null && levels > 0) {
                        hanoi(levels, "A", "C", "B")
                    } else {
                        println("error, los datos ingresados fueron invalidos")
                    }
                }
                while (levels==null || levels<=0)
                println("\nPresione Enter para continuar...")
                readLine()
            }
            "5" -> {
                println("Saliendo del programa.")
                continueProgram = false
            }
            else -> println("Opción inválida. Por favor intente nuevamente.")
        }
    }
}