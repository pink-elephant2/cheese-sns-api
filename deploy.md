# デプロイ手順

## EC2起動

AWSコンソールにて。

## yum update

`$ sudo yum update`

## Java8インストール

`$ java -version`

`$ sudo yum install -y java-1.8.0-openjdk.x86_64`

`$ sudo alternatives --config java`

## 日付フォーマット

`$ date`

`$ sudo cp -p /usr/share/zoneinfo/Japan /etc/localtime`

`$ sudo vi /etc/sysconfig/clock`

```
ZONE="Asia/Tokyo"
UTC=false
```

`$ date`

## nginxインストール

`$ sudo yum -y install nginx`

/conf/nginx.conf

`$ sudo service nginx start`

`$ sudo chkconfig nginx on`

## redisインストール

`$ sudo yum --enablerepo=epel install redis`

`$ sudo service redis start`

## AWSLogsインストール

`$ sudo yum install -y awslogs`

`$ sudo vi /etc/awslogs/awscli.conf`

※値は別管理

```
[default]
aws_access_key_id =
aws_secret_access_key =
region = ap-northeast-1
```

`$ sudo vi /etc/awslogs/awslogs.conf`

/conf/awslogs.conf

サフィックス

* -dev
* -prod

`$ sudo chkconfig awslogs on`

`$ sudo service awslogs start`

## bash_profile修正

`$ vi ~/.bash_profile`

末尾に追加 ※値は別管理

```
export DATABASE_URL_SPRINGBOOT=""
export DATABASE_USER=""
export DATABASE_PASSWORD=""
export CHEESE_S3_ACCESS_KEY=""
export CHEESE_S3_SECRET_KEY=""
export CHEESE_S3_BUCKET=""
export CHEESE_FACEBOOK_APP_ID=""
export CHEESE_FACEBOOK_APP_SECRET=""
export CHEESE_FACEBOOK_REDIRECT_URL=""
export CHEESE_MAIL_ADDRESS=""
export CHEESE_MAIL_PASSWORD=""
export CHEESE_SLACK_APP_TOKEN=""
export CHEESE_AES_KEY=""
```

## アプリアップロード

`$ mkdir ~/project`

/build/libs/cheese-sns-api-0.1.0.jar を ~/project/cheese-sns-api-0.1.0.jar にアップロード

`$ mkdir ~/project/script`

/script/start_server.sh を ~/project にアップロード

/script/stop_server.sh を ~/project にアップロード

`$ chmod 755 ~/project/script/start_server.sh`

`$ chmod 755 ~/project/script/stop_server.sh`

`$ mkdir ~/project/logs`

## crontab定義

`$ crontab -e`

```
@reboot sudo service redis start && ~/project/script/start_server.sh
```

## 起動

`$ ~/project/script/start_server.sh`

## （おまけ）停止

`$ ~/project/script/stop_server.sh`
