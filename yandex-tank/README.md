Pull the image: 

```bash
$ docker pull direvius/yandex-tank
```
Go to `yandex-tank` directory and run the tests

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

In interactive mode run

```bash
# yandex-tank -c load.yaml ammo.txt
```