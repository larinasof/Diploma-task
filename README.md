Для запуска автотестов необходимо запустить используемые контейнеры командой `docker-compose up`.

1. Тестирования приложения на СУБД MySQl:
    1. Запустить SUT командой: `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar`
    2. Запустить выполнение тестов в интерфейсе IntelliJ IDEA или в терминале командой `gradlew test`.
