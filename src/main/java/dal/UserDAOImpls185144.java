package dal;

import dal.dto.IUserDTO;
import dal.dto.UserDTO;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAOImpls185144 implements IUserDAO {
    //TODO Make a connection to the database
    private Connection createConnection() throws SQLException {
        return  DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185144?"
                    + "user=s185144&password=XFfpicTFLy2RzYknRgLMO");
    }

    @Override
    public void createUser(IUserDTO user) throws DALException {
        try(Connection connection = createConnection();
             PreparedStatement pStmtUser =
                     connection.prepareStatement(
                             "INSERT INTO users_mandatory2" +
                             " VALUES(?,?,?)");
             PreparedStatement pStmtRoles =
                    connection.prepareStatement(
                            "INSERT INTO roles_mandatory2 " +
                                    "VALUES(?,?)")){

            pStmtUser.setInt(1, user.getUserId());
            pStmtUser.setString(2 ,user.getUserName());
            pStmtUser.setString(3, user.getIni());
            pStmtUser.executeUpdate();

            for(String role: user.getRoles()){
                pStmtRoles.setInt(1, user.getUserId());
                pStmtRoles.setString(2, role);
                pStmtRoles.executeUpdate();
            }
        } catch (SQLException e){
            throw new DALException(e.getMessage(),e);
        }
    }

    @Override
    public IUserDTO getUser(int userId) throws DALException {

        ResultSet resultSet = null;


       try (Connection connection = createConnection();
            PreparedStatement pStmtUser = connection.prepareStatement(
                    "SELECT * FROM users_mandatory2 WHERE user_id = ?");
            PreparedStatement pStmtRoles = connection.prepareStatement(
                    "SELECT role FROM roles_mandatory2 WHERE user_id = ?")){

           IUserDTO user = new UserDTO();

           pStmtUser.setInt(1, userId);
           resultSet = pStmtUser.executeQuery();
           resultSet.next();

           user.setUserId(userId);
           user.setUserName(resultSet.getString(2));
           user.setIni(resultSet.getString(3));


           pStmtRoles.setInt(1, userId);
           resultSet = pStmtRoles.executeQuery();

           List<String> roles = new LinkedList<>();
           while(resultSet.next()){
               roles.add(resultSet.getString(1));
           }
           user.setRoles(roles);
           return user;

        } catch (SQLException e) {
            throw new DALException(e.getMessage(),e);
        } finally {
           try{
               if(resultSet != null){
                   resultSet.close();
               }
           } catch (SQLException e){
               throw new DALException(e.getMessage(), e);
           }
       }

    }



    @Override
    public List<IUserDTO> getUserList() throws DALException {

        List<IUserDTO> users = new ArrayList<>();
        UserDTO user;
        ResultSet resultSetUsers = null;
        ResultSet resultSetRoles = null;

        try(Connection connection = createConnection();
            PreparedStatement pStmt = connection.prepareStatement(
                "SELECT * FROM users_mandatory2");
            PreparedStatement pStmt1 = connection.prepareStatement(
                    "SELECT role FROM roles_mandatory2 WHERE user_id = ?")){


            resultSetUsers = pStmt.executeQuery();
            while(resultSetUsers.next()){
                // set user object equal to results from database
                user = new UserDTO();
                user.setUserId(resultSetUsers.getInt(1));
                user.setUserName(resultSetUsers.getString(2));
                user.setIni(resultSetUsers.getString(3));

                pStmt1.setInt(1, user.getUserId());
                resultSetRoles = pStmt1.executeQuery();

                List<String> roles = new LinkedList<>();
                while(resultSetRoles.next()){
                    roles.add(resultSetRoles.getString(1));
                }
                user.setRoles(roles);
                users.add(user);
            }

        } catch (SQLException e){
            throw new DALException(e.getMessage(), e);
        } finally {
            try{
                resultSetRoles.close();
                resultSetUsers.close();
            } catch (SQLException e){
                throw new DALException(e.getMessage(), e);
            }
        }
        return users;
    }



    @Override
    public void updateUser(IUserDTO user) throws DALException {
        //TODO Implement this - Should update a user in the db using data from UserDTO object.
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        //TODO Implement this - Should delete a user with the given userid from the db.
    }
}
