\# 📦 Módulo de Inventario Hospitalario



\## Descripción



Este módulo corresponde a la implementación del sistema de inventario hospitalario, orientado a gestionar las operaciones principales del almacén mediante el registro de movimientos de entrada y salida, actualización automática del stock disponible, consulta del historial de operaciones y control de productos con niveles bajos de inventario.



El desarrollo fue realizado utilizando \*\*Spring Boot\*\*, \*\*Spring Data JPA\*\*, \*\*MySQL\*\* y \*\*Thymeleaf\*\*, siguiendo una arquitectura basada en capas para separar correctamente la lógica de presentación, negocio y persistencia.



\---



\# 👨‍💻 Autor



\*\*Noe Oswaldo Escobal Huayana\*\*



\## Aporte realizado



Implementación del módulo operativo de inventario:



\- Registro de entradas de productos.

\- Registro de salidas de productos.

\- Actualización automática del stock.

\- Historial de movimientos.

\- Validación de stock insuficiente.

\- Manejo global de excepciones.

\- Alertas de stock bajo.

\- Interfaces web para la gestión del inventario.



\---



\# 🏗️ Arquitectura implementada



El módulo fue desarrollado utilizando una arquitectura por capas:



```

Usuario

\&#x20;  |

\&#x20;  v

Interfaz Web / API REST

\&#x20;  |

\&#x20;  v

Controller

\&#x20;  |

\&#x20;  v

Service

\&#x20;  |

\&#x20;  v

Repository

\&#x20;  |

\&#x20;  v

Base de Datos

```



\## Controller



Gestiona las solicitudes realizadas por el usuario y permite la comunicación entre la interfaz y la lógica del sistema.



Controladores implementados:



```

InventarioWebController

MovimientoInventarioController

```



Responsabilidades:



\- Mostrar información del inventario.

\- Registrar entradas.

\- Registrar salidas.

\- Consultar movimientos.

\- Obtener historial por producto.



\---



\## Service



Contiene la lógica principal del módulo de inventario.



Clase implementada:



```

MovimientoInventarioService

```



Responsabilidades:



\- Incrementar stock al registrar entradas.

\- Reducir stock al registrar salidas.

\- Validar disponibilidad antes de realizar una salida.

\- Registrar movimientos asociados a cada producto.



\---



\## Repository



Permite la comunicación con la base de datos mediante Spring Data JPA.



Repositorios implementados:



```

ProductoRepository

MovimientoInventarioRepository

```



Funciones principales:



\- Consultar productos.

\- Consultar movimientos.

\- Buscar historial por producto.

\- Detectar productos con stock bajo.



\---



\# 🔄 Funcionalidades implementadas



\## 1. Registro de entrada de productos



Permite aumentar la cantidad disponible de un producto dentro del inventario.



Flujo:



```

Usuario

\&#x20;  |

Selecciona producto

\&#x20;  |

Ingresa cantidad

\&#x20;  |

Actualiza stock

\&#x20;  |

Registra movimiento ENTRADA

```



Ejemplo:



```

Stock inicial: 20 unidades



Entrada registrada: +10 unidades



Stock final: 30 unidades

```



\---



\# 2. Registro de salida de productos



Permite retirar productos del inventario verificando previamente la disponibilidad.



Flujo:



```

Usuario

\&#x20;  |

Selecciona producto

\&#x20;  |

Ingresa cantidad

\&#x20;  |

Validar stock disponible

\&#x20;  |

Actualizar stock

\&#x20;  |

Registrar movimiento SALIDA

```



Ejemplo:



```

Stock inicial: 50 unidades



Salida registrada: -15 unidades



Stock final: 35 unidades

```



\---



\# 3. Validación de stock insuficiente



Se implementó una validación para evitar que el inventario tenga valores negativos.



Ejemplo:



```

Stock actual: 10 unidades



Salida solicitada: 50 unidades

```



Resultado:



```

Error:

No existe stock suficiente para realizar la salida

```



Para controlar este escenario se implementaron:



```

StockInsuficienteException

```



y



```

GlobalExceptionHandler

```



permitiendo devolver mensajes claros al usuario.



\---



\# 4. Historial de movimientos



Cada operación realizada queda registrada con la siguiente información:



\- Producto asociado.

\- Tipo de movimiento.

\- Cantidad.

\- Fecha.

\- Observación.



Tipos de movimiento:



```

ENTRADA

SALIDA

```



Ejemplo:



| Producto | Tipo | Cantidad |

|---|---|---|

| Laptop Lenovo | ENTRADA | 20 |

| Laptop Lenovo | SALIDA | 5 |



\---



\# 5. Alertas de stock bajo



Se implementó una consulta para identificar productos cuyo stock actual sea menor o igual al stock mínimo configurado.



Regla aplicada:



```

stockActual <= stockMinimo

```



Esta funcionalidad permite identificar productos que requieren reposición.



\---



\# 🌐 Interfaces desarrolladas



\## Vista principal de inventario



Ruta:



```

/inventario

```



Permite visualizar:



\- Lista de productos.

\- Stock actual.

\- Historial de movimientos.

\- Alertas de inventario.



\---



\## Registro de entrada



Ruta:



```

/inventario/entrada

```



Permite registrar ingresos de productos al almacén.



\---



\## Registro de salida



Ruta:



```

/inventario/salida

```



Permite registrar retiros de productos verificando disponibilidad.



\---



\# 🔌 Endpoints implementados



\## Registrar entrada



```

POST /api/inventario/entrada

```



Parámetros:



```

productoId

cantidad

observacion

```



\---



\## Registrar salida



```

POST /api/inventario/salida

```



Parámetros:



```

productoId

cantidad

observacion

```



\---



\## Consultar movimientos



```

GET /api/inventario/movimientos

```



\---



\## Historial por producto



```

GET /api/inventario/movimientos/{productoId}

```



\---



\# 🛠️ Tecnologías utilizadas



| Tecnología | Uso |

|---|---|

| Java | Desarrollo backend |

| Spring Boot | Framework principal |

| Spring MVC | Controladores web |

| Spring Data JPA | Persistencia de datos |

| Hibernate | ORM |

| MySQL | Base de datos |

| Thymeleaf | Interfaces web |

| Bootstrap | Diseño visual |

| Postman | Pruebas API |



\---



\# 📁 Estructura del módulo



```

src/main/java/com/hospital/inventario



├── controller

│   ├── InventarioWebController.java

│   └── MovimientoInventarioController.java

│

├── model

│   └── MovimientoInventario.java

│

├── repository

│   ├── ProductoRepository.java

│   └── MovimientoInventarioRepository.java

│

├── service

│   └── MovimientoInventarioService.java

│

└── exception

\&#x20;   ├── GlobalExceptionHandler.java

\&#x20;   └── StockInsuficienteException.java

```



\---



\# 📌 Commit asociado



Implementación realizada mediante:



```

Actualiza modulo de inventario hospitalario - ESCOBAL

```



\---



\# ✅ Estado del módulo



\- ✅ Registro de entradas implementado.

\- ✅ Registro de salidas implementado.

\- ✅ Actualización automática de stock.

\- ✅ Historial de movimientos funcional.

\- ✅ Validación de stock insuficiente.

\- ✅ Manejo de excepciones.

\- ✅ Alertas de stock bajo.

\- ✅ Interfaz web funcional.



\---



\# Conclusión



El módulo desarrollado permite gestionar las operaciones principales de un inventario hospitalario, proporcionando control sobre las entradas y salidas de productos, actualización automática del stock y trazabilidad mediante el historial de movimientos.



La implementación permitió aplicar conocimientos de desarrollo backend con Spring Boot, persistencia mediante JPA, diseño de arquitectura por capas y construcción de interfaces web, obteniendo una solución escalable que puede ampliarse con nuevas funcionalidades en futuras versiones.

