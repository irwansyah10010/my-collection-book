# **MY COLLECTION BOOK**

Ini adalah Rest API untuk aplikasi koleksi buku yang dimiliki beserta catatan tentang buku seperti buku baru, buku dalam progress pembacaan dan buku yang telah selesai dibaca.

## **Instruksi Penggunaan**

Dalam pembuatan res API dengan java spring boot, membutuhkan JDK versi 11 dan PosgreSQL untuk menjalankannya. Berikut ini adalah langkah-langkahnya:

```
  1. Sebelum dijalankan atur bagian username, password dan database(jika ingin diubah) pada application.properties.
  2. Buat database dengan nama yang telah di atur pada application.properties.
  3. Lalu jalankan aplikasinya (menggunakan java editor), tabel akan dibuat secara otomatis menyesuaikan bentuk model yang dibuat.
  4. Selanjutnya rest API MY COLLECTION BOOK siap digunakan, untuk detailnya didokumentasikan pada baris selanjutnya.
```

## **Daftar API yang tersedia di MY COLLECTION BOOK**


### Book Type
Variasi tipe buku yang tersedia, 

#### getAll()
url: `http://localhost:8080/book-types/` dengan method: `GET`

output:
```json
{
  "data": [
    {
      "id": "id",
      "bookTypeCode": "code",
      "bookTypeName": "name"
    },
    {
      "id": "id2",
      "bookTypeCode": "code2",
      "bookTypeName": "name2"
    }
  ],
  "countOfData": 2
}
```

#### getById(id:String)
url: `http://localhost:8080/book-types/{id}/id`*({id}: diisi dengan id yang dicari*) dengan method: `GET`

output:
```json
{
  "data":{
    "id": "id",
      "bookTypeCode": "code",
      "bookTypeName": "name"
  }
}
```

#### save(entity:Object Book Type)
url: `http://localhost:8080/book-types/` dengan method: `POST`

input(request body):
 ```json
{
  "bookTypeCode":"code",
  "bookTypeName":"name"
}
 ```

output(data tersimpan):
```json
{
  "id": "id",
  "message":"message"
}
```

#### update(entity:Object Book Type)
url: `http://localhost:8080/book-types/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"id",
  "bookTypeName":"name"
}
 ```
 
output:

```json
{
  "message":"message"
}
```

### Book
buku yang tersedia

#### getAll()
url: `http://localhost:8080/book-types/` dengan method: `GET`

output:

```json
{
  "data": [
   {
      "id": "id",
      "issbn": "issbn",
      "title": "title",
      "synopsis": "synopsis",
      "numberOfPage": number,
      "price": price,
      "status": "status",
      "bookType": {
        "id": "id",
        "bookTypeCode": "code",
        "bookTypeName": "name"
      },
      "publisher": "publisher",
      "authorName": "author"
  },
  "countOfData": count
}
```
#### getById(id:String)
url: `http://localhost:8080/books/{id}/id` *({id}: diisi dengan id yang dicari)* dengan method: `GET`

output:
```json
{
  "data":{
      "id": "id",
      "issbn": "issbn",
      "title": "title",
      "synopsis": "synopsis",
      "numberOfPage": number,
      "price": price,
      "status": "status",
      "bookType": {
        "id": "id",
        "bookTypeCode": "code",
        "bookTypeName": "name"
      },
      "publisher": "publisher",
      "authorName": "author"
  },
}
```

#### save(entity:Object Book Type)
url: `http://localhost:8080/books/` dengan method: `POST`

input(request body):
```json
{
    "issbn":"issbn",
    "title":"title",
    "numberOfPage":number,
    "synopsis":"synopsis",
    "price":0,
    
    "publisher":"publisher",
    "authorName":"author",
    "bookTypeId":"typeId"
}
```

output:
```json
{
  "id": "id",
  "message":"message"
}
```

#### update(entity:Object Book Type)
url: `http://localhost:8080/book-types/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"id",
  "title":"title",
  "numberOfPage":number,
  "synopsis":"synopsis",
  "price":0,
    
  "publisher":"publisher",
  "authorName":"author"
}
 ```
 
output:
```json
{
  "message":"message"
}
```

#### update status(entity:Object Book Type)
url: `http://localhost:8080/book-types/update-status/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"id",
  "status":"status"
}
 ```
 
output:
```json
{
  "message":"message"
}
```

### Read Book
Catatan buku yang dibaca

#### getAll()
url: `http://localhost:8080/read-books/` dengan method: `GET`

output:
```json
{
  "data": [
    {
      "id": "id",
      "pageOfRead": 0,
      "dateOfRead": "dateOfRead",
      "book": {
        "id": "id",
        "issbn": "issbn",
        "title": "title",
        "synopsis": "synopsis",
        "numberOfPage": number,
        "price": 0,
        "status": "status",
        "bookType": {
          "id": "id",
          "bookTypeCode": "code",
          "bookTypeName": "name"
        },
        "publisher": "publisher",
        "authorName": "author"
      }
    }
  ],
  "countOfData": 0
}
```

#### getById(id:String)
url: `http://localhost:8080/read-books/{id}/id`*({id}: diisi dengan id yang dicari)* dengan method: `GET`

output:
```json
{
  data:{
      "id": "id",
      "pageOfRead": 0,
      "dateOfRead": "dateOfRead",
      "book": {
        "id": "id",
        "issbn": "issbn",
        "title": "title",
        "synopsis": "synopsis",
        "numberOfPage": number,
        "price": 0,
        "status": "status",
        "bookType": {
          "id": "id",
          "bookTypeCode": "code",
          "bookTypeName": "name"
        },
        "publisher": "publisher",
        "authorName": "author"
   }
}
```

#### save(entity:Object Book Type)
url: `http://localhost:8080/read-books/` dengan method: `POST`

input:
```json
{
    "pageOfRead":0,
    "status":"status",

    "bookId":"id"
}
```

output:
```json
{
  "id": "id",
  "message":"message"
}
```
