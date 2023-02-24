import java.util.Scanner;
import java.util.Random;

class Main {


  
  public static String generarPalabra() {
    String[] _diccionario = {"cabildo", 
      "democracia", "dialogo", "consenso",
      "pluriculturalidad", "participacion",
      "asertividad", "efectividad", "coordinacion",
      "paz"};
    Random _generador= new Random();
    return _diccionario[_generador.nextInt(10)];
  }



  

  public static boolean imprimirJuego(String _palabraElegida, char[] _letras, int _iteracion) {
    boolean _haImpreso= false;
    char[] _palabra = _palabraElegida.toCharArray();
    int cuentaLetrasFaltantes = 0; 

    
    
    System.out.print("Has ingresado hasta ahora: ");
    
    for(int h = 0; h < _iteracion; h++){
      System.out.print(_letras[h]);
      if (h != _iteracion - 1){
        System.out.print(", ");}
    }
    System.out.println("\n");
  

    
    for (int i = 0; i < _palabra.length; i++){
      for (int j = 0; j < _letras.length; j++){
        if (_palabra[i] == _letras[j]){
          System.out.print(_palabra[i]);
          _haImpreso = true;
        }
      }
      if (!_haImpreso){
        System.out.print('_');
        cuentaLetrasFaltantes++;
      }
      _haImpreso = false;
    }
    System.out.println("\n\n");

    return(cuentaLetrasFaltantes == 0);
  }




  
  public static void main(String[] args) {
    Scanner _input = new Scanner(System.in);
    String _palabra = generarPalabra();
    char _letrasIngresadas[] = new char[27];
    int _errores = 0;
    int _iteracion = 0;
    boolean _seHaGanado = false;
    char _temp;
    //System.out.println(_palabra); debug

    while (_errores < 7) {
      
      _seHaGanado = imprimirJuego(_palabra, _letrasIngresadas, _iteracion);
      if (_seHaGanado){
        System.out.printf("Ganaste!!! te tomó %d turnos\n", _iteracion);
        System.exit(0);;}


      
      System.out.printf("Tienes %d oportunidades para cometer un error\n", (7 - _errores));
      System.out.println("Ingresa un caracter! (sin tildes): ");
      _temp = _input.nextLine().toLowerCase().charAt(0);

      if ((Character.isLetter(_temp)) && (new String(_letrasIngresadas).indexOf(_temp) == -1 )){
        _letrasIngresadas[_iteracion] = _temp; // este
      }
      else if (!Character.isLetter(_temp)){
        System.out.println("Ingresa una letra!! (Sin tilde)"); // este
        continue;
      }
      else if (!(new String(_letrasIngresadas).indexOf(_temp) == -1)){
        System.out.println("Ya ingresaste esa letra!");
        continue;// y este; son para asegurar que el input sea puro y pueda ser almacenado y comparado
      }


      
      if (_palabra.indexOf(_temp) == -1){
        System.out.printf("Error!!, \'%c\' no hace parte de la palabra\n", _temp);
        _errores++;
      }
      
      _iteracion++;

      System.out.print("\033[H\033[2J");  // usando ANSI, \033[H coloca el cursor de la terminal al inicio del texto y \033[2J limpia la consola desde el cursor hasta el fin de la pantalla
    }

    System.out.printf("Perdiste :/, mejor suerte a la próxima :), la palabra era %s y sobreviviste %d turnos\n", _palabra, _iteracion);
  }
}
