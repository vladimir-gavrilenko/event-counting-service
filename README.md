# Сервис для учёта однотипных событий
Требования к сервису: [REQUIREMENTS.md](./REQUIREMENTS.md)

## Запуск проекта
Правильнее было бы завернуть Spring-Boot в Docker-контейнет, и поднять вместе с
PostgreSQL используя Docker-Compose. В текущей реализации для запуска необходимо
создать пустую базу данных в PostgreSQL с именем `events`. В файле `application.properties`
необходимо указать пароль и пользователя созданной базы.

Для сборки (включая тесты) в корневой директории проекта необходимо выполнить команду
```bash
./gradlew build
```

После успешного завершения сборки запуск сервиса осуществляется командой
```bash
java -jar build/libs/event-counting-service.jar
```

Добавление события осуществляется с помощью POST-запроса:
```bash
curl -d '{"description":"event description"}' -H "Content-Type: application/json" -X POST http://localhost:8080/events
```

Получение числа событий. За последние
- 60 секунд
```bash
curl -d 'seconds=60' -G http://localhost:8080/events/count
```
- 60 минут
```bash
curl -d 'seconds=3600' -G http://localhost:8080/events/count
```
- 24 часа
```bash
curl -d 'seconds=86400' -G http://localhost:8080/events/count
```

Работа под нагрузкой проверялась с помощью Yandex.Tank. Инструкция и конфигурация находятся в
[`yandex-tank`](./yandex-tank). Тестирование показало, что сервис не выдерживает 
требуемую нагрузку в 10k rps. Для удовлетворения этому требованию необходимо попробовать следующее:
- использовать асинхронную обработку запросов, неблокирующий ввод-вывод;
- оптимизировать работу с базой, использовать очередь.

## Проблемы в IntelliJ IDEA
Для работы с проектом в IntelliJ IDEA необходимо включить обработку аннотаций:

File &rarr; Settings (Ctrl + Alt + S) &rarr; Build, Execution, Deployment &rarr; Compiler &rarr; 
Annotation Processors &rarr; Enable annotation processing.

Также необходимо установить плагин для Lombok:
Settings (Ctrl + Alt + S) &rarr; Plugins.
