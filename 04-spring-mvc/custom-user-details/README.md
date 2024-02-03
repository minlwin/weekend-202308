# Database Creation

```
create database security_db1;
create user 'securityusr'@'localhost' identified by 'securitypwd';
grant all privileges on security_db1.* to 'securityusr'@'localhost';
```