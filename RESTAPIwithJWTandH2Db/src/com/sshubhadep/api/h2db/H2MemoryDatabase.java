package com.sshubhadep.api.h2db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sshubhadep.api.product.Product;
import com.sshubhadep.api.store.Store;
import com.sshubhadep.auth.user.User;

public class H2MemoryDatabase {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	private static Connection getDBConnection() {

		Connection dbConnection = null;
		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());
		}
		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}

		return dbConnection;
	}

	static {

		Connection connection = getDBConnection();
		Statement stmt = null;

		try{

			connection.setAutoCommit(false);

			stmt = connection.createStatement();
			stmt.execute("CREATE TABLE Products (ProductID int primary key, "
					+ "ProductName varchar(200), ProductSport varchar(200), "
					+ "ProductLevel int,ProductDescription varchar(200),"
					+ "AssociatedStores varchar(200))");

			stmt.execute("CREATE TABLE Stores (StoreID int primary key, "
					+ "StoreName varchar(200), StoreCity varchar(200))");

			stmt.execute("CREATE TABLE Users (UserID int primary key, "
					+ "FirstName varchar(200), LastName varchar(200), "
					+ "Age int, Gender varchar(20), "
					+ "UserName varchar(200), Password varchar(200))");

			stmt.close();
			connection.commit();

		}catch(SQLException e){
			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}


	public static List<Product> getAllProducts() {

		Connection connection = getDBConnection();
		Statement stmt = null;

		List<Product> productList = new ArrayList<Product>();

		try{

			connection.setAutoCommit(false);

			stmt = connection.createStatement();
			/*stmt.execute("CREATE TABLE Products IF NOT EXISTS (ProductID int primary key, "
					+ "ProductName varchar(200), ProductSport varchar(200), "
					+ "ProductLevel int,ProductDescription varchar(200),"
					+ "AssociatedStores varchar(200))");*/

			ResultSet rs = stmt.executeQuery("select * from Products");

			while (rs.next()) {

				Product product = new Product(rs.getInt("ProductID"),rs.getString("ProductName"),rs.getString("ProductName"),
						rs.getInt("ProductLevel"),rs.getString("ProductDescription"),rs.getString("AssociatedStores"));

				productList.add(product);
			}

			stmt.close();
			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}

		return productList;
	}

	public static void saveProduct(Product product){

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement insertPreparedStatement = null;

		/*String CreateQuery = "CREATE TABLE Products IF NOT EXISTS (ProductID int primary key, "
				+ "ProductName varchar(200), ProductSport varchar(200), "
				+ "ProductLevel int,ProductDescription varchar(200),"
				+ "AssociatedStores varchar(200))";*/

		String InsertQuery = "INSERT INTO Products(ProductID, ProductName, ProductSport, ProductLevel, ProductDescription, AssociatedStores) "
				+ "VALUES(?,?,?,?,?,?)";

		try{

			connection.setAutoCommit(false);

			/*createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();*/

			insertPreparedStatement = connection.prepareStatement(InsertQuery);
			insertPreparedStatement.setInt(1, product.getProductID());
			insertPreparedStatement.setString(2, product.getProductName());
			insertPreparedStatement.setString(3, product.getProductSport());
			insertPreparedStatement.setInt(4, product.getProductLevel());
			insertPreparedStatement.setString(5, product.getProductDescription());
			insertPreparedStatement.setString(6, product.getAssociatedStores());
			insertPreparedStatement.executeUpdate();
			insertPreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}

	public static void updateProduct(Product product) {

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement updatePreparedStatement = null;

		/*String CreateQuery = "CREATE TABLE Products IF NOT EXISTS (ProductID int primary key, "
				+ "ProductName varchar(200), ProductSport varchar(200), "
				+ "ProductLevel int,ProductDescription varchar(200),"
				+ "AssociatedStores varchar(200))";*/

		String updateQuery = "UPDATE Products SET "
				+ "ProductName = ?,ProductSport = ?,ProductLevel =?,ProductDescription =?,"
				+ "AssociatedStores = ?  where ProductID = ?";

		try{
			connection.setAutoCommit(false);

			/*			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();*/

			updatePreparedStatement = connection.prepareStatement(updateQuery);
			updatePreparedStatement.setString(1,product.getProductName());
			updatePreparedStatement.setString(2,product.getProductSport());
			updatePreparedStatement.setInt(3, product.getProductLevel());
			updatePreparedStatement.setString(4,product.getProductDescription());
			updatePreparedStatement.setString(5,product.getAssociatedStores());
			updatePreparedStatement.setInt(6, product.getProductID());
			updatePreparedStatement.executeUpdate();
			updatePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}

	public static void deleteProduct(Product product) {

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement deletePreparedStatement = null;

		/*		String CreateQuery = "CREATE TABLE Products IF NOT EXISTS (ProductID int primary key, "
				+ "ProductName varchar(200), ProductSport varchar(200), "
				+ "ProductLevel int,ProductDescription varchar(200),"
				+ "AssociatedStores varchar(200))";
		 */
		String DeleteQuery = "DELETE FROM Products where ProductID = ?";

		try{
			connection.setAutoCommit(false);

			/*			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			 */
			deletePreparedStatement = connection.prepareStatement(DeleteQuery);
			deletePreparedStatement.setInt(1, product.getProductID());
			deletePreparedStatement.executeUpdate();
			deletePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}

	//store

	public static List<Store> getAllStores() {

		Connection connection = getDBConnection();
		Statement stmt = null;

		List<Store> storeList = new ArrayList<Store>();

		try{

			connection.setAutoCommit(false);

			stmt = connection.createStatement();
			//			stmt.execute("CREATE TABLE Stores IF NOT EXISTS (StoreID int primary key, "
			//					+ "StoreName varchar(200), StoreCity varchar(200))");

			ResultSet rs = stmt.executeQuery("select * from Stores");

			while (rs.next()) {

				Store store = new Store(rs.getInt("StoreID"),rs.getString("StoreName"),rs.getString("StoreCity"));

				storeList.add(store);
			}

			stmt.close();
			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}

		return storeList;
	}

	public static void saveStore(Store store) {

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement insertPreparedStatement = null;

		/*String CreateQuery = "CREATE TABLE Stores (StoreID int primary key, "
				+ "StoreName varchar(200), StoreCity varchar(200))";
		 */
		String InsertQuery = "INSERT INTO Stores(StoreID, StoreName, StoreCity) "
				+ "VALUES(?,?,?)";

		try{
			connection.setAutoCommit(false);

			/*			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			 */
			insertPreparedStatement = connection.prepareStatement(InsertQuery);
			insertPreparedStatement.setInt(1, store.getStoreID());
			insertPreparedStatement.setString(2, store.getStoreName());
			insertPreparedStatement.setString(3, store.getStoreCity());
			//int i = 
			insertPreparedStatement.executeUpdate();
			insertPreparedStatement.close();

			//System.out.println(i);

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}

		return;
	}

	public static void updateStore(Store store) {

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement updatePreparedStatement = null;

		/*		String CreateQuery = "CREATE TABLE Stores IF NOT EXISTS (StoreID int primary key, "
				+ "StoreName varchar(200), StoreCity varchar(200))";
		 */
		String updateQuery = "UPDATE Stores SET "
				+ "StoreName = ?,StoreCity = ? where StoreID = ?";

		try{
			connection.setAutoCommit(false);

			/*			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			 */
			updatePreparedStatement = connection.prepareStatement(updateQuery);
			updatePreparedStatement.setString(1,store.getStoreName());
			updatePreparedStatement.setString(2,store.getStoreCity());
			updatePreparedStatement.setInt(3, store.getStoreID());
			updatePreparedStatement.executeUpdate();
			updatePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}


	public static void deleteStore(Store store) {

		Connection connection = getDBConnection();

		//PreparedStatement createPreparedStatement = null;
		PreparedStatement deletePreparedStatement = null;

		/*		String CreateQuery = "CREATE TABLE Stores IF NOT EXISTS (StoreID int primary key, "
				+ "StoreName varchar(200), StoreCity varchar(200))";
		 */
		String DeleteQuery = "DELETE FROM Stores where StoreID = ?";

		try{
			connection.setAutoCommit(false);

			/*			createPreparedStatement = connection.prepareStatement(CreateQuery);
			createPreparedStatement.executeUpdate();
			createPreparedStatement.close();
			 */
			deletePreparedStatement = connection.prepareStatement(DeleteQuery);
			deletePreparedStatement.setInt(1, store.getStoreID());
			deletePreparedStatement.executeUpdate();
			deletePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}


	//user

	public static List<User> getAllUsers() {

		Connection connection = getDBConnection();
		Statement stmt = null;

		List<User> userList = new ArrayList<User>();

		try{

			connection.setAutoCommit(false);

			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from Users");

			while (rs.next()) {

				User user = new User(rs.getInt("UserID"),rs.getString("FirstName"),rs.getString("LastName"),
						rs.getInt("Age"),rs.getString("Gender"),rs.getString("UserName"),rs.getString("Password"));

				userList.add(user);
			}

			stmt.close();
			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}

		return userList;
	}

	public static void saveUser(User user) {

		Connection connection = getDBConnection();
		PreparedStatement insertPreparedStatement = null;

		String InsertQuery = "INSERT INTO Users(UserID,FirstName,LastName,Age,Gender,UserName,Password) "
				+ "VALUES(?,?,?,?,?,?,?)";

		try{
			connection.setAutoCommit(false);

			insertPreparedStatement = connection.prepareStatement(InsertQuery);
			insertPreparedStatement.setInt(1, user.getUserID());
			insertPreparedStatement.setString(2, user.getFirstName());
			insertPreparedStatement.setString(3,  user.getLastName());
			insertPreparedStatement.setInt(4, user.getAge());
			insertPreparedStatement.setString(5, user.getGender());
			insertPreparedStatement.setString(6,  user.getUserName());
			insertPreparedStatement.setString(7,  user.getPassword());
			int i = insertPreparedStatement.executeUpdate();
			insertPreparedStatement.close();

			if(i==1)
				System.out.println("User successfully added");

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}

		return;
	}

	public static void updateUser(User user) {

		Connection connection = getDBConnection();
		PreparedStatement updatePreparedStatement = null;

		String updateQuery = "UPDATE Users SET "
				+ "FirstName = ?,LastName = ?, "
				+ "Age = ?,Gender = ? "
				+ "where UserName = ?";

		try{
			connection.setAutoCommit(false);

			updatePreparedStatement = connection.prepareStatement(updateQuery);
			updatePreparedStatement.setString(1,user.getFirstName());
			updatePreparedStatement.setString(2,user.getLastName());
			updatePreparedStatement.setInt(3, user.getAge());
			updatePreparedStatement.setString(4,user.getGender());
			updatePreparedStatement.setString(5,user.getUserName());
			updatePreparedStatement.executeUpdate();
			updatePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}


	public static void deleteUser(User user) {

		Connection connection = getDBConnection();
		PreparedStatement deletePreparedStatement = null;

		String DeleteQuery = "DELETE FROM Users where UserName = ?";

		try{
			connection.setAutoCommit(false);

			deletePreparedStatement = connection.prepareStatement(DeleteQuery);
			deletePreparedStatement.setString(1, user.getUserName());
			deletePreparedStatement.executeUpdate();
			deletePreparedStatement.close();

			connection.commit();

		} catch (SQLException e) {

			System.out.println("Exception Message " + e.getLocalizedMessage());

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				connection.close();

			} catch (SQLException e) {

				System.out.println("Exception Message " + e.getLocalizedMessage());
			}
		}
	}
}