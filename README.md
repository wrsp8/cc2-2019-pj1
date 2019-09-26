# CC2 - Proyecto #1 - Workshop Scheduler

------------------------------------------------------------------------------
**Proyecto:** WorkshopScheduler\
**Tema:** Pilas, Colas, Paquetes, Herencia y Threads\
**Fecha de Entrega:** Indicada en la asignación del GES\
**Grupo:** Parejas\
**Calificacion personal:** Nosotros le indicaremos la fecha.

------------------------------------------------------------------------------

**ATENCION:** Todos los proyectos consumen tiempo, así que trate de empezar lo más pronto que pueda. Recuerde que el proyecto es en PAREJAS en todo el sentido, es decir que no puede trabajar junto a otros compañeros de otros grupos.

## Orders Scheduling
Para este proyecto usted implementará una simulación de un Calendarizador de Órdenes de Pintura para un taller de enderezado y pintura de prestigio.
Es entonces donde se debe aplicar un algoritmo que decida cual de las órdenes se debe atender primero.
Generalmente, las órdenes son diferentes, y se diferencian por el tiempo que requieren para pintar piezas.
A continuación le explicaremos algunas de las políticas para este proyecto:

 - **First-Come First-Served (FCFS)**: La orden que llega de primero, es la primera en ser atendida.
 - **Last-Come First-Served (LCFS)**: La orden que llega de último se atiende primero.
 - **Round-Robin (RR)**: La orden se atiende pieza por pieza. Es decir, si un vehículo genera una orden para 2 piezas, se atiende una pieza y luego la orden se encola de nuevo al final. De esta manera la pieza faltante de la orden se atenderá en su próximo turno. Cuando ya se acabaron de pintar todas las piezas de la orden, esta sale de la cola definitivamente. La idea de esta política es que todas las órdenes se puedan ir atendiendo poco a poco.

 ## Su proyecto
**En general:** ¿Qué es lo que tiene que hacer en su proyecto? Implementar la simulación del funcionamiento de un Calendarizador de Órdenes. Su calendarizador debe implementar la simulación para las tres políticas descritas anteriormente:
 - First Come First Served
 - Last Come First Served
 - Round Robin

utilizando solo una cola de atención.

## Especificaciones:
La política en que se manejarán las órdenes será escogida por el usuario al momento de ejecutar el programa. Por cada ejecución solo se podrá correr la simulación de una política a la vez (más adelante se mostrará cómo elige el usuario dicha política). Ya escogida la política, su programa debe empezar la simulación de ingreso y atención de órdenes. El ingreso y la atención de órdenes deben ser procesos/acciones INDEPENDIENTES. 

Cada orden debe guardar un número de orden y un tiempo de atención por pieza en segundos o milisegundos (como le funcione a usted mejor). Este tiempo está definido dependiendo el tipo de orden que sea. En este proyecto manejaremos tres tipos de órdenes: Sedán, Microbús, y Coupé. Los tiempos por pieza de cada tipo serán definidos al momento de mandar a ejecutar el programa, y serán fijos para todos las órdenes del mismo tipo. Ejemplo: Todos las órdenes de Microbuses duran 300 milisegundos, todas los de Sedanes duran 200 milisegundos, y así sucesivamente.

Las órdenes deben ser generadas en forma aleatoria, y en tiempos aleatorios. Además, las placas de los vehículos deben generarse en forma aletoria en formato `'DDDLLL'` (`D` = Dígito y `L` = Letra Mayúscula). Ejemplo: La placa generada para cierta orden podría ser `071CVR`. El número de orden debe ir incrementando (no es aleatorio) con cada nueva orden, es decir que la primer orden tendrá numOrden = 1, el segundo numOrden = 2, etc. Su tipo será aleatorio, es decir que se eligirá de forma aleatorea qué tipo de orden es (coupé, sedán o microbús). El numero correlativo es compartido entre todos los tipos de órdenes, es decir que no hay contadores separados para cada tipo.

Las órdenes generadas aleatoriamente se posicionaran en una "cola" de órdenes de atención. Dicha "cola" debe ser implementada según la política que se haya escogido. Nótese que "cola" hace referencia a una estructura de datos, la que se adapte mejor a la política.  

El taller debe ir atendiendo las órdenes almacenadas en la estructura de datos, dependiendo de la política que se haya escogido. Cada orden tiene su tiempo de atención según la cantidad de piezas, y ese tiempo es lo que se debe tardar el "taller" en atenderlas. Como esto es solo una simulación, el taller solo tiene que "esperar" el período que la orden tardaría en ser atendida, en vez de atenderla en realidad. Es decir:

Si una orden tiene tiempo de 1000 milisegundos por pieza y se necesita trabajar 3 piezas, entonces el taller debería ejecutar un:
```java
Thread.sleep(3000);
```
para tardarse exactamente lo que la orden debe tardarse. La simulación de la atención al proceso es simplemente pausar el programa procesador por el tiempo que la orden tenga asociado, no hay que hacer nada más.

Recuerde que para la política Round Robin, la orden se trabaja pieza por pieza. Para las otras dos, la orden se trabaja por completo (se atienden todas las piezas de la orden
de una sola vez).

Después de ser atendida, la orden se elimina de la "cola" y se atiende la siguiente.

## Definición de sus clases:
Para este proyecto se le proveen ciertas clases (las cuales obtendrá al seguir los pasos de la sección [¿Cómo-empiezo?](#cómo-empiezo) ), cuya documentacion puede encontrar [aquí](https://cc2-ug.github.io/cc2-2019-pj1/).
Para la definicion de sus clases debe cumplir con lo siguiente:
 - En este proyecto SE DEBE utilizar herencia, clases abstractas e interfaces.
 - Debe definir tres tipos de órdenes: `SedanOrder`, `BusOrder` y `CoupeOrder`. Se le provee una clase abstracta  `PaintOrder` para que todos sus tipos de órdenes hereden de ella (**TIENEN** que heredar de ella). Las clases que usted defina para esto deben pertenecer al paquete llamado `workshop.orders`. Recuerde que las órdenes además de su id (número de orden), guardan un tiempo de atención por pieza y este es IGUAL para todos las órdenes que sean del mismo tipo. El tiempo de cada orden es definido como argumento a la hora de iniciar la ejecución del programa.
 - Tome en cuenta que para la política Round Robin, la orden se atiende pieza por pieza.
 - Cada una de las clases que representen una política deben heredar de la clase abstracta `Policy` e implementar la interfaz `OrderManager` incluidas en las clases provistas para el proyecto. Todas estas deben pertenecer al paquete llamado `policies`, el cual es subpaquete del paquete `workshop`. Deben ser tres clases que correspondan a las políticas mencionadas anteriormente.
 - Las clases que representen a las políticas mencionadas, deberán ir modificando los campos heredados `currentOrders`, `queuedOrders` y `processedOrders` durante la ejecución del programa. Puede leer en la documentación las descripción de estos campos.  

 - Las clases que extienden a `PaintOrder` DEBEN implementar los métodos abstractos de la siguiente manera:
    - `end()`: Debe cambiar el estado de la orden a DONE. Puede guiarse con el método `waiting()` de la clase `PaintOrder`.
     - `getType()`: Debe devolver *"SEDAN"*, *"MICRO"* o *"COUPE"* para las clases `SedanOrder`, `BusOrder` u `CoupeOrder` respectivamente.
     - `getPrice()`: Si la orden no está TERMINADA, debe devolver "?", de lo contario calcular el precio de la siguiente manera:
         - Para los sedanes el precio será igual a la cantidad de piezas por el precio indicado.
         - Para los microbuses, el precio tendrá un descuento y ese descuento se calcula tomando el tercer dígito de la placa del vehículo. Es decir que si la placa es `"74`**`2`**`FSP"`, el descuento total que se le hará efectivo sería el 2%.     
         - Los vehículos tipo coupé también cuentan con un descuento, y este se calcula usando un número aleatorio entre 4 y 9%. 
         - No nos equivocamos, el precio debe retornarse como String. Ejemplo: si una orden de sedán tuvo 12 piezas y el precio por pieza es de 1000 quetzales, el método debe devolver "12000".
 - Las clases que heredan de `PaintOrder` deben implementar un constructor que reciba TODOS los parámetros que recibe la clase padre en el constructor más los demás parámetros que usted considere particulares para cada una de las subclases. Los constructores por supuesto que deben llamar al constructor de la super clase.
 - Cuando una orden esté en atención, (se esté trabajando), aplicarle el método `paint(pieces)` para indicar que se trabajarán la cantidad de piezas parametrizadas. Puede revisar la documentación, o bien, ese método en las clase `PaintOrder` para analizar qué es lo que hace.
 - **NO** puede ni debe modificar ninguna de las clases e interfaces provistas.
 - Las clases que defina (que no sean políticas o tipos de órdenes), deben pertenecer al paquete `workshop`, no estar dentro de un subpaquete de él.
 - DEBE utilizar pilas y colas para guardar las órdenes. **NO puede utilizar arreglos o ArrayList**.

 - Las clases como estructuras de datos que se le permiten utilizar son: [ConcurrentLinkedQueue<E>](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentLinkedQueue.html) como cola, [Stack<E>](https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html) como pila, [LinkedList<E>](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html) como lista encadenada, proveidas en Java. La estructura de LinkedList solo puede ser utilizada en una política, no más. NO puede utilizar estructuras de datos hechas por usted.
 - NO puede utilizar Menús para la ejecución de políticas.
 - La clase principal: `WorkshopScheduler` no debe pertenecer a ningún paquete.
 - TODAS sus clases deben ir comentariadas de la siguiente forma:
    - En la primera línea debe llevar siempre el nombre del archivo:
    ```java
    /* Order.java */
    ```
    - En la parte de arriba deben llevar nombre del implementador, seccion y carnet:
    ```java
    /**
     ** Hecho por: Juan Pérez
     ** Carnet: 19007891
     ** Sección: A
    **/	
    ```
 - Después debe llevar una breve descripción de para qué sirve la clase.
 - Cada uno de los métodos debe llevar un comentario antes de la declaración en donde diga cómo se llama el metodo, qué parametros recibe y qué devuelve al final y para qué sirve.

## Presentación
Recuerde que usted debe seguir TODAS las especificaciones del proyecto para que no se le 
bajen puntos en su calificación total del mismo. 
He aquí entonces las especificaciones para su presentación:
Su programa se debe poder mandar a ejecutar de las siguientes formas (note que es desde la consola de comandos):
```bash
java WorkshopScheduler -politica(en minusculas) rango_tiempo_ingreso sedan microbus coupe precio
```
En la bandera `-politica` debe ir `-fcfs` para FIRST COME FIRST SERVED, `-lcfs` para LAST COME FIRST SERVED, `-rr` para ROUND ROBIN.

Con respecto a los tiempos: `rango_tiempo_ingreso` es el rango de tiempos dentro del cual se elije aleatoriamente el tiempo en el que se va a ingresar una nueva orden; `sedan` es el tiempo de atención para pieza de sedán; `microbus` es el tiempo de atención para pieza de microbús; `coupe` es el tiempo de atención para pieza de coupé;

```
java WorkshopScheduler -fcfs rango_tiempo_ingreso sedan microbus coupe precio o
java WorkshopScheduler -lcfs rango_tiempo_ingreso sedan microbus coupe precio o
java WorkshopScheduler -rr   rango_tiempo_ingreso sedan microbus coupe precio
```

Ejemplos:
```
java WorkshopScheduler -fcfs  1.5-3  2 1 4 1500
```
Significa que su Workshop Scheduler debe utilizar la politica FIRST COME FIRST SERVED.\
Tiempo de entrada de órdenes debe estar entre 1.5 segundos y 3 segundos.\
El tiempo de atención por pieza para vehículos tipo sedán es 2 segundos.\
El tiempo de atención por pieza para vehículos tipo microbús es 1 segundos.\
El tiempo de atención por pieza para vehículos tipo coupé es 4 segundos.\
El precio por pieza es de 1500 quetzales.

**Si los parámetros son inválidos, indicar esto al usuario y no ejecutar nada más.**

Ya escogida la política, el workshop Scheduler debe empezar el 
simulador e imprimir los datos correspondientes en la pantalla. 
 - Al correr la simulación, su programa debe desplegar en pantalla la siguiente información:
 - La cola de órdenes de entrada:  representando cada orden.
 - Todos los datos de la orden que está siendo atendida.
 - La política que se está utilizando.
 - El número de órdenes ya atendidas (finalizadas) hasta el momento.
 - Cada vez que se dé una acción: Ingreso de orden a la cola, finalizacón de una orden e inicio de atención de una orden, se debe desplegar en pantalla toda la información: ordenes pendientes, órdenes en atención, etc.
 - Ejemplo de despliegue:
```
---------------
Nuevo ingreso! Se ingresó un SEDAN que necesita 3 piezas (correlativo #8)

Política seleccionada: First Come First Served

Actualmente se atiende a: COUPE, 1 pieza (correlativo #5)
Ordenes pendientes: SEDAN 2 piezas (#6) | MICROBUS 4 piezas (#7) | SEDAN 3 piezas (#8)
---------------


---------------
Orden completa! Se completó COUPE que necesitaba 1 pieza (correlativo #5)
Precio original: 1000
Descuento: 5%
Total a pagar: 950

Política seleccionada: First Come First Served

Actualmente se atiende a: SEDAN 2 piezas (correlativo #6)
Ordenes pendientes: MICROBUS 4 piezas (#7) | SEDAN 3 piezas (#8)
---------------
```
 - Ejemplo de despliegue para Round Robin:
```
---------------
Se terminó de trabajar pieza! Se completó pieza 2 de 3 de COUPE (correlativo #11)

Política seleccionada: Round Robin

Actualmente se atiende a: MICROBUS pieza 2 de 4 (correlativo #12)
Ordenes pendientes: MICROBUS 2 de 4 (#12) | SEDAN 1 de 2 (#13) | COUPE 2 de 3 (# 11)
---------------

...

---------------
Orden completa! Se completó pieza 2 de 2 de SEDAN (correlativo #13)
Precio original: 400 por pieza
Cantidad de piezas: 2
Total a pagar: 800

Política seleccionada: Round Robin

Actualmente se atiende a: COUPE pieza 3 de 3 (correlativo #11)
Ordenes pendientes: COUPE 3 de 3 (#11) | MICROBUS 3 de 4 (#12) | SEDAN 0 de 4 (#14)
---------------
```

 - La información debe ser ordenada y legible.
 - Su programa terminará si oprimimos la tecla q. (Puede ser q y ENTER). Y
puede detenerse en cualquier momento de la ejecución. Al detenerse debe imprimir en
la pantalla la informacion de: cuántas órdenes se atendieron, 
cuántas órdenes quedaron en cola (sin atenderse), el tiempo promedio de atencion por orden y la politica utilizada. Para el caso de round-robin solo se deben tomar en cuenta las 
órdenes terminadas completamente, es decir, aquellas cuyas piezas fueron atendidas en su totalidad.

Estas son todas las especificaciones que necesita para realizar el proyecto, si en caso tiene alguna duda 
sobre especificaciones SOLO puede preguntarle a los docentes del curso.

## Entrega de su solución
Su programa debe compilar y correr para poder entregarlo.
Todo su proyecto debe estar en un directorio llamado **pj1**, en el cual debe incluir los
directorios ya hechos de los paquetes (con los paquetes ya compilados).
Completado su directorio, debe agregarlo a un archivo **`pj1-grupo.zip`**
y subirlo al GES.

La estructura de entrega sería la siguiente:
```
pj1
├── workshop
│   ├── orders
│   │   ├── PaintOrder.java
│   │   ├── State.java
│   │   ├── SedanOrder.java
│   │   ├── BusOrder.java
│   │   ├── CoupeOrder.java
│   ├── policies
│   │   ├── Policy.java
│   │   ├── OrderManager.java
│   │   ├── LastComeFirstServed.java
│   │   ├── FirstComeFirstServed.java
│   │   └── RoundRobin.java
│   ├── OTRAS CLASES DEFINIDAS
└── WorkshopScheduler.java
```
Los nombres de las clases correspondientes a las tres políticas pueden ser escogidos por usted.

**No puede entregar su proyecto tarde**

## Puntos Extra
Para poder implementar los puntos extra, debería haber terminado el proyecto completo, NO se meta a hacer puntos extra antes de terminarlo.
 - Ninguna política adicional, se le tomará en cuenta, así que ni lo haga.
 - Puede implementar su proyecto en forma gráfica. (Applets o JFrames)
 - Puede implementar la documentación de Java para sus clases, que se pueda generar con Javadoc (formato API)
 - Cualquier cosa adicional (que no sea otra política) que implemente por su propia cuenta y se considere para puntos extra.

## ¿Cómo empiezo?

Antes de comenzar asegúrense de que hayan leído y comprendido todas las instrucciones del proyecto de principio a fin. Si tienen alguna pregunta pueden diríjanse a Slack y preguntar en los canales correspondientes, solamente si ya han leído completamente este documento.

Tienen que utilizar git y GitHub para este proyecto, ustedes pueden encontrar un tutorial bastante bueno en el siguiente [link](https://rogerdudler.github.io/git-guide/index.es.html). Cada vez que hagan un avance significativo en su proyecto porfavor realicen un commit y realicen un push hacia GitHub.

Para comenzar con el proyecto, primero tienen que tener todos los archivos base, estos se encuentran [aquí](https://classroom.github.com/g/N8wpFaPA). Tienen permitido trabajar en parejas o de forma individual, por lo que al aceptar la asignación les preguntará si desean crear un grupo nuevo o unirse a uno ya existente. Si crean un grupo nuevo, deben enviar un correo a su catedratico (_recuerde incluir en el asunto del correo CC2 y su sección_) para que se les asigne un número de grupo `N` el cual les servira para ingresar el nombre grupo con el formato `grupo-N`, por ejemplo si su número de grupo `2B` deberia ingresar `grupo-2B` como muestra la siguiente imagen:
![Classroom 1](/docs/classroom1.png?raw=true)

Si desean unirse a un grupo ya creado, tienen que buscar el nombre del grupo y pulsar el botón que dice join:
![Classroom 2](/docs/classroom2.png?raw=true)

**Tienen que tener mucho cuidado al unirse a un grupo ya existente, ya que esto no se puede cambiar después, además lo consideraremos como PLAGIO y obtendrá una nota de 0.**

Ya sea que se unan o creen un nuevo grupo, al finalizar el proceso les creará automáticamente un repositorio con una extensión que termina con su nombre de grupo. Ya habiendo hecho todo eso, pueden ejecutar los siguientes comandos abriendo una terminal:

```bash
git clone <link del repositorio>
```
> NOTA: Tienen que reemplazar con el `<link del repositorio>` que se creó.

Considere lo siguiente:
 - Cada vez que hagan un avance significativo en su proyecto porfavor realicen un commit y realicen un push hacia GitHub.
 - Lea completamente las instrucciones del proyecto. Si ya las leyó, puede preguntar.
 - Lea la documentación de las clases dadas.
 - Use Slack para hacer preguntas que no compromentan código.
 - Comience definiendo las clases bajo el paquete `worshop.orders`.
    la definición de las clases tal y como usted ya sabe hasta ahora.
 - Luego defina las políticas que van en el paquete `workshop.policies`. Implemente los métodos necesarios.
 - Ya teniendo definidas las órdenes y políticas, proceda a crear un Thread que se encargue de generar órdenes aleatorias dentro de alguna política, llamando al método respectivo `add(orden)`.
 - HINT: el Thread anterior debe poder guardar solamente una de las tres políticas de atención (pero debe ser capaz de guardar cualquiera).
 - Luego genere un Thread que se encargue de ir atendiendo las órdenes almacenadas en la política (al igual que el anterior, debe poder atender a cualquier política), utilizando los métodos respectivos `remove()` y `next()`.
 - Encárguese de hacer  el programa principal `WorkshopScheduler` que lea los parámetros del usuario e inicialice todo.
 - Piense cómo poder implementar la funcionalidad de terminar la ejecución al presionar q (q + ENTER).

 ¡EXITOS Y MUCHA SUERTE!
 
 --------------------
 `cc2fisicc@galileo.edu`
