package ar.gcba.cactyt.common;

import com.beust.jcommander.Parameter;

public class CommandLineOptions {

    @Parameter(names = "--debug")
	public boolean debug = true;

    @Parameter(names = {"--service-port"})
    public Integer servicePort = 4567;

    @Parameter(names = {"--database"})
    public String database = "blog";

    @Parameter(names = {"--db-host"})
    public String dbHost = "localhost";

    @Parameter(names = {"--db-username"})
    public String dbUsername = "postgres";

    @Parameter(names = {"--db-password"})
    public String dbPassword = "root";

    @Parameter(names = {"--db-port"})
    public Integer dbPort = 5432;
}
