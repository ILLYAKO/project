package service;

import domain.User;
import exception.EntityNotFoundException;
import exception.ValidationException;
import repository.UserRepository;
import repository.Repository;

import java.util.List;


public class UserService implements Service<User> {

    private Repository<User> userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }


    private void validate(User user) {
        if (isDuplicatedEmail(user)) {
            throw new ValidationException("There is another user with the same email!");
        }
    }

    private boolean isDuplicatedEmail(User user) {
        return userRepository.findByCriteria("user_email", user.getUserEmail())
                .filter(u -> !u.getUserID().equals(user.getUserID()))
                .isPresent();
    }

    @Override
    public void add(User user) {
        validate(user);
        userRepository.add(user);
    }

    @Override
    public void modify(User user) {
        findById(user.getUserID());
        validate(user);
        userRepository.modify(user);
    }

    @Override
    public void remove(String id) {
        userRepository.remove(findById(id));
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id " + id + " was not found!"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllComplaintOfUser(User user) {
        return null;
    }

    public User findByCriteria(String field, String criteria) {
        return userRepository.findByCriteria(field, criteria).orElseThrow(() ->
                new EntityNotFoundException("User with " + field + ": " + criteria + " was not found!"));
    }

    public User login(User user) throws EntityNotFoundException, Exception {
        User wantedUser = findByCriteria("user_email", user.getUserEmail());
        ProtectedConfigFile protectedConfigFile = new ProtectedConfigFile();
        protectedConfigFile.setEncryptedPassword(wantedUser.getUserPassword());
        String wantedUserDecrPassword = protectedConfigFile.getDecryptedPassword();
        if (wantedUser.getUserEmail().equals(user.getUserEmail()) && wantedUserDecrPassword.equals(user.getUserPassword())) {
            return wantedUser;
        } else {
            return null;
        }
    }
}