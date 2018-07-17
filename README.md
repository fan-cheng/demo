# demo

关于provider服务启动方式的说明：

第一种方式：
使用Servlet容器运行（Tomcat、Jetty等），需要在web.xml中装配spring.xml、spring-dao、以及provider配置文件
缺点：增加复杂性（端口、管理、浪费资源（内存） 、需要占用多个端口和内存，不建议

第二种方式：
自己建立Main方法类运行，本demo中使用System.in.read()阻塞的方式防止服务自动关闭，可进行本地调使用。 
缺点：Dobbo本身提供的高级特性没用上

第三种方式使用Dubbo框架提供的Main方法类来运行（Spring 容器） 建议使用 
优点：框架本身提供（com.alibaba.dubbo.container.Main），可实现优雅关机（ShutdownHook）

第三种方式具体步骤：

    1、pom.xml中配置打包操作
    <build>
        <!--打包可执行jar-->
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <classesDirectory>target/classes/</classesDirectory>
                    <archive>
                        <manifest>
                            <mainClass>com.alibaba.dubbo.container.Main</mainClass>
                            <!-- 打包时 MANIFEST.MF文件不记录的时间戳版本 -->
                            <useUniqueVersions>false</useUniqueVersions>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>lib/</classpathPrefix>
                        </manifest>
                        <manifestEntries>
                            <Class-Path>.</Class-Path>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
            <!-- 把依赖的jar包,打成一个lib文件夹 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <type>jar</type>
                            <includeTypes>jar</includeTypes>
                            <outputDirectory>
                                ${project.build.directory}/lib
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>

        <!-- 配置文件的打包操作 -->
        <resources>
        <resource>
            <targetPath>${project.build.directory}/classes</targetPath>
            <directory>src/main/resources</directory>
            <!-- 将 xml和properties文件都打包到build目录下的 classes目录下 -->
            <filtering>true</filtering>
            <includes>
                <include>**/*.xml</include>
                <include>**/*.properties</include>
            </includes>
        </resource>
        </resources>
    </build>
    
    2、执行 mvn clean、 mvn install打成jar包，一般会生成在项目的target目录下
    3、执行java -jar xxx.jar启动服务即可
    4、详情见源码
