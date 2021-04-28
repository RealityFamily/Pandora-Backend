### /category/all `[stable]`
Возвращает все группы
``` 
{
    [
        "",
        "",
        ""
    ]
}
```

### /category/{group}/subgroup `[stable]`

Возвращает все подгруппы
```json{
    [
        "",
        "",
        ""
    ]
}
```

### item/bysubgroup/{subgroup_id} `[stable]`
Возвращает краткий перечень элементов в подгруппе
```json
{
    "title" : "журнальные",
    "items" : [
        {
            "id" : "какой-то ID"
        }
    ]
}
```

### /item/{id}/photo/small `[in work]`
Возвращает маленькую фотку модели

### /item/{id} `[in work]`
Возвращает подробную информацию о модели

``` json
{
    id : "",
    name : "",
    description : "",
    author : "",
    authorURL : ""
}
```

### /item/{id}/photo/large `[in work]`
Возвращает большую фотку/фотки модели

### /item/{id}/download `[in work]`
```
скачка ZIP по запросу
```
