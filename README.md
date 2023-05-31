plan
介绍
规划网站,主要模块：学习计划、工作计划、存钱计划 技术栈：SpringCloud、SpringBoot、mybatis-plus、shardingJDBC、ElasticSearch、Xxl、RocketMQ、Redis、阿里云服务 服务拆分：用户中心、三方服务、api网关、学习计划服务、存钱计划服务、工作计划服务

软件架构
软件架构说明 study-service：ddd(领域驱动) other：mvc(三层架构)

安装教程
mysql、redis使用阿里云 其他中间件、微服务组件运行在云服务器，不修改配置文件就行 切换阿里镜像，下完maven依赖后可直接运行。

使用说明
自己有能力独立完成最好、不懂可以参考users-center和study-service，三个服务本质上功能都差不多

参与贡献
yhx：users-center、gateway、third-party-service、study-service