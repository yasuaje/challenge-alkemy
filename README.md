# challenge-alkemy
Dependencias:
- MySQL Driver 
- Spring Data JPA
- Spring Web
- Lombok


Descargar el proyecto o clonarlo
Abra la consola y vaya a la carpeta del proyecto.
Run these commands: mvn clean install and then mvn spring-boot:run
\
Para hacer una búsqueda hay que colocar dentro de la url correspondiente el equivalente a 

`
url:puerto/{tipo}?{filtro}={valor}
`
\
Ejemplo
`
localhost:8080/characters?name=Mickey
