run:
	./mvnw spring-boot:run -DskipTests -DskipITs

package:
	./mvnw clean verify