# KURULUMLAR VE PROJE TEKNOLOJILERI

## Git Komutlari:

    ***** Degisiklikleri kaydetmek
    Commit kismindan commit and push yapıyoruz
    Terminal ekraninda git checkout master yazıyoruz
    Daha sonra git merge umit yaziyoruz
    git push -u origin master yaziyoruz
    (Eğer bu kısımda çakışma sorunu verirse git pull origin master komutu ile verileri çekiyoruz.)
    ve tekrar master dan umit e gecip kod yazmaya devam ediyoruz
 
    ***** Degisiklikleri kendi projene cekmek icin;
    git pull origin master

## MongoDB Entegrasyonu:

```bash
    docker run --name java13MongoDB -d -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:jammy 
```

    Yetkilendirme İşlemleri:
    1- MONGOSH'a tıklayarak açıyorsunuz.
    2- Açılan kısımda test> şeklinde bir yer göreceksiniz, öncelikle test DB'den kendi DB'nize geçmek için 
    use [DB_adı] yazıp enter'a basınız.
    Örn: 
    use CompanyDB
    switched to db CompanyDB
     CompanyDB > şeklinde bir görüntü elde edeceksiniz.
    3- Burada kullanıcı oluturmak için gerekli komutları giriyoruz.

```
     db.createUser({ user: "bilgeUser", pwd: "bilgeUser*", roles: ["readWrite","dbAdmin"]})
```

## RabbitMq Kurulum ve Kullanım
    RabbitMq iki port ile çalışır. 5672, 15672 bu portlardan;
    1- 5672 olan port Rest isteklerini işlemek için kullanılır, bu nedenle Spring Boot bu porta bağlanır.
    2- 15672 olan port arayüz webUI kısmıdır. yönetim ekranı burasıdır.


```bash
    docker run -d --name java13-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3-management
```

    Spring boot ile kullanım için;
    implemantatio 'org.springframework.boot:spring-boot-starter-amqp:3.2.2'

    DİKKAT!!!
    RabbitMq Desrializable işleminde getirilen yeni güvenlik config nedeniyle şu ENV nin eklenmesi gereklidir. 
    "SPRING_AMQP_DESERIALIZATION_TRUST_ALL=true" 
    Bu envirenment ı eklemek için user microservisin main class üzerine sağ tıklayarak
    run modify configuration diyerek environment variable eklememiz gerekmektedir.