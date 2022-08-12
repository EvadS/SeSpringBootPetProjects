

## Git config 

Глобальные настройки для логгирования 
все созраняется HOME_DIR/.gitconfig

```bash
  git config  --global user.name
```

```bash
  git config  --global user.email
```
все конфигурации 
```bash
  git config  -l
```

установить для одного репо (локально)
```bash
git config  --global user.name
```
-------------------------------------------------
## Local repository 
### последний коммит
```
git log -1 -p 
```
### отменить изменение 
```bash
 git checkout -- FILE_NAME
```
### что будет записано в коммит 
```bash
 git diff --staged
``` 

### Изменения 
изменить коммит не делая новый 
```bash
git commit --amend
```




