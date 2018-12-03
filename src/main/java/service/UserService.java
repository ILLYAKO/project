package service;

import com.google.common.annotations.VisibleForTesting;
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
    public UserService(Repository<User> userRepository){
        this.userRepository = userRepository;
    }

    // @VisibleForTesting
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


    public User findByCriteria(String field, String criteria) {
        System.out.println("UserService.findByCriteria  user.email: " + field + " = " + criteria);
        return userRepository.findByCriteria(field, criteria).orElseThrow(() ->
                new EntityNotFoundException("User with " + field + ": " + criteria + " was not found!"));
    }

    public User login(User user) throws EntityNotFoundException, Exception {
        System.out.println("UserService.login user.email: " + user.getUserEmail());
        User wantedUser =  findByCriteria("user_email", user.getUserEmail());



        ProtectedConfigFile protectedConfigFile = new ProtectedConfigFile();
        protectedConfigFile.setEncryptedPassword( wantedUser.getUserPassword());
        String wantedUserDecrPassword = protectedConfigFile.getDecryptedPassword();
        //


        if(  wantedUser.getUserEmail().equals(user.getUserEmail()) && wantedUserDecrPassword.equals(user.getUserPassword())  ){
            System.out.println("UserService.login wantedUser.email: " + wantedUser.getUserEmail());
            return  wantedUser;
        }else {
            return null;
        }
    }












//    public User login(User user)throws Exception{
//
//        UserRepository userRepository = new UserRepository();
//        String id = user.getUserID();
//        String userName = user.getUserName();
//        String userPassword = user.getUserPassword();
//        User wantedUser = userRepository.findUserByUsername(userName);
//
//        if((wantedUser != null) &&  userPassword.equals(wantedUser.getUserPassword())  ){
//            return wantedUser;
//        }else{
//            throw new Exception("Wrong name and password combination");
//        }
//    }

//    public User registrationNewUser(User user)throws Exception{
//
//        UserRepository userRepository = new UserRepository();
//        String id = user.getUserID();
//        String userName = user.getUserName();
//
//        if(userRepository.findUserByUsername(userName) != null){
//            throw new Exception("This user name is already in use, choose another user name!");
//        }else{
//            userRepository.addUser(user);
//            if(userRepository.findUserByID(id) == null){
//                throw new Exception("The Registration wasn't executed!");
//            }else{
//                return userRepository.findUserByID(id);
//            }
//        }
//    }


}
