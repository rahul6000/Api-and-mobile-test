package API.Config;

public class Endpoints {
	public static final String POST_USER_URL = Config.getProperty("user_post_url");
	public static final String GET_USER_URL = Config.getProperty("user_get_url");
	public static final String UPDATE_USER_URL = Config.getProperty("user_update_url");
	public static final String DELETE_USER_URL = Config.getProperty("user_delete_url");

	public static final String STORE_POST_URL = Config.getProperty("store_post_url");
	public static final String STORE_GET_URL = Config.getProperty("store_get_url");
	public static final String STORE_DELETE_URL = Config.getProperty("store_delete_url");
}


