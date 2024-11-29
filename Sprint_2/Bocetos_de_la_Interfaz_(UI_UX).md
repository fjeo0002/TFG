# Bocetos de la Interfaz (UI/UX)

> *Una buena interfaz es como una máscara en un carnaval. Oculta lo mundano y resalta la magia de la experiencia.*
**Creación propia**
> 

Surgen las primeras ideas de interfaces consensuadas con el usuario.

La metodología a seguir será:

1º. Crear ideas a papel y lápiz: tormenta de ideas, todo puede valer. El lápiz es un gran aliado que plasma rápidamente una idea sin perder detalle para hacerla en un visionador de interfaces online. No se descarta nada por el camino, ya habrá tiempo de negar alguna propuesta

2º. Entrevistas con el cliente: llevar a cabo un pequeño encuentro con el usuario e ir baremando las distintas opciones; qué le gusta, qué no, qué ideas puede proponer e indagar acerca de ellas, cómo usa o quiere usar los programas de facturación actuales y no crear nuevos modelos mentales… todo ello nos servirá para acordar y consensuar los detalles que puedan quedar más al aire.

3º. Intentaremos, sobre las ideas finales, crear un pequeño ejemplo con [v0 by Vercel](https://v0.dev/) que se encarga de hacer los mockups de las interfaces más exitosas por medio de IA. Esto hará que nuestro trabajo creando las primeras maquetas de interfaz sea aún más rápido.

# 1º Idea: Sencillez ante todo

Pensamos en la interfaz más simple para generar una factura, tal cual nos ha pedido el cliente. Al comienzo, nos ceñimos a las funcionalidades principales que quiere el usuario: UI intuitiva, fácil de usar, con pocas opciones que dificulten su UX y ventanas pequeñas que no abarquen mucha pantalla. 

![image.png](imgs/image%204.png)

Una interfaz muy simple que contiene lo siguiente:

- Seleccionador de clientes por desplegable
- Introducir códigos de locales diferenciados en BBDD
- Escribir o seleccionar fecha de factura en un calendario.
- Botón para Generar Factura que finaliza el proceso
- Menú con 3 opciones:
    - Clientes: con un submenú para
        - Añadir Cliente: despliega una ventana típica de formulario con btotón para añadir al cliente
        - Eliminar Cliente: despliega una ventana para seleccionar cliente en desplegable y botón para confirmar eliminación
    - Locales: idem con clientes
    - Contabilidad: despliega una ventana que, de forma vertical, muestra los pagos de los clientes y al final de cada columna, si el cliente debe, anticipa o está al día según su cuenta. Estas operaciones se llevan a nivel mensual diferenciando el año en la esquina superior izquierda.

Ahora expondremos cada una de las ventajas e inconvenientes que puede presentar esta UI.

Ventajas

Inconvenientes

1. Interfaz Muy sencilla:
No tiene pérdida para el objetivo del usuario y es intuitiva para realizar operaciones con la generación de documentos y gestión de BBDD

1. Poco diseño y poco profesional: 
A decir verdad, las aplicaciones actuales de escritorio cuentan con otra diversidad de opciones que, aunque ahora mismo sean nuestro enemigo, ahora mismo hacen que la interfaz pueda verse tan pequeña en el escritorio que sea algo parecido a una calculadora de Windows y no sea muy profesional.

1. Menús poco cargados: 
Contamos con un menú que tiene únicamente 3 opciones, con submenús de 2 opciones respectivamente. No sobrecarga la pantalla ni evade la funcionalidad principal.

1. Menús demasiado pequeños:
Los menús están pensados para crear orden cuando hay muchas opciones y poder clasificarlas todas según criterios. ¿Podríamos llevar todos esos menús a botones que hagan la interfaz más profesional ?

1. Demasiada verticalidad: La contabilidad debe rehacerse con otro formato horizontal y empezar a pensar en un escritorio de PC. Aunque sea totalmente intuitivo, llevamos al usuario a hacer scroll innecesario si cambiamos la orientación

1. Separación de funciones: Está bien pensado anotar que un cliente paga, debe o anticipa por dinero que recibimos y hacer una suma total para contabilizarlo. No quiero sobrecargar una pantalla con mucho potencial para mi usuario y quizás el parecerse a un Excel quita la UX que voy buscando.

# 2º Idea: de Windows XP a Windows 11

La UI deja de ser una aplicación de Windows XP pegada a un Excel y empezamos a tomar ideas más profesionales y que la UX aumente considerablemente.

![image.png](imgs/image%205.png)

Mejoras con respecto al anterior diseño:

- Se añade un Plan de Facturación que será el listado de Clientes y Locales asignados para hacer las facturas que corresponden en el mes. Hasta el momento, el usuario cuenta con un PDF aparte que debe abrir externamente siempre que va a facturar e ir fijándose en él para asignar en la factura correpondiente qué cliente tiene alquilado qué local/es. Puede ser buena opción incluir todo dentro de la UI y no depender de archivos externos.
- Se sustituyen los menús de Clientes y Locales por espacios físicos que, aunque ocupen espacio en la pantalla, servirán de ayuda y darán información al cliente sin quitar visibilidad a la funcionalidad principal.
- Se crean pantallas nuevas para Crear Factura/Crear Anticipo que, al principio puede verse como una degradación de importancia dado que no es lo primero que se puede hacer en la aplicación, también puede ofrecer ayudas y mejor UX creando una pantalla exclusiva para esas tareas

![image.png](imgs/image%206.png)

![image.png](imgs/image%207.png)

- Ahora se podrá agregar-modificar-eliminar a los clientes y locales de la gestión respectiva. Está pensado para, no poder accionar Modificar y Eliminar hasta que se seleccione un Cliente/Local de la lista del historial.
- Se da un nuevo diseño a la hora de añadir-modificar a los clientes/locales mucho más profesional (se elimina el centrado y se pasa todo el texto a la izquierda y el botón final a la derecha. Ahora, los labels y TextFields de la pantalla, no se dividen horizontalmente, sino verticalmente)

![image.png](imgs/image%208.png)

- La contabilidad se ha pensado por completo de nuevo siguiendo el diseño anterior. Hemos separado los registros de Anticipos y la Contabilidad en 2 tareas interdependientes (Botones nuevos de Contabilidad y Anticipos)
    - Contabilidad: Ahora, en lugar de ver cifras en todos los campos posibles (12meses+Cuenta_final x n_clientes) se reduce a, en un espaciado horizontal, controlar si:
        - P o ✅ : si ha pagado y le hemos hecho factura
        - D o ❌ : si no ha pagado y la factura está emitida
        - A 🟡 : si ha pagado mediante anticipo y todavía falta por emitir factura
        
        Así, de un sólo vistazo, mi usuario va a controlar quien es moroso, ha anticipado o está al día en las cuentas.
        
    - Anticipos: por otra parte, para un detalle en profundidad de cómo quedan las cuentas en los clientes, se crea el Registro de Anticipos que muestra
    
    | Nombre de Cliente | Fecha (en la que se hizo el Anticipo) | Cantidad o Monto | Meses cubiertos | Saldo restante |
    | --- | --- | --- | --- | --- |
    | Juan Pérez | 01/05/2024 | 1200.00€ | 6 | 600.00€ |
    | Dolores Gutiérrez | 01/09/2024 | 400.00€ | 2 | 0.00€ |

En este pequeño ejemplo, se muestra cómo se gestionan a los clientes dispuestos a hacer anticipos, de tal manera que, al Crear Anticipo, se crea una instancia de fila en el registro. Asimismo, creamos una hucha ficticia para ese anticipo en la que, mes a mes, el saldo restante, por cada mes que se facture al cliente, decrementará hasta llegar a 0, en cuyo caso, el anticipo se agota y es cuando debemos notificar que, el siguiente mes debe cobrarse de nuevo.

![image.png](imgs/image%209.png)

![image.png](imgs/image%2010.png)

Aunque, surgen varias preguntas que después se modelarán con las entrevistas

- ¿Es mejor un botón de Crear Factura/Anticipo que esconda la funcionalidad principal y nos centramos más en su gestión de Locales y Clientes?
- ¿Sería bueno emplear algún tipo de resumen en el Historial de Clientes más que mostrar datos “innecesarios” a la hora de facturar como la localidad, ciudad, código postal…?
- ¿La contabilidad y el registro de Anticipos es más importante que la gestión de Clientes y Locales?
- ¿Es más intuitivo deshabilitar los botones de Modificar/Eliminar en Clientes y Locales a crear una ventana con los datos del Cliente/Local e incluirlos ahí?
- ¿El sistema de Contabilidad es mejor visual (2ºIdea) o matemático (1ºIdea)?
- ¿En el registro de Anticipos, sería conveniente eliminar el anticipo para, aquellos que están cumplidos, irlos desechando y no sobrecargar la pantalla para solo ver los activos?
- A la hora de Crear Factura y Anticipos, ¿es más eficiente (pensando en el usuario) buscar el cliente/local o crear un desplegable y seleccionarlo?

Ahora, veo un momento muy oportuno para, plantear ambas Interfaces y crear un diálogo que pueda aclarar las preguntas que, yo sólo no podré responder.

# Entrevista con el Usuario

Se plantean las 2 ideas previstas hasta el momento y creo un espacio seguro donde pueda, con comunicación verbal y no verbal, darme feedback sobre qué funciona, qué no y así, eliminar lo negativo y mejorar lo positivo.

→ No queda duda de que la segunda idea es significativamente más atractiva para él: Esta propuesta parece alinearse mejor con sus expectativas de una aplicación de gestión moderna y eficiente.

Atrae el hecho de que la interfaz no cuente únicamente con 3 botones-desplegables y prefiere que todo esté ya a mano, dentro de lo posible. 
Expresa gran entusiasmo por la disposición de funcionalidades clave, especialmente la gestión de clientes y locales, así como la visualización mejorada de la contabilidad y los anticipos.

→ Las nuevas ventanas de Creación de Factura y Anticipo es esencial: 

Recupera bien los resúmenes de la factura antes de crearla
Explica que se asemeja mucho (sin documentación previa) a su programa de facturación actual
Fáciles de usar y sencillo de encontrar, aunque pide que resalte algo más dentro de la interfaz.

→ El historial es rediseñado: No quiere información que pueda molestarle y mirar en otro momento (ciudad, codigo postal…). En su lugar, prefiere un pequeño resumen de cada uno de ellos, incluyendo el Estado en el que se encuentra cada uno de ellos y el saldo que tiene en su monedero

→ La contabilidad y registro de anticipos parecen ser grandes promesas:

Quiere más información sobre esa propuesta que, nunca antes se había planteado y le da mucha importancia, incluso más que la gestión de clientes o locales propios
El sistema de contabilidad desea manejarlo independiente del registro de anticipos de una manera más visual y poder aclarar dudas de un vistazo, sin perder más tiempo

→ ¿Buscador o desplegable?

El buscador de los historiales quiere mantenerlos y poder buscar por nombre y código-DNI o nombre. En la generación de facturas y anticipos prefiere un buscador por nombre para los clientes y locales ya que los conoce y sabe en todo momento a quién debe facturar y con qué productos, ayundándose así del nuevo Plan de Facturación.

→ Modificaciones y Eliminaciones: Es más intuitivo para él, dadas las conversaciones, clicar en un cliente o local y habilitar los botones de Modificar o Eliminar, realmente mucho más eficiente que clicar 2 veces en un cliente o local y desplegar una ficha modificable o eliminable.

→ Registro de Anticipos permanente: No quiere borrar los anticipos aunque se cumplan (saldo a 0). Quiere control interno y prefiere remontarse al pasado para observar cómo se efectuó el pago. Habrá que controlar cómo podemos integrar esta parte en la página principal.

→ Poder ver el plan de facturación es un pro, que a priori, no le ha dado demasiada importancia. Se enfoca en otras funcionalidades exóticas que ve con mucha esperanza.

# 3º Idea: Vuelta de Tuerca

Nos acercamos a una UI finalizada y consensuada por el cliente tras la entrevista que cuenta con la UX necesaria y satisface los puntos abordados que no quedaban del todo claro en el anterior diseño.

![image.png](imgs/image%2011.png)

Pequeñas mejoras detalladas con respecto la 2º idea:

- Los botones Crear Factura y Crear Anticipos son más visibles y pasan a un Top de la ventana.

![image.png](imgs/image%2012.png)

![image.png](imgs/image%2013.png)

- Se crean las Preferencias en el menú principal para la gestión de porcentajes
- El plan de facturación, al ser de 2º prioridad para él, se convierte en un botón para ver en una ventana externa y eliminar de la interfaz principal

![image.png](imgs/image%2014.png)

- Creación de categorías “Contabilidad” y “Anticipos” que refrescará parte de la página donde visualizar las páginas descritas anteriormente
    - La contabilidad ha unificado las 2 ideas y ahora es tanto visual como informativo con números y colores tal como se describió.
    
    ![image.png](imgs/image%2015.png)
    
    - El registro podrá ser filtrado según Fecha, Cliente y Actividad o Finalización del Anticipo y no sobrecargar la ventana, problema surgido tras la entrevista.
    
    ![image.png](imgs/image%2016.png)
    

- El buscador ahora cuenta con una búsqueda por Alias tanto para clientes como para locales
    - El historial de Clientes ahora cuenta con 3-4 campos únicamente, y se presentarían de la siguiente manera:
    - Para los locales, muy seguramente ocurra algo parecido

| Nombre | Alias | Estado | Saldo |
| --- | --- | --- | --- |
| Juan Pérez | Juanito | Al día | 0€ |
| Dolores Gutiérrez | Loli | Anticipo | 50.00€ |
| Mario Castellano | Marito | Debe | -20.00€ |
- Se podrá hacer filtrado de clientes por Estado (Al día, Deben, Anticipan).
- Como ya se ha visto, se dan los últimos retoques a pequeños detalles que podrían quedar para finalizar las interfaces.

![image.png](imgs/image%2017.png)