Образ: 

```bash
$ docker pull direvius/yandex-tank
```
Запуск контейнера в интерактивном режиме:
```bash
$ cd yandex-tank
$ docker run \
      -v $(pwd):/var/loadtest \
      -v $SSH_AUTH_SOCK:/ssh-agent -e SSH_AUTH_SOCK=/ssh-agent \
      --net host \
      -it \
      --entrypoint /bin/bash \
      direvius/yandex-tank
```

Запуск тестов (в терминале контейнера):
```bash
# yandex-tank -c load.yaml ammo.txt
```

Нагрузка задаётся функцией:
```yaml
    ...
    schedule: line(1, 5000, 60)
    ...
```

Подробнее про [Yandex.Tank](https://yandextank.readthedocs.io/en/latest/tutorial.html).