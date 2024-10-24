# Sprint 1: Investigación Inicial

- [x]  Investigación sobre herramientas y tecnologías que utilizarás (frameworks, lenguajes, bases de datos).
- [x]  Definir los requisitos funcionales de la aplicación.
- [x]  Crear una visión del producto y el backlog inicial.

# Investigación sobre Herramientas y Tecnologías

Para la implementación técnica del proyecto, se utilizará Java / Swing. La elección de Java / Swing se debe al conocimiento previo de la plataforma que tiene el estudiante, a la capacidad de Java / Swing para generar interfaces de usuario complejas de forma flexible (usando el IDE y programandolas en código), a que es multiplataforma y a que puede usarse de forma gratuita.

Para la generación y control de la BBDD tengo pensado incluir **Firebase Realtime Database** o **Firebase Firestore** (parte de Firebase, propiedad de Google) como servicio de base de datos en la nube remota, que es gratuito hasta ciertos límites de uso.

- **Ventajas**:
    - **Fácil de usar**: Firebase ofrece SDKs para múltiples plataformas, y con Java puedes integrar Firebase fácilmente.
    - **Base de datos en tiempo real** (Firestore): Muy útil si en el futuro quisieras tener datos sincronizados en tiempo real.
    - **Seguridad**: Firebase gestiona la autenticación, permisos y encriptación de los datos por ti, lo que es ideal para tu necesidad de privacidad y seguridad.
    - **Escalabilidad**: Empiezas con un plan gratuito que te permite crecer a medida que aumente tu tráfico.

Adicionalmente, incluiré **Herramientas de gestión y desarrollo** con las que estoy familiarizado y me darán apoyo en la realización del proyecto:

- **Notion**: Utilizado para la recopilación y organización de ideas, planificación de sprints y seguimiento del progreso.
- **GitHub**: Control de versiones y repositorio principal del código fuente, permitiendo la gestión del ciclo de vida del desarrollo.

# Definir requisitos funcionales de la Aplicación

Desarrollaré una metodología de gestión técnica de priorización utilizada para clasificar las funcionalidades o requisitos en función de su importancia llamada **MoSCoW.**

[MoSCoW](MoSCoW.md)

1. Crear lista requisitos funcionales
- [ ]  Generación de Facturas: Crear, guardar y gestionar facturas.
- [ ]  Gestión de Clientes: Crear, Leer, Actualizar, Eliminar BBDD de Clientes
- [ ]  Gestión de Locales: Crear, Leer, Actualizar, Eliminar BBDD de Locales
- [ ]  Cálculo automático de Impuestos y Totales: Cálculos de porcentajes de IVA y Retenciones
- [ ]  Exportación de facturas a PDF: Es necesario crear la factura en formato PDF para su posible manipulación (Enviar, Imprimir…)
- [ ]  Almacenamiento automático de Facturas: Al crearla, esta debe guardarse en una ruta específica dada por mi cliente y no podrá ser manipulada.
- [ ]  Recordatorio para facturar: A principios de mes siempre se factura, así que sería conveniente dar notificaciones cuando se acerque la fecha
- [ ]  Envío de facturas por correo electrónico desde la aplicación: El usuario va a tener que mandar las facturas a sus clientes. Estaría bien poder automatizar también ese proceso
- [ ]  Multimoneda: Está pensada para pequeñas PYMES. Será todo en €.
- [ ]  Contabilidad Individual: Sería conveniente llevar anotado cuando el cliente paga o si tiene meses por pagar. Parecido a un Historial de Clientes.
- [ ]  Resumen anual/mensual de ingresos: Informe de facturas pagadas/pendientes personal y resumen mensual de contabilidad
- [ ]  Integración de múltiples opciones: pueden ser laboriosas para los autónomos que, en un principio, no les haya echado en falta.
- [ ]  Almacenamiento seguro de datos: Surge la necesidad de proteger los datos financieros de clientes con seguridad básica en la BBDD
- [ ]  Control de Anticipos: en correlación con la contabilidad Individual, se podría barajar la posibilidad de controlar monederos de cuentas pagadas/pendientes por cliente por si alguno te hace ingresos de los próximos ‘x’ meses
    - Tendría que generar una especie de recibo notificando al cliente que he recibido la cantidad correspondiente y, de su monedero virtual, cada mes que se factura, ir quitando dinero de su cuenta y ver si me debe dinero o, genéricamente, ‘x-n’ meses.
1. Asignar Prioridades MoSCoW

| Must Have | Should Have | Could Have | Won’t Have |
| --- | --- | --- | --- |
| Generación de Facturas | Recordatorio para facturar | Resumen anual/mensual de ingresos | Multimoneda |
| Gestión de Clientes | Envío de facturas por correo electrónico desde la aplicación | Control de Anticipos  | Integración de múltiples opciones |
| Gestión de Locales | Contabilidad Individual |  |  |
| Cálculo automático de Impuestos y Totales |  |  |  |
| Exportación de facturas a PDF |  |  |  |
| Almacenamiento automático de Facturas |  |  |  |
| Almacenamiento seguro de datos |  |  |  |

# Crear visión de producto y Backlog inicial

La **visión del producto** es una descripción clara y concisa que define el propósito general del proyecto y los objetivos principales. Ayuda a establecer una dirección para el desarrollo y sirve como una brújula a lo largo del proyecto. La visión debe responder a preguntas como:

- ¿Qué problema resuelve?
- ¿Quiénes son los usuarios objetivo?
- ¿Cómo mejorará la vida de los usuarios?

Para tu proyecto de facturación, la **visión del producto** podría centrarse en los beneficios que tu aplicación ofrece a las pequeñas PYMES y autónomos. El resultado final podría ser

## Visión del Producto:

"Crear una aplicación de facturación eficiente, sencilla y automatizada para pequeñas PYMES y autónomos, que les permita gestionar sus facturas, clientes y contabilidad de forma rápida y segura, sin la necesidad de conocimientos técnicos. La aplicación proporcionará características claves como la generación automática de facturas, almacenamiento seguro y reportes mensuales/anuales, con el objetivo de simplificar el proceso administrativo y liberar tiempo para que los usuarios se concentren en hacer crecer sus negocios."

El **backlog inicial** es una lista priorizada de todas las funcionalidades, mejoras y arreglos que se van a implementar en la aplicación, con base en tus requisitos funcionales y la priorización que has hecho con MoSCoW.

Al crear el **Product Backlog**, cada ítem debe describirse como una **historia de usuario**. Las historias de usuario son descripciones simples de una característica desde la perspectiva del usuario final. Se escriben en un formato específico: *“Como [tipo de usuario], quiero [funcionalidad], para que [valor o beneficio].”*

Aquí tienes un ejemplo de cómo podrías estructurar tu backlog inicial usando las historias de usuario basadas en las tareas priorizadas:

### **Backlog inicial:**

1. **Generación de Facturas**
    
    *Historia de Usuario*: Como usuario, quiero generar una factura con los datos del cliente y del servicio/producto ofrecido, para poder entregársela a mi cliente de manera formal.
    
2. **Gestión de Clientes**
    
    *Historia de Usuario*: Como usuario, quiero crear y gestionar una lista de clientes, para poder tenerlos organizados y acceder rápidamente a sus datos para emitir facturas.
    
3. **Gestión de Locales**
    
    *Historia de Usuario*: Como usuario, quiero crear y gestionar una lista de locales-trasteros-cocheras… para poder tenerlos organizados y acceder rápidamente a sus datos para emitir facturas.
    
4. **Cálculo Automático de Impuestos y Totales**
    
    *Historia de Usuario*: Como usuario, quiero que el sistema calcule automáticamente los impuestos y totales, para evitar errores y ahorrar tiempo en la facturación.
    
5. **Exportación de Facturas a PDF**
    
    *Historia de Usuario*: Como usuario, quiero exportar mis facturas a formato PDF, para enviarlas o imprimirlas según sea necesario.
    
6. **Almacenamiento Automático de Facturas**
    
    *Historia de Usuario*: Como usuario, quiero que las facturas se guarden automáticamente en una ruta específica, para tenerlas organizadas y almacenadas sin esfuerzo manual.
    
7. **Almacenamiento Seguro de Datos**
    
    *Historia de Usuario*: Como usuario, quiero que los datos de clientes y facturas estén protegidos, para asegurar la privacidad y evitar accesos no autorizados.