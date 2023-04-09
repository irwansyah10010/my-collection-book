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
Sebelum melihat bentuk API nya, saya akan menjelaskan sedikit mengenai alur penggunaannya. Sederhananya sistem ini mencatat semua aktifitas membaca buku(hardcode), lalu buku dapat dikategorikan ke dalam beberapa tipe sesuai keinginan. karena menggunakan security dengan bearer token, anda harus login dahulu untuk mendapatkan token untuk mengakses API yang lain. Sebelum itu anda harus membuat usernya dahulu dengan mengakses API berikut:

### User
API user untuk mengakses My Collection

#### Save
Ini adalah API untuk menambahkan user baru

url: `http://localhost:8080/users/` dengan method: `POST`

input:

```json
{
    "username": "namausername",
    "name": "namanya",
    "password": "passnya",
    "role": "rolenya(hanya sebagai pemanis saja)"
}
```
output:

```json
{
    "id": "6a1b8051-2e40-4a12-b004-3db3290fd38a",
    "message": "user has been added"
}
```

Setelah user diperoleh, anda bisa login langsung dengan username dan password berdasarkan user yang telah berhasil anda tambahkan. Berikut API untuk login

### Login
API untuk memproleh token

url: `http://localhost:8080/login` dengan method: `POST`

input:

```json
{
    "username":"username2",
    "password":"name2"
}
```

output:

```output
{
    "role": "admin",
    "name": "name2",
    "id": "6a1b8051-2e40-4a12-b004-3db3290fd38a",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJyYyI6ImFkbWluIiwiaWQiOiI2YTFiODA1MS0yZTQwLTRhMTItYjAwNC0zZGIzMjkwZmQzOGEiLCJleHAiOjE2ODEwMTM2NzF9.McTOVhi5cnIOh_Eu3GN4n_V2AqFkMWC53rA1an46R4U"
}
```

Anda bisa mengakses API My Collection Book bersama dengan token yang berhasil diperoleh. Untuk caranya mungkin bisa kalian cari di google. TY

Untuk selanjut, kita jelaskan model yang tersedia:
  - Status: model untuk status buku yang tersedia.
  - BookType: model untuk kategori buku.
  - Book: model untuk koleksi buku beserta status dari buku tersebut.
  - ReadBook: model untuk mencatat aktifitas membaca berdasarkan tanggal dan halaman terakhir yang dibaca.

### Status
API status wajib dijalankan pertama kali, karena mempengaruhi API Book 

#### Save
Ini adalah api untuk membuat data status baru

url: `http://localhost:8080/statuses/` dengan method: `POST`

input:

```json
[
  {
    "statusCode":"N",
    "statusName":"new"
  },
  {
    "statusCode":"R",
    "statusName":"read"
  },
  {
    "statusCode":"C",
    "statusName":"complete"
  }
]
```

#### Get All
url: `http://localhost:8080/statuses/` dengan method: `GET`

output:
```json
{
  "data": [
    {
      "id": "string",
      "statusCode": "string",
      "statusName": "string"
    },
  ],
  "countOfData": 0
}
```

#### get by status code
url: `http://localhost:8080/statuses/{code}/code`*({code}: diisi dengan status code yang dicari)* dengan method: `GET`

```json
{
  "data": {
    "id": "697b51d1-7aef-48c1-b3a4-823f6438356c",
    "statusCode": "R",
    "statusName": "read"
  }
}

```

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
url: `http://localhost:8080/books/` dengan method: `GET`

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
    "bookType":{
      "bookTypeCode":"String"
    }
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

#### Delete
url: `http://localhost:8080/books/` dengan method: `DELETE`


input: 
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
url: `http://localhost:8080/read-books/{id}/id` *({id}: diisi dengan id yang dicari)* dengan method: `GET`

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
}
```

#### Save
url: `http://localhost:8080/read-books/` dengan method: `POST`

input:
```json
{
    "pageOfRead":0,
    "book":{
      "id":"string"
    }
}
```

output:
```json
{
  "id": "string",
  "message":"string"
}
```
