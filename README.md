## Proyecto BASE JSF

**Tecnología**

* Spring 4
* PrimeFaces 5.1
* Mybatis 3.3.0
* JDK8

**Modulos**

El proyecto consta de un módulo web que depende de dos jar:

* **proyecto-base-jsf-web** (war) - Front-end de la aplicacion
* **proyecto-base-jsf-back** (jar) - consulta BBDD / generacion informes / envío de mails... 


**Configuración Generación war**

Se puede modificar la configuración depediendo del entorno **desa** y **prod** 

* Configuración entorno desarrollo proyecto-base-jsf-web/src/main/resources/env/**desa.properties**

	mvn clean install -Pdesa

* Configuración entorno producción proyecto-base-jsf-web/src/main/resources/env/**prod.properties**
	
	mvn clean install -Pprod
	
Copian la configuracion en los properties que utiliza la aplicacion

* Configuración general - proyecto-base-jsf-web/src/main/resources/**environment.properties**
* Logs - proyecto-base-jsf-web/src/main/resources/**log4j.properties**

**Documentación**

[Primefaces documentación](https://www.primefaces.org/documentation/)
