Проект является тестовым заданием при приёме на работу в компанию N.

# Статистика платежей
В топик Kafka приходит информация о платежах, совершенных клиентами банка. В каждом таком сообщении передается ID клиента, номер счета, с которого был совершен платеж, сумма платежа (для простоты все суммы будут в рублях), ОКВЭД, номер счета получателя и БИК банка получателя.
Все переводы, которые попали в топик, были успешно выполнены.
Необходимо реализовать 2 микросервиса.
## Сервис Updater
Первый - updater - он должен читать сообщения о платежах из топика Kafka и записывать их в Elasticsearch. Для каждого платежа при этом необходимо определить его категорию по ОКВЭД. Если выполняется перевод денег со счета на счет (а не оплата товаров и услуг), то ОКВЭД не указывается (тоже так сделаем для упрощения) и, в этом случае, необходимо проверить, в какой банк переведены деньги. Если перевод внутрибанковский (БИК банка получателя принадлежит банку клиента), то платеж необходимо пометить как внутренний перевод.
Соответствие ОКВЭД и категорий можно хранить в реляционной БД, но, если есть желание прокачаться в межсервисном взаимодействии, можно сделать получение ОКВЭД по коду из openapi - https://dadata.ru/api/suggest/okved2/ . В этом случае категорией расходов будем считать раздел, к которому принадлежит ОКВЭД (https://regforum.ru/okved/razdel/), а все вызовы openapi должны обязательно кэшироваться. Для простоты можно использовать любой кэш, если есть желание поучиться работать с Redis, то можно прикрутить его.

## Сервис получения статистики
Второй - REST API получения статистики платежей клиента.
Необходимо реализовать метод, который должен получать ID клиента и временной период, а возвращать статистику платежей клиента за период по категориям из Elasticsearch - категорию и сумму всех платежей за период по этой категории. Если запрашивается статистика по переводам, то в нее не должны попадать внутрибанковские переводы.


#### Пример сообщения в Kafka, который может обрабатывать программа.
{
"clientId": "12",
"okvedCode": "51.22.3",
"amount": 2000,
"senderAccountNumber": "321",
"senderBIC": "234",
"receiverAccountNumber": "123",
"receiverBIC": "432",
"paymentTime": "2001-10-10 11:11:11.111",
"created": "2001-10-10 11:11:11.111"
}

В докер окружении имеются:
elasticsearch,
zookeeper,
kafka,
kafka-ui,
redis<br>

Сами приложения надо запускать отдельно.

#### Api метод получения статистики
для локалки http://localhost:8080/statistic/client/{client_id}?startDate=2000-12-03T10:15:30.00Z&endDate=2025-12-03T10:15:30.00Z
