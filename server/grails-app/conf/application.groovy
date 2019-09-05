environments {
    production {
        dataSource {
            pooled = false
            jmxExport = true
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.ProgressDialect
            initialSize=2
            maxActive = 5
            uri = new URI(System.env.DATABASE_URL ?: "postgres://localhost:5432/test")
            username = uri.userInfo ? uri.userInfo.split(":")[0] : ""
            password = uri.userInfo ? uri.userInfo.split(":")[1] : ""
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path
            properties {
                jmxEnabled = true
                initialSize = 2
                maxActive = 5
                minIdle = 1
                maxIdle = 5
                maxWait = 10000
                maxAge = 600000
                timeBetweenEvictionRunsMillis = 5000
                minEvictableIdleTimeMillis = 60000
                validationQuery = 'SELECT 1'
                validationQueryTimeout = 3
                validationInterval = 15000
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                jdbcInterceptors = 'ConnectionState'
                defaultTransactionIsolation = 2 //# TRANSACTION_READ_COMMITTED
            }
        }
        /*dataSource {
            pooled = true
            jmxExport = true
            driverClassName = "com.mysql.jdbc.Driver"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            maxActive = 4
            uri = new URI(System.env.DATABASE_URL ?: "mysql://foo:bar@localhost")
            username = uri.userInfo ? uri.userInfo.split(":")[0] : ""
            password = uri.userInfo ? uri.userInfo.split(":")[1] : ""
            url = "jdbc:mysql://" + uri.host + uri.path

            properties {
                dbProperties {
                    autoReconnect = true
                }
            }
        }*/
    }
}