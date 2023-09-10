# store
商城管理系统
请按照以下步骤进行操作：
1. 在IDEA中创建一个新的Spring Boot项目。
2. 使用Maven管理依赖项，并确保在`pom.xml`文件中包含适当的依赖项，以支持Spring Boot、MySQL连接和其他所需的库和工具。
3. 配置MySQL数据库连接。在`application.properties`或`application.yml`配置文件中，设置数据库连接的URL、用户名和密码等信息。
4. 创建实体类来表示用户、商品等相关数据，并与MySQL数据库中的表进行映射。可以使用Spring Data JPA或其他ORM框架来简化数据访问层的开发。
5. 创建控制器类来处理HTTP请求，并编写相应的业务逻辑来处理用户的购买行为、商品列表展示等操作。
6. 使用JavaScript和HTML/CSS创建前端页面，实现用户界面和交互效果。可以使用HTML模板引擎（如Thymeleaf）来动态生成页面。
7. 在前端页面中使用AJAX或其他技术与后端进行数据交互，例如通过AJAX请求获取商品列表或提交用户的购买请求。
8. 运行项目并测试功能。确保用户可以注册、浏览商品、添加到购物车并进行结算等常见操作。
9. 如果需要添加管理员，请在Test类中的UserServiceTests里添加。
10. 代码在master里，不在main里面。
希望以上步骤能帮助你开始项目。
