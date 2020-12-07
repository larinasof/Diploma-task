Для запуска автотестов необходимо запустить используемые контейнеры командой `docker-compose up`.

1. Тестирования приложения на СУБД MySQl:
    1. Запустить SUT командой: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`.
    2. Запустить выполнение тестов командой `gradlew clean test -Dspring.datasource.url=jdbc:mysql://localhost:3306/app`.
    3. Посмотреть отчет о тестировании, выполнив следующие команды: `gradlew allureReport`, `gradlew allureServe`.
    4. Закрыть контейнеры командой `docker-compose down`.

2. Тестирование приложения на СУБД PostgreSQL:
    1. Запустить SUT командой: `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar`.
    2. Запустить выполнение тестов командой `gradlew clean test -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app`.
    3. Посмотреть отчет о тестировании, выполнив следующие команды: `gradlew allureReport`, `gradlew allureServe`.
    4. Закрыть контейнеры командой `docker-compose down`.



