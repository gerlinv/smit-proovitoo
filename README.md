# SMIT Proovitöö

## Rakenduse käivitamine

Rakenduse saab käivitada ühe käsuga:
```
docker-compose up -d
```

## Rakenduse peakomponendid

### Backend
Rakenduse põhiloogika kus paikneb procedure API mis suhtleb frontendiga ning tegeleb andmete töötlemise ja salvestamisega.
Jookseb pordil 8080. Backend kasutab Postgres andmebaasi, mis on saadaval pordil 5432.

### Email-service:
Mikroteenus, mis võtab vastu backendi poolt saadetud emaili tehingu ning vastab backendile.
Jookseb pordil 8090.

### Frontend
Rakenduse kasutajaliides.
Jookseb pordil 4200.