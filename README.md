# Freelance Platform

Das Ziel dieses Projekts bestand darin, einen kleinen Teil einer Freelancer Platform umzusetzen (eg. Upwork). Im Service sollen zwei Usertypen existieren, und zwar Freelancer und Kunde. Der Service soll nach Anfrage Informationen über Freelancer, deren Projekte und aktuelle Verträge zur Verfügung stellen.

Übersicht über die verwendeten Technologien:
- Programmiersprache: Java 17
- Framework: Micronaut
- Persistenz: Postgres, Hibernate
- DTO mapping: Mapstruct
- Boilerplate Codegenerierung: Lombok
- Build-Werkzeug: Gradle, Jib
- Deployment: Kubernetes Cluster im Docker Desktop, PowerShell Skripts zur Automatisierung des Deployment Prozesses
- Observability: Dynatrace, Jaeger

### Architektur der Lösung

Die Lösung besteht aus einem einzigen Service. Der Service ist nach einem typischen Muster der Servicearchitektur implementiert und hat folgende Schichten:
- Controller Schicht (inklusive DTO Mapping)
- Service Schicht (Business Logik der Anwendung)
- Repository Schicht (Kommunikation mit der Datenbank)
Datenbank

![1_architecture](/images/1_architecture.jpg)

Am Beispiel des Freelancers, sieht das UML-Diagramm mit den Verbindungen zwischen Klassen wie folgt aus:

![2_uml](/images/2_uml.png)

Diesen Klassen gehören folgende Attribute:

![3_uml](/images/3_uml.png)

Im Service wurden drei Controller implementiert, und zwar ein Controller für Operationen mit Freelancern, einer für Kunden und einer für Projekte. Jeder der Controller stellt Endpoints zur Verfügung, um die jeweiligen Entitäten im System erstellen und abfragen zu können:

| Method | Routes | Description |
| -------- | -------- |-------- |
| POST |/client| Einen Kunden erstellen|
| GET |/client/{id}| Einen Kunden abfragen|
| POST |/freelancer| Einen Freelancer erstellen|
| GET |/freelancer/{id}| Einen Freelancer abfragen|
| POST |/project| Ein Projekt erstellen|
| GET |/project/{id}| Ein Projekt abfragen|

`POST http://localhost:8080/freelancer`
```json
{
  "id": 1,
  "firstName": "first",
  "secondName": "second",
  "nickName": "nick",
  "email": "a@a.a"
}
```
`POST http://localhost:8080/client`
```json
{
  "id": 1,
  "firstName": "first",
  "secondName": "second",
  "email": "a@a.a"
}
```
`POST http://localhost:8080/project`
```json
{
  "id": 1,
  "name": "proj",
  "client": {
    "id": 1,
    "firstName": "first",
    "secondName": "second",
    "email": "a@a.a"
  },
  "freelancers": [
    {
      "id": 1,
      "firstName": "first",
      "secondName": "second",
      "nickName": "nick",
      "email": "a@a.a"
    }
  ]
}
```

### Datenmodell

Das Datenmodell ist durch drei Entitäten abgebildet. Wie man auf dem Bild unten sieht, können einem Freelancer mehrere Projekt gehören und mehrere Freelancer können an einem Projekt beteiligt werden. Wohingegen ein Projekt maximal einem Kunden gehören darf, kann ein Kunde mehrere Projekt haben. Die erwähnten Verbindungen zwischen Entitäten werden in den Response JSONs entsprechend abgebildet: Wenn ein Freelancer mittels HTTP GET Methode abgefragt wird, bekommt man als Ergebnis Informationen über den Freelancer und die Liste von Projekten, an denen er beteiligt ist (genauso funktioniert es für einen Kunden). Bei der Abfrage eines Projektes wird ein Projekt mit den Informationen über den Kunden und die Liste der beteiligten Freelancers zurückgegeben.

![4_data_model](/images/4_data_model.jpg)

### Kurzer Überblick über Technologien

### Mapstruct

Mithilfe von Mapstruct können DTOs (Data Transfer Object) auf Business Entitäten gemappt werden. Diese Technik führt eine zusätzliche Entkopplungsschicht zwischen dem wie Daten nach Außen repräsentiert werden und wie sie intern im System abgebildet werden. So können Business Entitäten ohne jegliche Einflüsse auf die Clients des Services geändert werden.

```java
@Mapper(componentModel = "jakarta", uses = NestedMapper.class)
public interface ProjectMapper {
    ProjectDto toDto(Project entity);

    NewProject toEntity(NewProjectDto dto);
}
```

Im Freelancer Platform Service hat jede Entität ihren eigenen Mapper. Da ein zyklischer Zusammenhang zwischen Projekten und Freelancern (so wie zwischen Projekten und Kunden) besteht, wurde ein zusätzlicher Mapper eingeführt: NestedMapper. Dieser Mapper bricht den rekursiven Zusammenhang Projekt-Freelancer und Projekt-Kunde.

```java
@Mapper(componentModel = "jakarta")
public interface NestedMapper {

    @Mapping(target = "projects", ignore = true)
    FreelancerDto toDto(Freelancer entity);

    @Mapping(target = "freelancers", ignore = true)
    ProjectDto toDto(Project entity);

    @Mapping(target = "projects", ignore = true)
    ClientDto toDto(Client entity);
}
```

### Lombok

Lombok dient zur Erzeugung des Boilerplate Codes. Eine vergleichbare Funktionalität bieten record Klassen, die in Java seit Version 14 (im Preview Modus) verfügbar sind. Allerdings wurde die Entscheidung getroffen doch Lombok zu wählen, zumal viele Java Bibliotheken, die sich auf Reflektion bzw. Codegenerierung verlassen, damit rechnen, dass es in Klassen Getter und Setter Methoden gibt. Getter und Setter Methoden in record Klassen beginnen nicht mit “get” und “set”, was im Falle des vorliegenden Projekts zu einem Konflikt bei Hibernate und Mapstruct geführt hat. Lombok wurde sowohl bei DTO, als auch bei Entität Klassen verwendet.

```java
@Data
@Introspected
public class ClientDto {
    Long id;

    @NotBlank
    String firstName;

    @NotBlank
    String secondName;

    @Email
    String email;

    List<ProjectDto> projects;
}
```

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = DB, schema = TABLE_NAMESPACE, name = Client.CLIENT)
public class Client
```

### Packaging für Deployment

Als Packaging für den Service wurde nicht überraschend Docker und als unterstützendes Werkzeug Jib gewählt. Jib ist ein Werkzeug zur Containerisierung der Anwendungen von Google und kann sehr einfach mit Build-Werkzeugen wie Maven und Gradle in CI/CD Pipelines verknüpft werden. Jib hat den Prozess der Erstellung der Images signifikant vereinfacht. Ohne Jib hätte der Arbeitsablauf (Workflow) folgendermaßen ausgeschaut:

![5_without_jib](/images/5_without_jib.jpg)

Wie es aus dem Bild oben ersichtlich ist, werden für die Erstellung eines Images ohne Jib folgende Schritte benötigt:
- Quellcode bauen
- Dockerfile erstellen
- Aus dem Dockerfile und der gebauten Jar Datei mittels Docker ein Container Image erstellen
- Container Image in Container Registry pushen

Im Gegensatz zum vorigen Workflow vereinfacht Jib den Prozess drastisch:

![6_with_jib](/images/6_with_jib.jpg)

Jib übernimmt die Konfiguration für den Bau des Containers und für dessen Upload aus build.gradle. Jib relevanter Teil des build.gradle:

```groovy
jib {
    from {
        image = "openjdk:17.0.2-jdk"
    }
    to {
        image = "docker.io/stakkato95/freelance-platform"
        tags = [version, 'latest']
    }
    container {
        mainClass = "${group}.Application"
        jvmFlags = ["-Xms256m", '-Xdebug']
        ports = ['80']
        volumes = ['/data']
    }
}
```

Befehle, die für den Bau und Upload benötigt werden:

```bash
gradle jibDockerBuild # erstellt ein Image
gradle jib # lädt das Image in Container Registry

```

Als Container Registry wurde Docker Hub verwendet.

![7_dockerhub](/images/7_dockerhub.png)

### Deployment

Als eine einfache Deployment Umgebung für Test Zwecke wurde ein lokaler Kubernetes Cluster von Docker Desktop gewählt. Das Deployment der Freelancer Platform erfolgt mittels eines PowerShell Skripts (kein helm) in zwei Schritten: Zuerst wird Postgres und anschließend das Freelancer Platform Deployment ausgerollt. Um den Zugriff auf Kubernetes Pods sowohl innerhalb, als auch außerhalb des Clusters zu gewährleisten, werden zusätzlich Kubernetes Services erstellt. Dadurch wurden folgende Vorteile erzielt:

- Mittels “port” in der Service Definition erhalten Pods statische DNS Adressen innerhalb eines Kubernetes Clusters und können von anderen Pods auch dann erreicht werden, wenn ein Pod runterfährt und durch einen anderen Pod (mit einer neuen IP Adresse) ersetzt wird.
- Mittels “nodePort” in der Service Definition wird ein Pod auf dem Kubernetes Knoten, wo der Pod läuft, durch einen statisch definierten Port nach Außen ausgesetzt .

```yaml
ports:
  - nodePort: 32222
    port: 8080
    targetPort: platform-port
    protocol: TCP
```

Im Falle dieses einfachen Projekts wird sowohl die Platform, als auch Postgres, mittels “nodePort” ausgesetzt. Die Platform wird ausgesetzt, um darauf überhaupt zugreifen und die Funktionalität testen zu können, wohingegen Postgres nur für den lokalen Entwicklungsprozess ausgesetzt wird, d.h. man startet das Platform Projekt in Intellij und greift auf die Postgres Datenbank, die im Kubernetes Cluster läuft, zu.

Platform Pod stellt liveness und readiness Probes zur verfügung.

```yaml
readinessProbe:
    failureThreshold: 3
    initialDelaySeconds: 10
    httpGet:
        path: /health/readiness
        port: 8080
livenessProbe:
    failureThreshold: 3
    initialDelaySeconds: 20
    httpGet:
        path: /health/liveness
        port: 8080
```

### Monitoring

Für Monitoring wurden zwei Werkzeuge ausprobiert:
- Dynatrace
- Jaeger

Beide dieser Monitoring Werkzeuge wurden als Kubernetes Operatoren im Cluster gestartet. Ein Kubernetes Operator entlastet einen menschlichen Operator, indem er wiederkehrende Operationen automatisch ausführt. In diesem Fall ist es die Erstellung der Sidecars, die Agenten enthalten und die für Monitoring Zwecke verwendet werden. Sidecar ist eine Abstraktion aus Kubernetes und bezeichnet einen Container, der im selben Pod mit einem anderen Container läuft und seine Funktionalität ergänzt. Agent ist ein Programm, das Metriken / Informationen über ein anderes Programm sammelt.

Dynatrace und Jaeger wurden nach folgenden Kriterien verglichen:

Einfachheit des Deployments:

-  Dynatrace. Das Deployment des Dynatrace Operators im Kubernetes Cluster erfordert eine minimale Anzahl an Schritten und Befehlen, die ausgeführt werden müssen. Auffallend beim Deployment war, dass Dynatrace eine gewisse Zeit in Anspruch nimmt, um die eingespeiste Metriken zu verarbeiten. So sieht man Daten in Dynatrace erst 5-10 Minuten später, nachdem der Dynatrace Operator im Cluster erstellt wurde. Das Bild unten zeigt die Seite, wo man die Verbindung mit einem Kubernetes Cluster erstellen kann (das erfordert nur 4 Kubernetes Befehle).

![8_dynatrace_deployment](/images/8_dynatrace_deployment.png)

- Jaeger. Bevor man Jaeger auf einem Kubernetes Cluster installiert, muss man zuerst in die Dokumentation eintauchen. Das Deployment ist in mehrere Schritte unterteilt, die einzeln ausgeführt werden müssen. Ein unerfahrener Kubernetes User kann dafür einige Stunden brauchen. Außerdem werden Pods nicht automatisch gemonitored, wie es bei Dynatrace der Fall ist. Die Pods, die gemonitored werden sollen, müssen mit der Annotation "sidecar.jaegertracing.io/inject": "true" markiert werden. Außerdem muss berücksichtigt werden, in welchem Modus Jaeger installiert wird (Cluster / Non Cluster Mode). Der Modus bestimmt, ob Jaeger auf alle oder nur auf bestimmte Namespaces im Kubernetes Zugriff haben soll.

Art des Deployments:

- Dynatrace. Für die Experimente mit Dynatrace wurde ein Trial Account angelegt.

- Jaeger. Für das lokale Deployment von Jaeger wurde ein PowerShell Skript geschrieben, welches alle Deployment Schritte automatisch ausführt und Jaeger UI an localhost:16686 anbindet.

![9_jaeger_port_forward](/images/9_jaeger_port_forward.png)

Datenerhebung und Datenvollständigkeit:

- Dynatrace. Out of the Box kann Dynatrace ziemlich alle Metriken und zusätzliche Informationen, die ein Entwickler für die Analyse des Verhaltens eines Services braucht, sammeln und miteinander korrelieren lassen. Traces können ohne jeglicher Änderungen in der Anwendung erhoben werden. Zusätzlich zu den Traces (Laufzeitmessungen für Aufrufe der REST Endpoints), kann Dynatrace feststellen, welche Anfrage an die Datenbank geschickt wurde. Das kann sehr praktisch sein, wenn man versucht festzustellen, welche Datenbankoperation einen REST Aufruf langsam macht. Um die Analyse noch einfacher zu machen, stellt Dynatrace Callstacks bei einer REST Anfrage dar.

- Jaeger. Jaeger kann REST Traces automatisch sammeln. Alle Methoden, die mit Traces genauer analysiert werden sollen, müssen im Falle Micronaut mit Annotationen markiert werden (entweder `@NewSpan` oder `@ContinueSpan`), sonst sammelt Jaeger nur die Gesamtlaufzeit einer REST Anfrage (ohne Zeitmessungen für die Aufrufe einzelner Methoden). Der Versuch ein Sidecar Container neben einem Postgres Container zu deployen hat nicht geholfen, die Laufzeit eines Datenbankzugriffs zu messen (zur Erinnerung: Dynatrace kann das automatisch). Auf den Bildern unten kann man vergleichen, wie Jaeger und Dynatrace Informationen über eine POST Anfrage darstellen. Bei Dynatrace sind gesammelte Informationen umfangreicher und werden auf unterschiedlichen Seiten abgebildet.

![10_jaeger_ui](/images/10_jaeger_ui.png)

![11_dynatrace_1](/images/11_dynatrace_1.png)

![12_dynatrace_2](/images/12_dynatrace_2.png)

![13_dynatrace_3](/images/13_dynatrace_3.png)

Struktur der Verzeichnisse:
- k8s/dynatrace: Skripts für das Deployment von Dynatrace
- k8s/jaeger: Skripts für das Deployment von Jaeger
- k8s/platform: Skripts für das Deployment der Plattform zusammen mit Postgres und für das Deployment der Postgres Datenbank alleine (zwecks lokaler Entwicklung).

### Fazit

Im vorliegenden Projekt wurde Folgendes ausprobiert:
- ein technology Stack für die Entwicklung von Microservice, und zwar Micronaut als Application Framework, Mapstruct als Bibliothek für DTO Mapping, Lombok als Bibliothek für Boilerplate Codegenerierung
- eine Kombination von Werkzeugen zur Automatisierung des Entwicklungsprozesses, und zwar Jib als ein Werkzeug zur Containerisierung von Anwendungen, Kubernetes als Application Laufzeitumgebung
- zwei Services zur Analyse des Laufzeitverhaltens der Anwendungen, und zwar Dynatrace und Jaeger. Diese wurden als Kubernetes Operatoren in der lokalen Docker Desktop Umgebung deployed







