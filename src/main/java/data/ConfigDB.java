package data;

public class ConfigDB {
    private String user;
    private String password;
    private String host;
    private String port;
    private String dbName;

    // Constructor
    ConfigDB() {
        this.user = "grupo03sa";
        this.password = "grup003grup003*";
        this.host = "mail.tecnoweb.org.bo";
        this.port = "5432";
        this.dbName = "db_grupo03sa";
    }

    // Getters
    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }
}
