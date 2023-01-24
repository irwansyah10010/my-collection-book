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
Variasi tipe buku yang tersedia

#### getAll()
output:

```
{
  data:[
  
  ],
  countOfData: total data(number)
}
```

#### getById(id:String)
output:

```
{
  data:{
  
  }
}
```

#### save(entity:Object Book Type)
output:

```
{
  id: id new object,
  message:pesan yang diberikan
}
```

#### update(entity:Object Book Type)
output:

```
{
  message:pesan yang diberikan
}
```


### Book
buku yang tersedia

#### getAll()

output:

```
{
  data:[
  
  ],
  countOfData: total data(number)
}
```
#### getById(id:String)

output:

```
{
  data:{
  
  }
}
```

#### save(entity:Object Book Type)
output:

```
{
  id: id new object,
  message:pesan yang diberikan
}
```

#### update(entity:Object Book Type)
output:

```
{
  message:pesan yang diberikan
}
```

### Read Book
Catatan buku yang dibaca

#### getAll()
```
{
  data:[
  
  ],
  countOfData: total data(number)
}
```

#### getById(id:String)
output:

```
{
  data:{
  
  }
}
```

#### save(entity:Object Book Type)
output:

```
{
  id: id new object,
  message:pesan yang diberikan
}
```
