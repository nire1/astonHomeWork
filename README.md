# Книжный Справочник
 Сервис по получению информации о книгах и авторах

## Описание сущностей
Лицензия (licence) , которой может владеть только один автор:
>* id
>* number (номер лицензии)

Автор (author), который владеет только одной лицензией и многими книгами:
>* id
>* name
>* List Book
>* licence

Книга(book):
>* id
>* name
>* author

![table](https://github.com/nire1/astonHomeWork/assets/19267297/a771b13a-95fb-4216-b02c-892f7dfc3bae)

## Принцип работы
### При обращении по адресу localhost:8080/licence указать параметры:
* GET-method(Получение всего списка)
> пустые параметры
* POST-method(создание лицензии)
>* number
* PUT-method(обновление)
>* id
>* number
* DELETE-method
>* number (поиск по номеру и удаление)
### При обращении по адресу localhost:8080/author указать параметры:
* GET-method
> id
* POST-method
>* name
>* licence_id
* PUT-method(обновление)
>* id
>* name
* DELETE-method
>* id
### При обращении по адресу localhost:8080/books указать параметры:
* GET-method(получение всех книг)
> пустые параметры
* POST-method
>* name
>* author_id
* PUT-method(обновление)
>* id
>* name
>* author_id
* DELETE-method
>* id

