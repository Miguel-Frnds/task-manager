package br.com.miguel.task_manager.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) { super("User not found with id: " + userId); }

    public UserNotFoundException(String message) {
        super(message);
    }
}
