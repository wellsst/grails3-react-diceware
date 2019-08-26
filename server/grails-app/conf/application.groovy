environments {
    production {
        dataSource {
            dbCreate = "update"
            driverClassName = "org.postgresql.Driver"
            dialect = org.hibernate.dialect.PostgreSQL94Dialect
            uri = new URI(System.env.DATABASE_URL?:"postgres://localhost:5432/test")
            url = "jdbc:postgresql://" + uri.host + ":" + uri.port + uri.path + "?sslmode=require"
            if (uri.userInfo) {
                username = uri.userInfo.split(":")[0]
                password = uri.userInfo.split(":")[1]
            }
        }
    }
}