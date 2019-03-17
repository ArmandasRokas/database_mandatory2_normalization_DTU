

## Delopgave1

Først og fremmest blev koden sikret mod SQL Injections ved at bruge PreparedStatements, som håndterer brugerens input som parameter og ikke som en forespørgsel. Altså tillades der ikke at køre en anden forespørgsel end der blev defineret i PreparedStatement.

Der blev også brugt CASCADE DELETE for at undgå Foreign key constraint violation, når der skal slettes en bruger fra databasen. Dette kunne komme, hvis der prøves at slettes en bruger fra users tabel, før der blev slettet brugerens roller. CASCADE gør det automatisk, dvs. fjerner brugeren både fra users tabel og roller tabeller samtidigt. 

Til sidst blev der tilføjet en kombineret Primary key til roles tabellen, som bistår af user_id og rolle, hvilket forhindrer at have dubletter i relationen.  

Det eneste, der bekymrer mig, er, at der blev ikke specificeret, hvilke roller der eksisterer. Det, jeg mener, er at der kan opstå inkonsistente data, hvis der f.eks. bruges forskellige betegnelser for den samme rolle. Så på det tidspunkt, når der skal findes ud af, f.ek.s hvor mange bruger der har en eller anden rolle, kan der opstå problemer.  


## Delopgaven2

![alt text](/diagram_jpg/delopgaven2.jpg "Title")

