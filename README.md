# **MY COLLECTION BOOK**

Ini adalah Rest API untuk sistem koleksi buku yang dimiliki beserta catatan mengenai buku tersebut. Catatan yang dimaksud seperti buku baru, buku dalam proses pembacaan dan buku yang telah selesai dibaca.

## **Instruksi Penggunaan**

Dalam pembuatan rest API dengan java spring boot, membutuhkan JDK versi 11 dan PostgreSQL untuk menjalankannya. Berikut ini adalah langkah-langkah untuk menggunakan API:

```
  1. Sebelum dijalankan atur bagian username, password dan database(jika ingin diubah) pada application.properties.
  2. Buat database dengan nama yang telah di atur pada application.properties.
  3. Lalu jalankan aplikasinya (menggunakan eclipse atau sejenisnya), tabel akan dibuat secara otomatis menyesuaikan bentuk model yang sudah dibuat.
  4. Selanjutnya rest API MY COLLECTION BOOK siap digunakan, untuk detailnya didokumentasikan pada bagian selanjutnya.
```

## **Daftar API yang tersedia di MY COLLECTION BOOK**
Sebelum melihat bentuk API nya, saya akan menjelaskan sedikit mengenai alur penggunaannya. Sederhananya sistem ini mencatat semua aktifitas membaca buku(hardcode), lalu buku dapat dikategorikan ke dalam beberapa tipe sesuai keinginan. berikutnya model yang tersedia:
  - BookType: model untuk menyimpan kategori buku.
  - Book: model untuk menyimpan buku beserta status dari buku tersebut.
  - ReadBook: model untuk mencatat aktifitas membaca berdasarkan tanggal dan halaman terakhir yang dibaca.

### Book Type

#### Get All
url: `http://localhost:8080/book-types/` dengan method: `GET`

output:
```json
{
  "data": [
    {
      "id": "string",
      "bookTypeCode": "string",
      "bookTypeName": "string",
      "books":[
      
      ]
    },
  ],
  "countOfData": 2
}
```

#### Get By Id
url: `http://localhost:8080/book-types/{id}/id`*({id}: diisi dengan id yang dicari)* dengan method: `GET`

output:
```json
{
  "data":{
    "id": "string",
    "bookTypeCode": "string",
    "bookTypeName": "string",
    "books":[
    ]
  }
}
```

#### Save
url: `http://localhost:8080/book-types/` dengan method: `POST`

input(request body):
 ```json
{
  "bookTypeCode":"string",
  "bookTypeName":"string"
}
 ```

output:
```json
{
  "id": "string",
  "message":"string"
}
```

#### Update
url: `http://localhost:8080/book-types/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"string",
  "bookTypeName":"string"
}
 ```
 
output:
```json
{
  "message":"string"
}
```

#### Delete
url: `http://localhost:8080/book-types/` dengan method: `DELETE`

input(request body):
 ```json
{
  "id":"string"
}
 ```
 
output:
```json
{
  "message":"string"
}
```

### Book
buku yang tersedia

#### Get All
url: `http://localhost:8080/book-types/` dengan method: `GET`

output:
```json
{
  "data": [
    {
        "id": "string",
        "issbn": "string",
        "title": "string",
        "numberOfPage": 99,
        "status": "string",
        "bookType": {
          "id": "string",
          "bookTypeCode": "string",
          "bookTypeName": "string"
        },
        "readBooks":[]
    }
  ],
  "countOfData": 1
}
```
#### Get By Id
url: `http://localhost:8080/books/{id}/id` *({id}: diisi dengan id yang dicari)* dengan method: `GET`

output:
```json
{
  "data":{
      "id": "string",
      "issbn": "string",
      "title": "string",
      "synopsis": "string",
      "numberOfPage": 13,
      "price": 99.00,
      "status": "string",
      "bookType": {
        "id": "string",
        "bookTypeCode": "string",
        "bookTypeName": "string"
      },
      "publisher": "string",
      "authorName": "string",
      "readBooks":[]
  },
  
}
```

#### Save
url: `http://localhost:8080/books/` dengan method: `POST`

input(request body):
```json
{
    "issbn":"string",
    "title":"string",
    "numberOfPage":12,
    "synopsis":"string",
    "price":0,
    
    "publisher":"string",
    "authorName":"string",
    "bookTypeId":"string"
}
```

output:
```json
{
  "id": "string",
  "message":"string"
}
```

#### Update
url: `http://localhost:8080/books/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"string",
  "title":"string",
  "numberOfPage":99,
  "synopsis":"string",
  "price":99.0,
    
  "publisher":"string",
  "authorName":"string"
}
 ```
 
output:
```json
{
  "message":"string"
}
```

#### update status
url: `http://localhost:8080/books/update-status/` dengan method: `PUT`

input(request body):
 ```json
{
  "id":"string",
  "status":"string"
}
 ```
 
output:
```json
{
  "message":"string"
}
```

### Read Book
Catatan buku yang dibaca

#### Get All
url: `http://localhost:8080/read-books/` dengan method: `GET`

output:
```json
{
  "data": [
    {
      "id": "string",
      "pageOfRead": 99,
      "dateOfRead": "timestamp",
      "book": {
        "id": "string",
        "issbn": "string",
        "title": "string",
        "synopsis": "string",
        "numberOfPage": 99,
        "price": 99.00,
        "status": "string",
        "bookType": {
          "id": "string",
          "bookTypeCode": "string",
          "bookTypeName": "string"
        },
        "publisher": "publisher",
        "authorName": "author"
      }
    }
  ],
  "countOfData": 0
}
```

#### Get By Id
url: `http://localhost:8080/read-books/{id}/id`*({id}: diisi dengan id yang dicari)* dengan method: `GET`

output:
```json
{
  "data":{
      "id": "string",
      "pageOfRead": 0,
      "dateOfRead": "string",
      "book": {
        "id": "string",
        "issbn": "string",
        "title": "string",
        "synopsis": "string",
        "numberOfPage": 99,
        "price": 0,
        "status": "string",
        "bookType": {
          "id": "string",
          "bookTypeCode": "string",
          "bookTypeName": "name"
        },
        "publisher": "string",
        "authorName": "string"
  }
}
```

#### Save
url: `http://localhost:8080/read-books/` dengan method: `POST`

input:
```json
{
    "pageOfRead":0,
    "status":"string",

    "bookId":"string"
}
```

output:
```json
{
  "id": "string",
  "message":"string"
}
```
