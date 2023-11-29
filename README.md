# task-manager

REST API для
- Просмотра списка задач на сегодня/неделю/месяц с фильтрацией по выполнению:
 http://localhost:8080/tasks - список всех задач
 http://localhost:8080/tasks/{id} - получение задачи по id
 http://localhost:8080/tasks/today - список задач на сегодня
 http://localhost:8080/tasks/week - список задач на неделю от текущего момента
 http://localhost:8080/tasks/month - список задач на месяц от текущего момента

- Создание задачи:
 http://localhost:8080/tasks - добавить новую задачу (responseBody JSON Task)

- Изменение задачи:
 http://localhost:8080/tasks/{id} - изменить задачу по id (responseBody JSON Task)

- Установление/снятие метки выполнения:
 http://localhost:8080/tasks - сменить статус задачи (responseBody JSON TaskStatusDto)

- Удаление задачи:
 http://localhost:8080/tasks/{id} - удалить задачу по id

