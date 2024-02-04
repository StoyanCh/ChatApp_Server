package org.chat.app.repository.commadHandler;
import org.chat.app.model.User;
import org.chat.app.repository.UserRepository;

import java.sql.*;
import java.util.Optional;

public class UserCommandHandler {
    private final UserRepository userRepository;

    public UserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void handleCommand(String command) {
        String[] parts = command.split(":");
        String commandType = parts[0];

        switch (commandType) {
            case "CREATE_USER":
                handleCreateUserCommand(parts);
                break;
            case "UPDATE_USER":
                handleUpdateUserCommand(parts);
                break;
            case "DELETE_USER":
                handleDeleteUserCommand(parts);
                break;
            // Добавете други команди тук...
            default:
                System.out.println("Unknown command: " + commandType);
        }
    }

    private void handleCreateUserCommand(String[] parts) {
        if (parts.length != 6) {
            System.out.println("Invalid command format. Expected: CREATE_USER:firstName:lastName:email:password:status");
            return;
        }

        User user = new User();
        user.setFirstName(parts[1]);
        user.setLastName(parts[2]);
        user.setEmail(parts[3]);
        user.setPassword(parts[4]);
        user.setStatus(Boolean.parseBoolean(parts[5]));

        userRepository.save(user);

        System.out.println("User created successfully");
    }

    private void handleUpdateUserCommand(String[] parts) {
        if (parts.length != 7) {
            System.out.println("Invalid command format. Expected: UPDATE_USER:userId:firstName:lastName:email:password:status");
            return;
        }

        int userId = Integer.parseInt(parts[1]);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(parts[2]);
            user.setLastName(parts[3]);
            user.setEmail(parts[4]);
            user.setPassword(parts[5]);
            user.setStatus(Boolean.parseBoolean(parts[6]));

            userRepository.save(user);

            System.out.println("User updated successfully");
        } else {
            System.out.println("User with id " + userId + " does not exist");
        }
    }

    private void handleDeleteUserCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Invalid command format. Expected: DELETE_USER:userId");
            return;
        }

        int userId = Integer.parseInt(parts[1]);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User with id " + userId + " does not exist");
        }
    }

    public Optional<User> findById(int userId) {
        Optional<User> optionalUser = Optional.empty();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ChatAppDB", "postgres", "Kurama279")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setStatus(resultSet.getBoolean("status"));

                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching user: " + e.getMessage());
        }

        return optionalUser;
    }


}
