###springboot redis整合
    1.新建maven工程，支持web，依赖详见pom.xml
    2.编写启动类Application.java
        @SpringBootApplication
        public class Application {
            public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
            }
        }
    3.实现数据库到业务层的jpa操作，需要添加jpa模块，具体见repository, service, domain以及test用例
    4.添加Redis业务逻辑支持数据redis缓存
        需要注意的一点是，实现redis我们需要实现CacheManager, RedisTemplate, KeyGenerator
        KeyGenerator实现是可选的，但是采用默认的key生成策略会存在一个问题，如果方法参数中存在非String
        类型的参数时将会发生错误，推荐自己重写key生成策略，只需要实现CachingConfigurerSupport中的
        keyGenerator方法即可，程序中采用的是类名+方法名+参数值的方式生成唯一redis key

        
        说明，这里连接redis只是测试了单台redis服务器，至于多台redis服务器集群以及一致性hash集群算法，这里没有做详细的测试