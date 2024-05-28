package org.example

import java.util.Date

fun main() {
    println("Hello World!")
    //Inmutables (no se pueden RE ASIGNAR "=")
    //val <nombre>: <tipo>= "valor"
    val inmutable: String="Erick"
    // inmutable: String="jose"  //Error
    var mutable: String="Mardix"
    mutable="Erick" //ok
    //val > var
    println(mutable)

    //Duck Typing
    var ejemploVariable= "Adrian Eguez"
    val edadEjemplo: Int = 12
    val edad=12
    // trim = para eliminar espacios al inicio y al final
    ejemploVariable.trim()
    println(ejemploVariable.trim())
    //variables primitivas
    val nombre:String="Adrian"
    val sueldo:Double= 1.2
    val estadoCivil: Char='C'
    val mayorEdad: Boolean = true
    // Clases en java
    val fechaNacimiento:Date= Date()
    // when (Switch)
    val estadoCivilWhen="C"
    when (estadoCivilWhen){
        ("C")-> {
        println("Casado")
        }
        "S"-> {
            println("Soltero")
        }
        else -> {
        println("Casado")
        }
    }
    val esSoltero = (estadoCivilWhen=="S")
    val coqueteo = if (esSoltero) "Si" else "No" // if else chiquito

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    //Named parameters
    //calcularSueldo(sueldo, tasa, bonoespecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa= 14.00)
    //uso de clases
    val sumaUno = Suma(1,1) //new Suma(1,1) en kotlin no hay "new"
    val sumaDos= Suma(null,1)
    val sumaTres = Suma(1, null)
    val sumaCuatro= Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
//// Arreglos
    //Arreglos
//Estáticos
    val arregloEstatico:Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico);

//Dinámicos
    val arregloDinamico:ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)

    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

///Operadores de arreglos
    //FOR EACH=> Unit
    // Iterar un arreglo
    // El tipo de dato Unit es para demostrar que no retorna nada
    val respuestaForEach: Unit = arregloDinamico.forEach{
        valorActual: Int -> // ->
        println("Valor actual:${valorActual}");
    }
    //"it" (en ingles "eso") significa el elemento iterado= el valor actual
    arregloDinamico.forEach { println("Valor Actual (it): ${it}")}

    //MAP-> MUTA(Modifica cambia) el arreglo
    //1) Enviamos el nuevo valor a la iteración
    //2) Nos devuelve un Nuevo Arreglo con los valores de las iteraiones


    val respuestaMAP: List<Double> = arregloDinamico
        .map{ valorActual:Int->
            //@map= sintaxis de kotlin para indicar un return de dicha función map
            //@pepito = indica el nombre de la función a la que se va retornar
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMAP)
    val respuestaMapDos = arregloEstatico.map{it+15}
    println(respuestaMapDos)
    //Filter -> Filtrar el arreglo
    //1) Devolver una expresion (True o False)
    //2) Nuevo arreglo FILTRADO
    val respuestaFilter = arregloDinamico
        .filter { valorActual: Int ->
            //Expresion o CONDICION
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }
    val respuestaFilterDos= arregloDinamico.filter { it<= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR-->Any (Alguno Cumple?)
    //AND-->ALL(todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual:Int->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //True

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual:Int->
            return@all (valorActual > 5)
        }
    println(respuestaAll) //false

    ///REDUCE -->
    //valor acumulado = 0 (siempre empieza en 0 en Kotlin)
    //[1,2,3,4,5]-> Acumular "SUMAR" estos valores del arreglo
    //valorIteración1 = valorEmpieza +1 = 0+1=1-> Iteracion1
    //valorIteración2 = valorAcumuladoIteracion1 + 2 = 1 + 2 = 3 --> Iteracion2
    //...
    //...
    //...
    //...
    val respuestaReduce: Int = arregloDinamico
        .reduce { acumulado:Int, valorActual:Int->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce)
}

///Funciones
// como crear funciones dentro del lenguaje del kotlin
fun imprimirNombre(nombre:String):Unit{
    println("Nombre: ${nombre}") //template String
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, //Opcionsueldo al (defecto)
    bonoEspecial:Double?=null //Opcional (nullable)
    // variable-> "?" es nuellable (osea que puede en algun momento ser nulo)
): Double{
    //Int -> Int?(nullable)
    //string -> String? (nullable)
    //Date --> Date? (nullable)
    if(bonoEspecial==null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa)* bonoEspecial
    }
}

abstract class NumeroJava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno=uno
        this.numeroDos=dos
        println("inicializando")
    }
}

abstract class Numeros( //constructor primario
    // Caso 1) Parametro normal
    // uno: Int, (parametro (sin modificar acceso))

    //Caso 2) Parametro y propiedad (atributo) (private)
    //private var uno: Int (propiedad "instancia.uno")

    protected  val numeroUno:Int,//instancia.numeroUno
    protected  val numeroDos: Int, //instancia.numeroDos
    // parametroInutil:String, //Parametro
){
    init {
        this.numeroUno
        this.numeroDos
        //thsi,parametroInutil// error no existe
        println("Inicializarlo")
    }

}

class Suma(// Constructor primario
    unoParametro: Int, //Parametro
    dosParametro:Int,  //Parametros
): Numeros( // Clase papa, Numeros (extendido)
    unoParametro,
    dosParametro
){
    public val soyPublicoExplicito: String = "Explicito" // Publicas
    val soyPublicoImplicito:String="Implicito" //Publicas (propiedad, metodos)

    init{// Bloque Codigo Constructor primario
        //this.unoPrametro // ERROR no existe
        this.numeroUno
        this.numeroDos
        numeroUno // this. OPCINAL (propiedades, metodos)
        numeroDos // this. OPCINAL (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. Opcinal (propiedades, metodos)
    }

    //Multiles constructores

    constructor(//constructor secundario
        uno:Int?,
        dos:Int
    ):this(
        if(uno==null) 0 else uno,
        dos
    )

    constructor(//constructor tercero
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos==null) 0 else dos,
    )

    constructor(//constructor cuarto
        uno:Int?,
        dos:Int?
    ):this(
        if(uno==null) 0 else uno,
        if(dos==null) 0 else dos
    )

    // public fun sumar():Int (modificar "public" es OPCIONAL)
    fun sumar(): Int {
        val total = numeroUno + numeroDos
        //Suma.agregarHistorial(total) ("Suma." o "NombreClase." es OPCIONAL)
        agregarHistorial(total)
        return total
    }

    //propiedad de una clase
    companion object { //Comparte entre todos (es como tener estaticas)
        //funciones y variables
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorTotalSuma: Int) {
            historialSumas.add(valorTotalSuma)
        }

    }

}





