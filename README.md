# RecomendadorWeb
Este es un recomendador web desarrollado con springboot en la Universidad Complutense de Madrid. Continua en desarrollo
. Yo Pablo Saiz soy su autor principal ya que escribí o ayudé a escribir la totalidad del código del proyecto.
El repositorio original lo tengo privado.
## Patrones de diseño usados
- Converter
- MVC
- DTO
- Repository
- Façade
- Strategy
- Factory
## ¿En que se basa el recomendador?
Este recomendador se basa en el paper de Robin Burke sobre recomendadores híbridos y sus estrategias de recomendación
## Uso actual
Falta pulir bastante todavía.
### Creación de usuarios
Crear usuarios con longitud de 8 letras y contraseñas de 8 letras o más ambos.
### Recomendaciones
#### Sin loguear
La recomendación sin loguearse toma las estrategias de mayor puntuación y basada en conocimiento. 
#### Logueada
La logueada toma en cosideración las reviews de usuarios parecidos y la estrategia basada en conocimiento
