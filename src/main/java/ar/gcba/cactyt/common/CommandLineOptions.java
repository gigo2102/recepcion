package ar.gcba.cactyt.common;

import com.beust.jcommander.Parameter;

public class CommandLineOptions {

    @Parameter(names = "--debug")
	public boolean debug = true;

    @Parameter(names = {"--service-port"})
    public Integer servicePort = 4567;

    @Parameter(names = {"--database"})
    public String database = "recepcion";

    @Parameter(names = {"--db-host"})
    public String dbHost = "localhost";

    @Parameter(names = {"--db-username"})
    public String dbUsername = "sa";

    @Parameter(names = {"--db-password"})
    public String dbPassword = "password";

    @Parameter(names = {"--db-port"})
    public Integer dbPort = 5432;
}
