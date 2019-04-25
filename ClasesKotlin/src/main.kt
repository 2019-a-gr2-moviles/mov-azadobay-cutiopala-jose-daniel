class Usuario {
    public var nombre: String = " "
    public val apellido: String;

    constructor(apellido: String) {
        this.apellido = apellido;
    }
}

class UsuarioKT(
    public val nombre: String,
    public val apellido: String
) {

}

// En kotlin por defecto las propiedades son publicas.
// En kotlin si se desea que una variable sea privada es PRIVATE o PROTECTED;

fun a() {
    var adrian = UsuarioKT("a", "b");
    adrian.nombre
}

//Clase abtracta

class Numeros(
    var n1: Int,
    var n2: Int
) {

}

fun presley(
    requerido: Int,
    opcional: Int = 1,
    nulo: Int?
) {

}


fun app() {
    presley(requerido = 1, nulo = 0);
    presley(1, 1, 0);
    presley(1, 1, null);
}

