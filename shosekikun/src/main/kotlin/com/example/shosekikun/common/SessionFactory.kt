//package com.example.shosekikun.common
//
//import org.apache.ibatis.datasource.pooled.PooledDataSource
//import org.apache.ibatis.session.SqlSessionFactory
//import org.mybatis.spring.SqlSessionFactoryBean
//import org.mybatis.spring.SqlSessionTemplate
//import org.mybatis.spring.annotation.MapperScan
//import org.springframework.beans.factory.annotation.Qualifier
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver
//import javax.sql.DataSource
//
//@Configuration
//@MapperScan(
//    basePackages = ["com.example.shosekikun.service", "com.example.shosekikun.repository",
//        "com.example.shosekikun.mapper"],
//    sqlSessionTemplateRef = "SqlSessionTemplate"
//)
//class SessionFactory(private val propertyHolder: PropertyHolder) { // ↑で定義したプロパティを持っているクラスを注入する。
//
//    companion object {
//        // XMLのありか
//        // クラスの外にstaticフィールドとして切り出してもいい
//        const val MAPPER_XML_PATH: String = "classpath:com/example/api/**/*.xml"
//    }
//
//    /**
//     * データソース
//     */
//    @Bean(name = ["DataSource"])
//    @ConfigurationProperties(prefix = "spring.datasource")
//    fun dataSource(): DataSource? {
//        // プロパティから読み込んでデータソースにセットする
//        return PooledDataSource().apply {
//            url = propertyHolder.url
//            username = propertyHolder.username
//            password = propertyHolder.password
//            driver = propertyHolder.driverClassName
//        }
//    }
//
//    /**
//     * SQLセッションファクトリ
//     */
//    @Bean(name = ["SqlSessionFactory"])
//    @Throws(Exception::class)
//    fun sqlSessionFactory(@Qualifier("DataSource") dataSource: DataSource?): SqlSessionFactory? {
//        val bean = SqlSessionFactoryBean()
//        bean.setDataSource(dataSource)
////        bean.setMapperLocations(*PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH))
//        return bean.getObject()
//    }
//
//    /**
//     * SQLセッションテンプレート
//     */
//    @Bean(name = ["SqlSessionTemplate"])
//    @Throws(java.lang.Exception::class)
//    fun sqlSessionTemplate(@Qualifier("SqlSessionFactory") sqlSessionFactory: SqlSessionFactory?): SqlSessionTemplate? {
//        return SqlSessionTemplate(sqlSessionFactory)
//    }
//
//}
//
