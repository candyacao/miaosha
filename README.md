# springboot

docker-compose up -d   ## 启动数据库
docker-compose ps      ## 查看数据库状态

docker-compose down    ## 关闭数据库

docker-compose exec db sh ## 进入对应的容器，执行命令
mvn spring-boot:run    ## 启动Spring-boot