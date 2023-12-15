# sf-final

getBalance
Текущий баланс пользователя
localhost:9099/rest/v1/operations/balance

takeMoney
Снятие заданной суммы с баланса пользователя;
localhost:9099/rest/v1/operations/take-money/

result, code
    Недостаточно средств (0)
    Успех (1)

putMoney
Пополнение баланса на заданную сумму;
localhost:9099/rest/v1/operations/put-money/

result, code
    Ошибка при выполнении операции (0)
    Успех (1)

transferMoney
Перевод денег с одного баланса на другой
Ошибка при выполнении операции (0)
Успех (1)

getOperationList
Вывод списка операций

database

![Monosnap DBeaver 23.2.4 - sf_balances 2023-12-01-C1.png](core%2Fdb%2FMonosnap%20DBeaver%2023.2.4%20-%20sf_balances%202023-12-01-C1.png)

\sf-final\core\db
    структура данных
    заполнение данных
    дамп с данными

\sf-final\core\postman
    rest api коллекция с запросами

\sf-final\sf-rest\src\main\resources\application-etalon.properties
заготовка application.properties
